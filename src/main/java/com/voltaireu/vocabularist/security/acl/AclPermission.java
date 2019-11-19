package com.voltaireu.vocabularist.security.acl;

import org.springframework.security.acls.domain.AbstractPermission;
import org.springframework.security.acls.model.Permission;

public class AclPermission extends AbstractPermission {
    public static final Permission READ = new AclPermission(1 << 0, 'R'); // 1
    public static final Permission WRITE = new AclPermission(1 << 1, 'W'); // 2
    public static final Permission CREATE = new AclPermission(1 << 2, 'C'); // 4
    public static final Permission DELETE = new AclPermission(1 << 3, 'D'); // 8
    public static final Permission ADMINISTRATION = new AclPermission(1 << 4, 'A'); // 16

    protected AclPermission(int mask) {
        super(mask);
    }

    protected AclPermission(int mask, char code) {
        super(mask, code);
    }
}
