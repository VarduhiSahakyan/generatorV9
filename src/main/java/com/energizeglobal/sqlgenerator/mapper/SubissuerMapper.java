package com.energizeglobal.sqlgenerator.mapper;

import com.energizeglobal.sqlgenerator.domain.CryptoConfigurationEntity;
import com.energizeglobal.sqlgenerator.domain.Issuer;
import com.energizeglobal.sqlgenerator.domain.SubIssuer;
import com.energizeglobal.sqlgenerator.dto.SubIssuerDTO;
import com.energizeglobal.sqlgenerator.properties.SubissuerProperties;

public class SubissuerMapper {

    public static SubIssuer dtoToEntity(SubIssuerDTO subIssuerDto) {
        SubIssuer subIssuer = new SubIssuer();
        subIssuer.setAcsId(subIssuerDto.getAcsId());
        subIssuer.setAuthenticationTimeOut(subIssuerDto.getAuthenticationTimeOut());
        subIssuer.setDefaultLanguage(subIssuerDto.getDefaultLanguage());
        subIssuer.setCode(subIssuerDto.getCode());
        subIssuer.setCodeSvi(subIssuerDto.getCodeSvi());
        subIssuer.setCurrencyCode(subIssuerDto.getCurrencyCode());
        subIssuer.setName(subIssuerDto.getName());
        subIssuer.setLabel(subIssuerDto.getLabel());
        subIssuer.setAuthentMeans(subIssuerDto.getAuthentMeans());
        subIssuer.setPersonnalDataStorage(subIssuerDto.getPersonnalDataStorage());
        subIssuer.setResetBackupsIfSuccess(subIssuerDto.getResetBackupsIfSuccess());
        subIssuer.setResetChoicesIfSuccess(subIssuerDto.getResetChoicesIfSuccess());
        subIssuer.setManageBackupsCombinedAmounts(subIssuerDto.getManageBackupsCombinedAmounts());
        subIssuer.setManageChoicesCombinedAmounts(subIssuerDto.getManageChoicesCombinedAmounts());
        subIssuer.setHubMaintenanceModeEnabled(subIssuerDto.getHubMaintenanceModeEnabled());

        return subIssuer;
    }


    public static SubIssuer dtoToEntity(SubIssuerDTO subIssuerDto, Issuer issuer, CryptoConfigurationEntity crypto) {

        SubIssuer subIssuer = new SubIssuer();

        subIssuer.setAcsId(subIssuerDto.getAcsId());
        subIssuer.setAuthenticationTimeOut(subIssuerDto.getAuthenticationTimeOut());
        subIssuer.setDefaultLanguage(subIssuerDto.getDefaultLanguage());
        subIssuer.setCode(subIssuerDto.getCode());
        subIssuer.setCodeSvi(subIssuerDto.getCodeSvi());
        subIssuer.setCurrencyCode(subIssuerDto.getCurrencyCode());
        subIssuer.setName(subIssuerDto.getName());
        subIssuer.setLabel(subIssuerDto.getLabel());
        subIssuer.setAuthentMeans(subIssuerDto.getAuthentMeans());
        subIssuer.setPersonnalDataStorage(subIssuerDto.getPersonnalDataStorage());
        subIssuer.setResetBackupsIfSuccess(subIssuerDto.getResetBackupsIfSuccess());
        subIssuer.setResetChoicesIfSuccess(subIssuerDto.getResetChoicesIfSuccess());
        subIssuer.setManageBackupsCombinedAmounts(subIssuerDto.getManageBackupsCombinedAmounts());
        subIssuer.setManageChoicesCombinedAmounts(subIssuerDto.getManageChoicesCombinedAmounts());
        subIssuer.setHubMaintenanceModeEnabled(subIssuerDto.getHubMaintenanceModeEnabled());
        subIssuer.setIssuer(issuer);
        subIssuer.setCryptoConfigEntity(crypto);

        return subIssuer;
    }


    public static SubIssuerDTO entityToDto(SubIssuer subIssuer) {
        SubIssuerDTO subIssuerDto = new SubIssuerDTO();
        subIssuerDto.setAcsId(subIssuer.getAcsId());
        subIssuerDto.setAuthenticationTimeOut(subIssuer.getAuthenticationTimeOut());
        subIssuerDto.setDefaultLanguage(subIssuer.getDefaultLanguage());
        subIssuerDto.setCode(subIssuer.getCode());
        subIssuerDto.setCodeSvi(subIssuer.getCodeSvi());
        subIssuerDto.setCurrencyCode(subIssuer.getCurrencyCode());
        subIssuerDto.setName(subIssuer.getName());
        subIssuerDto.setLabel(subIssuer.getLabel());
        subIssuerDto.setAuthentMeans(subIssuer.getAuthentMeans());
        subIssuerDto.setPersonnalDataStorage(subIssuer.getPersonnalDataStorage());
        subIssuerDto.setResetBackupsIfSuccess(subIssuer.getResetBackupsIfSuccess());
        subIssuerDto.setResetChoicesIfSuccess(subIssuer.getResetChoicesIfSuccess());
        subIssuerDto.setManageBackupsCombinedAmounts(subIssuer.getManageBackupsCombinedAmounts());
        subIssuerDto.setManageChoicesCombinedAmounts(subIssuer.getManageChoicesCombinedAmounts());
        subIssuerDto.setHubMaintenanceModeEnabled(subIssuer.getHubMaintenanceModeEnabled());

        return subIssuerDto;
    }

    public static SubIssuerDTO propertiesToDto(SubissuerProperties properties) {
        SubIssuerDTO subIssuerDto = new SubIssuerDTO();

        subIssuerDto.setName(properties.getSubIssuerName());
        subIssuerDto.setLabel(properties.getSubIssuerLabel());
        subIssuerDto.setCode(properties.getSubIssuerCode());
        subIssuerDto.setAcsId(properties.getAcsId());
        subIssuerDto.setDefaultLanguage(properties.getDefaultLanguage());
        subIssuerDto.setCurrencyCode(properties.getCurrencyCode());
        subIssuerDto.setCodeSvi(properties.getSubIssuerCodeSvi());
        subIssuerDto.setAuthenticationTimeOut(properties.getAuthenticationTimeOut());

        return subIssuerDto;
    }
}
