package com.energizeglobal.sqlgenerator.dto;

public class SubIssuerDTO {
    String acsId;
    Integer authenticationTimeOut;
    String defaultLanguage;
    String code;
    String codeSvi;
    String currencyCode;
    String name;
    String authentMeans;
    String label;
    Boolean personnalDataStorage;
    Boolean resetBackupsIfSuccess;
    Boolean resetChoicesIfSuccess;
    Boolean manageBackupsCombinedAmounts;
    Boolean manageChoicesCombinedAmounts;
    Boolean hubMaintenanceModeEnabled;
    private Long  issuer_id;
    private Long cryptoConfig_id;

    public String getAcsId() {
        return acsId;
    }

    public void setAcsId(String acsId) {
        this.acsId = acsId;
    }

    public Integer getAuthenticationTimeOut() {
        return authenticationTimeOut;
    }

    public void setAuthenticationTimeOut(Integer authenticationTimeOut) {
        this.authenticationTimeOut = authenticationTimeOut;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeSvi() {
        return codeSvi;
    }

    public void setCodeSvi(String codeSvi) {
        this.codeSvi = codeSvi;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthentMeans() {
        return authentMeans;
    }

    public void setAuthentMeans(String authentMeans) {
        this.authentMeans = authentMeans;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getPersonnalDataStorage() {
        return personnalDataStorage;
    }

    public void setPersonnalDataStorage(Boolean personnalDataStorage) {
        this.personnalDataStorage = personnalDataStorage;
    }

    public Boolean getResetBackupsIfSuccess() {
        return resetBackupsIfSuccess;
    }

    public void setResetBackupsIfSuccess(Boolean resetBackupsIfSuccess) {
        this.resetBackupsIfSuccess = resetBackupsIfSuccess;
    }

    public Boolean getResetChoicesIfSuccess() {
        return resetChoicesIfSuccess;
    }

    public void setResetChoicesIfSuccess(Boolean resetChoicesIfSuccess) {
        this.resetChoicesIfSuccess = resetChoicesIfSuccess;
    }

    public Boolean getManageBackupsCombinedAmounts() {
        return manageBackupsCombinedAmounts;
    }

    public void setManageBackupsCombinedAmounts(Boolean manageBackupsCombinedAmounts) {
        this.manageBackupsCombinedAmounts = manageBackupsCombinedAmounts;
    }

    public Boolean getManageChoicesCombinedAmounts() {
        return manageChoicesCombinedAmounts;
    }

    public void setManageChoicesCombinedAmounts(Boolean manageChoicesCombinedAmounts) {
        this.manageChoicesCombinedAmounts = manageChoicesCombinedAmounts;
    }

    public Boolean getHubMaintenanceModeEnabled() {
        return hubMaintenanceModeEnabled;
    }

    public void setHubMaintenanceModeEnabled(Boolean hubMaintenanceModeEnabled) {
        this.hubMaintenanceModeEnabled = hubMaintenanceModeEnabled;
    }

    public Long getIssuer_id() {
        return issuer_id;
    }

    public void setIssuer_id(Long issuer_id) {
        this.issuer_id = issuer_id;
    }

    public Long getCryptoConfig_id() {
        return cryptoConfig_id;
    }

    public void setCryptoConfig_id(Long cryptoConfig_id) {
        this.cryptoConfig_id = cryptoConfig_id;
    }
}