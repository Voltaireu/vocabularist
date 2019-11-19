package com.voltaireu.vocabularist.security.acl;

import com.voltaireu.vocabularist.security.role.Role;
import com.voltaireu.vocabularist.security.role.RoleName;
import com.voltaireu.vocabularist.user.model.User;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;

public interface PermissionManager {

    /**
     * Returns Sid (security identity) object based on given Role object
     *
     * @param role Role object
     * @return Sid
     */
    public Sid createSid(Role role);

    /**
     * Returns Sid (security identity) object based on given RoleName enum
     *
     * @param roleName RoleName enum
     * @return Sid
     */
    public Sid createSid(RoleName roleName);

    /**
     * Returns Sid (security identity) object based on given User object
     *
     * @param user User object
     * @return Sid
     */
    public Sid createSid(User user);

    /**
     * Grants permission to resource with given class and id to given Sid
     *
     * @param resourceClass class of resource object
     * @param resourceId id of resource object
     * @param sid Sid object to be associated with given permission
     * @param permission permission to be granted
     */
    public void addPermission(Class resourceClass, Long resourceId, Sid sid, Permission permission);

    /**
     * Overrules given permission for resource with given class and id for given Sid
     *
     * @param resourceClass class of resource object
     * @param resourceId id of resource object
     * @param sid Sid object to be associated with given permission
     * @param permission permission to be granted
     */
    public void deletePermission(Class resourceClass, Long resourceId, Sid sid, Permission permission);

    /**
     * Deletes from database ACL object associated with resource with given class and id
     *
     * @param resourceClass class of resource object
     * @param resourceId id of resource object
     */
    public void deleteAllAssociatedPermissions(Class resourceClass, Long resourceId);

    /**
     * Assigns ACL of one resource as a child of ACL of another resource granting inheritance of ACE's from parent resource to child resource
     * In other words all permissions granted for given sids for parent resource will be granted also to child resource
     *
     * @param resourceClass class of child resource
     * @param resourceId id of child resource
     * @param parentResourceClass class of parent resource
     * @param parentResourceId id of parent resource
     */
    public void setParentAcl(Class resourceClass, Long resourceId, Class parentResourceClass, Long parentResourceId);
}
