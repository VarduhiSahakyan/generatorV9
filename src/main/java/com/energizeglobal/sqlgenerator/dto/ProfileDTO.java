package com.energizeglobal.sqlgenerator.dto;

import java.sql.Timestamp;
import java.time.Instant;

public class ProfileDTO {
    private long id;
    private String createdBy;
    private Timestamp creationDate;
    private String description;
    private String lastUpdateBy;
    private Instant lastUpdateDate;
    private String name;
    private String updateState;
    private long maxAttempts;
    private String dataEntryFormat;
    private String dataEntryAllowedPattern;

    private long authentMeans;
    private long subissuer;

    public ProfileDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getUpdateState() {
        return updateState;
    }

    public void setUpdateState(String updateState) {
        this.updateState = updateState;
    }

    public long getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(long maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public String getDataEntryFormat() {
        return dataEntryFormat;
    }

    public void setDataEntryFormat(String dataEntryFormat) {
        this.dataEntryFormat = dataEntryFormat;
    }

    public String getDataEntryAllowedPattern() {
        return dataEntryAllowedPattern;
    }

    public void setDataEntryAllowedPattern(String dataEntryAllowedPattern) {
        this.dataEntryAllowedPattern = dataEntryAllowedPattern;
    }

    public long getAuthentMeans() {
        return authentMeans;
    }

    public void setAuthentMeans(long authentMeans) {
        this.authentMeans = authentMeans;
    }

    public long getSubissuer() {
        return subissuer;
    }

    public void setSubissuer(long subissuer) {
        this.subissuer = subissuer;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "id=" + id +
                ", createdBy='" + createdBy + '\'' +
                ", creationDate=" + creationDate +
                ", description='" + description + '\'' +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                ", name='" + name + '\'' +
                ", updateState='" + updateState + '\'' +
                ", maxAttempts=" + maxAttempts +
                ", dataEntryFormat='" + dataEntryFormat + '\'' +
                ", dataEntryAllowedPattern='" + dataEntryAllowedPattern + '\'' +
                ", authentMeans=" + authentMeans +
                ", subissuer=" + subissuer +
                '}';
    }
}
