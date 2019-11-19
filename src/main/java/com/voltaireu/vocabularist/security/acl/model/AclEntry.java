package com.voltaireu.vocabularist.security.acl.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Entity
@Table(name = "acl_entry")
public class AclEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "acl_object_identity")
    private AclObjectIdentity aclObjectIdentity;

    @NotNull
    @Column
    private Integer aceOrder;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sid")
    private AclSid sid;

    @NotNull
    @Column
    private Integer mask;

    @NotNull
    @Column
    private Boolean granting;

    @NotNull
    @Column
    private Boolean auditSuccess;

    @NotNull
    @Column
    private Boolean auditFailure;

    public Long getId() {
        return id;
    }

    public AclObjectIdentity getAclObjectIdentity() {
        return aclObjectIdentity;
    }

    public void setAclObjectIdentity(AclObjectIdentity aclObjectIdentity) {
        this.aclObjectIdentity = aclObjectIdentity;
    }

    public Integer getAceOrder() {
        return aceOrder;
    }

    public void setAceOrder(Integer aceOrder) {
        this.aceOrder = aceOrder;
    }

    public AclSid getSid() {
        return sid;
    }

    public void setSid(AclSid sid) {
        this.sid = sid;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public Boolean getGranting() {
        return granting;
    }

    public void setGranting(Boolean granting) {
        this.granting = granting;
    }

    public Boolean getAuditSuccess() {
        return auditSuccess;
    }

    public void setAuditSuccess(Boolean auditSuccess) {
        this.auditSuccess = auditSuccess;
    }

    public Boolean getAuditFailure() {
        return auditFailure;
    }

    public void setAuditFailure(Boolean auditFailure) {
        this.auditFailure = auditFailure;
    }
}
