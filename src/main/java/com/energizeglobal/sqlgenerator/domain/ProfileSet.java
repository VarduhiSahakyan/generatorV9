package com.energizeglobal.sqlgenerator.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "ProfileSet")
public class ProfileSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "createdBy", nullable = false)
    private String createdBy;

    @Column(name = "creationDate",nullable = false)
    private Timestamp creationDate;

    @Column(name = "description")
    private String description;

    @Column(name = "lastUpdateBy")
    private String lastUpdateBy;

    @Column(name = "lastUpdateDate")
    private Instant lastUpdateDate;

    @Column(name = "name")
    private String name;

    @Column(name = "updateState", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileSet.UpdateState updateState;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "fk_id_subIssuer", referencedColumnName = "id")
    private SubIssuer subIssuer;

    public enum UpdateState{
        WAITING_VALIDATION,
        PUSHED_TO_CONFIG,
        WAITING_FOR_PUSH
    }

    public ProfileSet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Instant getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Instant lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UpdateState getUpdateState() {
        return updateState;
    }

    public void setUpdateState(UpdateState updateState) {
        this.updateState = updateState;
    }

    public SubIssuer getSubIssuer() {
        return subIssuer;
    }

    public void setSubIssuer(SubIssuer subIssuer) {
        this.subIssuer = subIssuer;
    }
}
