package com.voltaireu.vocabularist.security.acl;

import org.springframework.security.acls.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AclUtil {

    private final MutableAclService mutableAclService;

    public AclUtil(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }

    public MutableAcl getAcl(ObjectIdentity objectIdentity) {
        MutableAcl acl;
        try {
            acl = (MutableAcl) mutableAclService.readAclById(objectIdentity);
        }
        catch (NotFoundException nfe) {
            acl = mutableAclService.createAcl(objectIdentity);
        }
        return acl;
    }

    public void deleteAce(MutableAcl acl, Permission permission, Sid sid) {
        List<AccessControlEntry> entries = acl.getEntries();
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getSid().equals(sid)
                    && entries.get(i).getPermission().equals(permission)) {
                acl.deleteAce(i);
            }
        }
    }
}
