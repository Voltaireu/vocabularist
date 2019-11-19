package com.voltaireu.vocabularist.security.acl;

import com.voltaireu.vocabularist.security.role.Role;
import com.voltaireu.vocabularist.user.model.User;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;

public interface PermissionManager {
    public Sid createSid(Role role);
    public Sid createSid(User user);

    public void addPermission(Class resourceClass, Long resourceId, Sid sid, Permission permission);
    public void addPrincipalPermission(Class resourceClass, Long resourceId, User user, Permission permission);
    public void addRolePermission(Class resourceClass, Long resourceId, Role role, Permission permission);

    public void deletePermission(Class resourceClass, Long resourceId, Sid sid, Permission permission);
    public void deletePrincipalPermission(Class resourceClass, Long resourceId, User user, Permission permission);
    public void deleteRolePermission(Class resourceClass, Long resourceId, Role role, Permission permission);

    public void deleteAllAssociatedPermissions(Class resourceClass, Long resourceId);
}
