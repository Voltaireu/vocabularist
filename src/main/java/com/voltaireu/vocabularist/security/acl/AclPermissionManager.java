package com.voltaireu.vocabularist.security.acl;

import com.voltaireu.vocabularist.security.authority.Role;
import com.voltaireu.vocabularist.user.model.User;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.stereotype.Component;

@Component
public class AclPermissionManager implements PermissionManager{

    private final MutableAclService mutableAclService;
    private final AclUtil aclUtil;

    public AclPermissionManager(MutableAclService mutableAclService, AclUtil aclUtil) {
        this.mutableAclService = mutableAclService;
        this.aclUtil = aclUtil;
    }

    public void addPermission(Class resourceClass, Long resourceId, Sid sid, Permission permission) {
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(resourceClass, resourceId);
        MutableAcl acl = aclUtil.getAcl(objectIdentity);
        acl.insertAce(acl.getEntries().size(), permission, sid, true);
        mutableAclService.updateAcl(acl);
    }

    public void addPrincipalPermission(Class resourceClass, Long resourceId, User user, Permission permission) {
        PrincipalSid sid = createSid(user);
        addPermission(resourceClass, resourceId, sid, permission);
    }

    public void addRolePermission(Class resourceClass, Long resourceId, Role role, Permission permission) {
        GrantedAuthoritySid sid = createSid(role);
        addPermission(resourceClass, resourceId, sid, permission);
    }

    public void deleteAllAssociatedPermissions(Class resourceClass, Long resourceId) {
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(resourceClass, resourceId);
        mutableAclService.deleteAcl(objectIdentity, false);
    }

    public void deletePrincipalPermission(Class resourceClass, Long resourceId, User user, Permission permission) {
        Sid sid = createSid(user);
        deletePermission(resourceClass, resourceId, sid, permission);
    }

    public void deleteRolePermission(Class resourceClass, Long resourceId, Role role, Permission permission) {
        Sid sid = createSid(role);
        deletePermission(resourceClass, resourceId, sid, permission);
    }

    public void deletePermission(Class resourceClass, Long resourceId, Sid sid, Permission permission) {
        ObjectIdentity oid = new ObjectIdentityImpl(resourceClass, resourceId);
        MutableAcl acl = (MutableAcl) mutableAclService.readAclById(oid);
        aclUtil.deleteAce(acl, permission, sid);
        mutableAclService.updateAcl(acl);
    }

    public GrantedAuthoritySid createSid(Role role) {
        return new GrantedAuthoritySid(role.getName());
    }

    public PrincipalSid createSid(User user) {
        return new PrincipalSid(user.getUsername());
    }
}
