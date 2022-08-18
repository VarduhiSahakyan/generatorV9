package com.energizeglobal.sqlgenerator.domain;

import javax.persistence.*;

@Entity
@Table(name = "SubIssuer")
public class SubIssuer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "authentMeans")
    private String authentMeans;

    @ManyToOne
    @JoinColumn(name = "fk_id_issuer", referencedColumnName = "id", updatable = false)
    private Issuer issuer;

    @ManyToOne
    @JoinColumn(name = "fk_id_cryptoConfig", referencedColumnName = "id", updatable = false)
    private CryptoConfigurationEntity cryptoConfigEntity;

    @Column
    private String acsId;

    @Column
    private Integer authenticationTimeOut;

    @Column
    private String defaultLanguage;

    @Column
    private String codeSvi;

    @Column
    private String currencyCode;

    @Column(nullable = false)
    private String label;

    @Column(columnDefinition = "BIT", length = 1)
    private boolean resetBackupsIfSuccess;

    @Column(columnDefinition = "BIT", length = 1)
    private Boolean personnalDataStorage;

    @Column(columnDefinition = "BIT", length = 1)
    private boolean resetChoicesIfSuccess;

    @Column(columnDefinition = "BIT", length = 1)
    private boolean manageBackupsCombinedAmounts;

    @Column(columnDefinition = "BIT", length = 1)
    private boolean manageChoicesCombinedAmounts;

    @Column(columnDefinition = "BIT", length = 1)
    private boolean hubMaintenanceModeEnabled;

    public SubIssuer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthentMeans() {
        return authentMeans;
    }

    public void setAuthentMeans(String authentMeans) {
        this.authentMeans = authentMeans;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
    }

    public boolean isResetChoicesIfSuccess() {
        return resetChoicesIfSuccess;
    }

    public boolean isManageBackupsCombinedAmounts() {
        return manageBackupsCombinedAmounts;
    }

    public boolean isManageChoicesCombinedAmounts() {
        return manageChoicesCombinedAmounts;
    }

    public boolean isHubMaintenanceModeEnabled() {
        return hubMaintenanceModeEnabled;
    }

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

    public boolean getResetBackupsIfSuccess() {
        return resetBackupsIfSuccess;
    }

    public void setResetBackupsIfSuccess(boolean resetBackupsIfSuccess) {
        this.resetBackupsIfSuccess = resetBackupsIfSuccess;
    }

    public boolean isResetBackupsIfSuccess() {
        return resetBackupsIfSuccess;
    }

    public boolean getResetChoicesIfSuccess() {
        return resetChoicesIfSuccess;
    }

    public void setResetChoicesIfSuccess(boolean resetChoicesIfSuccess) {
        this.resetChoicesIfSuccess = resetChoicesIfSuccess;
    }

    public boolean getManageBackupsCombinedAmounts() {
        return manageBackupsCombinedAmounts;
    }

    public void setManageBackupsCombinedAmounts(boolean manageBackupsCombinedAmounts) {
        this.manageBackupsCombinedAmounts = manageBackupsCombinedAmounts;
    }

    public boolean getManageChoicesCombinedAmounts() {
        return manageChoicesCombinedAmounts;
    }

    public void setManageChoicesCombinedAmounts(boolean manageChoicesCombinedAmounts) {
        this.manageChoicesCombinedAmounts = manageChoicesCombinedAmounts;
    }

    public boolean getHubMaintenanceModeEnabled() {
        return hubMaintenanceModeEnabled;
    }

    public void setHubMaintenanceModeEnabled(boolean hubMaintenanceModeEnabled) {
        this.hubMaintenanceModeEnabled = hubMaintenanceModeEnabled;
    }

    public CryptoConfigurationEntity getCryptoConfigEntity() {
        return cryptoConfigEntity;
    }

    public void setCryptoConfigEntity(CryptoConfigurationEntity cryptoConfigEntity) {
        this.cryptoConfigEntity = cryptoConfigEntity;
    }
}
