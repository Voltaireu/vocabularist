package com.voltaireu.vocabularist.security.acl;

import com.voltaireu.vocabularist.security.role.Role;
import com.voltaireu.vocabularist.security.role.RoleName;
import com.voltaireu.vocabularist.user.model.User;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.stereotype.Component;

@Component
public class AclPermissionManager implements PermissionManager{

    private final AclUtil aclUtil;

    public AclPermissionManager(AclUtil aclUtil) {
        this.aclUtil = aclUtil;
    }

    public void addPermission(Class resourceClass, Long resourceId, Sid sid, Permission permission) {

        MutableAcl acl = aclUtil.getAcl(resourceClass, resourceId);
        acl.insertAce(acl.getEntries().size(), permission, sid, true);
        aclUtil.updateAcl(acl);
    }

    public void deleteAllAssociatedPermissions(Class resourceClass, Long resourceId) {
        ObjectIdentity objectIdentity = aclUtil.createObjectIdentity(resourceClass, resourceId);
        aclUtil.deleteAcl(objectIdentity);
    }

    public void setParentAcl(Class resourceClass, Long resourceId, Class parentResourceClass, Long parentResourceId) {
        MutableAcl resourceAcl = aclUtil.getAcl(resourceClass, resourceId);
        MutableAcl parentAcl = aclUtil.getAcl(parentResourceClass, parentResourceId);

        resourceAcl.setParent(parentAcl);
        aclUtil.updateAcl(resourceAcl);
    }

    public void deletePermission(Class resourceClass, Long resourceId, Sid sid, Permission permission) {
        MutableAcl acl = aclUtil.getAcl(resourceClass, resourceId);
        aclUtil.deleteAce(acl, permission, sid);
        aclUtil.updateAcl(acl);
    }

    public GrantedAuthoritySid createSid(Role role) {
        return new GrantedAuthoritySid(role.getName().name());
    }

    public GrantedAuthoritySid createSid(RoleName roleName) {
        return new GrantedAuthoritySid(roleName.name());
    }

    public PrincipalSid createSid(User user) {
        return new PrincipalSid(user.getUsername());
    }
}
