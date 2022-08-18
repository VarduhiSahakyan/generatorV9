package com.energizeglobal.sqlgenerator.dto;

import java.sql.Timestamp;

public class ProfileSetDTO {

    private String createdBy;
    private Timestamp creationDate;
    private String description;
    private String lastUpdateBy;
    private String name;
    private String updateState;

    private long subissuer;

    public ProfileSetDTO() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateState() {
        return updateState;
    }

    public void setUpdateState(String updateState) {
        this.updateState = updateState;
    }

    public long getSubissuer() {
        return subissuer;
    }

    public void setSubissuer(long subissuer) {
        this.subissuer = subissuer;
    }

    @Override
    public String toString() {
        return "ProfileSetDTO{" +
                "createdBy='" + createdBy + '\'' +
                ", creationDate=" + creationDate +
                ", description='" + description + '\'' +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                ", name='" + name + '\'' +
                ", updateState='" + updateState + '\'' +
                ", subissuer=" + subissuer +
                '}';
    }
}
