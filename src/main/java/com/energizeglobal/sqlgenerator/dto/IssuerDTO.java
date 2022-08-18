package com.energizeglobal.sqlgenerator.dto;

import java.sql.Timestamp;

public class IssuerDTO {

    private String code;
    private String createdBy;
    private Timestamp creationDate;
    private String name;
    private String updateState;
    private String label;
    private String availaibleAuthentMeans;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAvailaibleAuthentMeans() {
        return availaibleAuthentMeans;
    }

    public void setAvailaibleAuthentMeans(String availaibleAuthentMeans) {
        this.availaibleAuthentMeans = availaibleAuthentMeans;
    }

    @Override
    public String toString() {
        return "IssuerDTO{" +
                "code='" + code + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", creationDate=" + creationDate +
                ", name='" + name + '\'' +
                ", updateState='" + updateState + '\'' +
                ", label='" + label + '\'' +
                ", availaibleAuthentMeans='" + availaibleAuthentMeans + '\'' +
                '}';
    }
}
