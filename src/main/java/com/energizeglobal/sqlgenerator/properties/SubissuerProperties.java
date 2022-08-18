package com.energizeglobal.sqlgenerator.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "subissuer")
@PropertySource("classpath:subissuer.properties")
public class SubissuerProperties {

    String subIssuerName;
    String subIssuerLabel;
    String subIssuerCode;
    String acsId;
    String defaultLanguage;
    String subIssuerCodeSvi;
    String currencyCode;
    int authenticationTimeOut;


    public String getSubIssuerName() {
        return subIssuerName;
    }

    public void setSubIssuerName(String subIssuerName) {
        this.subIssuerName = subIssuerName;
    }

    public String getSubIssuerLabel() {
        return subIssuerLabel;
    }

    public void setSubIssuerLabel(String subIssuerLabel) {
        this.subIssuerLabel = subIssuerLabel;
    }

    public String getSubIssuerCode() {
        return subIssuerCode;
    }

    public void setSubIssuerCode(String subIssuerCode) {
        this.subIssuerCode = subIssuerCode;
    }

    public String getAcsId() {
        return acsId;
    }

    public void setAcsId(String acsId) {
        this.acsId = acsId;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public String getSubIssuerCodeSvi() {
        return subIssuerCodeSvi;
    }

    public void setSubIssuerCodeSvi(String subIssuerCodeSvi) {
        this.subIssuerCodeSvi = subIssuerCodeSvi;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getAuthenticationTimeOut() {
        return authenticationTimeOut;
    }

    public void setAuthenticationTimeOut(int authenticationTimeOut) {
        this.authenticationTimeOut = authenticationTimeOut;
    }
}
