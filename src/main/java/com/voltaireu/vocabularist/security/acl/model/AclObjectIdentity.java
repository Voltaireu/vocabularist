package com.voltaireu.vocabularist.security.acl.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "acl_object_identity")
public class AclObjectIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "object_id_class")
    private AclClass objectIdClass;

    @NotNull
    @Column
    private Long objectIdIdentity;

    @ManyToOne
    @JoinColumn(name = "parent_object")
    private AclObjectIdentity parentObject;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_sid")
    private AclSid ownerSid;

    @Column
    private Boolean entriesInheriting;

    public Long getId() {
        return id;
    }

    public AclClass getObjectIdClass() {
        return objectIdClass;
    }

    public void setObjectIdClass(AclClass objectIdClass) {
        this.objectIdClass = objectIdClass;
    }

    public Long getObjectIdIdentity() {
        return objectIdIdentity;
    }

    public void setObjectIdIdentity(Long objectIdIdentity) {
        this.objectIdIdentity = objectIdIdentity;
    }

    public AclObjectIdentity getParentObject() {
        return parentObject;
    }

    public void setParentObject(AclObjectIdentity parentObject) {
        this.parentObject = parentObject;
    }

    public AclSid getOwnerSid() {
        return ownerSid;
    }

    public void setOwnerSid(AclSid ownerSid) {
        this.ownerSid = ownerSid;
    }

    public Boolean getEntriesInheriting() {
        return entriesInheriting;
    }

    public void setEntriesInheriting(Boolean entriesInheriting) {
        this.entriesInheriting = entriesInheriting;
    }
}
