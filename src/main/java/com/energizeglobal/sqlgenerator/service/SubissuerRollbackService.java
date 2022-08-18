package com.energizeglobal.sqlgenerator.service;

import com.energizeglobal.sqlgenerator.domain.SubIssuer;
import com.energizeglobal.sqlgenerator.dto.SubIssuerDTO;
import com.energizeglobal.sqlgenerator.mapper.SubissuerMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;

@Service
public class SubissuerRollbackService {

    String FILE_PATH = "src/main/resources/sql_scripts/";
    String ROLLBACK_FILE_NAME = "subissuer_rollback.sql";
    String path = FILE_PATH + ROLLBACK_FILE_NAME;


    public String generateSqlScriptForDeleteRollback(SubIssuerDTO dto, Long issuer_id, Long crypto_id) {

        SubIssuer subIssuer = SubissuerMapper.dtoToEntity(dto);

        String queryType = "INSERT INTO SubIssuer  ( " +
                "acsId, " +
                "authenticationTimeOut, " +
                " defaultLanguage, " +
                "code, " +
                "codeSvi, " +
                "currencyCode, " +
                "name, " +
                "label, " +
                "authentMeans, " +
                "personnalDataStorage, " +
                "resetBackupsIfSuccess," +
                "resetChoicesIfSuccess, " +
                "manageBackupsCombinedAmounts, " +
                "manageChoicesCombinedAmounts, " +
                "hubMaintenanceModeEnabled, " +
                "fk_id_issuer, " +
                "fk_id_cryptoConfig )";

        String queryValue = "  VALUES ('" +
                subIssuer.getAcsId() + "', " +
                subIssuer.getAuthenticationTimeOut() + ", '" +
                subIssuer.getDefaultLanguage() + "', '" +
                subIssuer.getCode() + "', '" +
                subIssuer.getCodeSvi() + "', '" +
                subIssuer.getCurrencyCode() + "', '" +
                subIssuer.getName() + "', '" +
                subIssuer.getLabel() + "', '" +
                subIssuer.getAuthentMeans() + "', " +
                subIssuer.getPersonnalDataStorage() + ", " +
                subIssuer.isResetBackupsIfSuccess() + ", " +
                subIssuer.isResetChoicesIfSuccess() + ", " +
                subIssuer.isManageBackupsCombinedAmounts() + ", " +
                subIssuer.isManageChoicesCombinedAmounts() + ", " +
                subIssuer.isHubMaintenanceModeEnabled() + ", " +
                issuer_id + ", " +
                crypto_id + ");";

        String sqlInsert = queryType + queryValue;

        pathGenerator(sqlInsert);

        return ROLLBACK_FILE_NAME;
    }

    public String generateSqlScriptForUpdateRollback(SubIssuer oldSubIssuer) {

        String queryUpdate = "UPDATE SubIssuer SET " +
                "acsId = '" + oldSubIssuer.getAcsId() + "', " +
                "authenticationTimeOut = " + oldSubIssuer.getAuthenticationTimeOut() + ", " +
                "defaultLanguage = '" + oldSubIssuer.getDefaultLanguage() + "', " +
                "codeSvi = " + oldSubIssuer.getCodeSvi() + ", " +
                "currencyCode = '" + oldSubIssuer.getCurrencyCode() + "', " +
                "name = '" + oldSubIssuer.getName() + "', " +
                "label = '" + oldSubIssuer.getLabel() + "', " +
                "authentMeans = '" + oldSubIssuer.getAuthentMeans() + "', " +
                "personnalDataStorage = " + oldSubIssuer.getPersonnalDataStorage() + ", " +
                "resetBackupsIfSuccess = " + oldSubIssuer.isResetBackupsIfSuccess() + ", " +
                "resetChoicesIfSuccess = " + oldSubIssuer.isResetChoicesIfSuccess() + ", " +
                "manageBackupsCombinedAmounts = " + oldSubIssuer.isManageBackupsCombinedAmounts() + ", " +
                "manageChoicesCombinedAmounts = " + oldSubIssuer.isManageChoicesCombinedAmounts() + ", " +
                "hubMaintenanceModeEnabled = " + oldSubIssuer.isHubMaintenanceModeEnabled() + " " +
                " WHERE code = " + oldSubIssuer.getCode() + ";";

        pathGenerator(queryUpdate);

        return ROLLBACK_FILE_NAME;
    }

    public String generateSqlScriptForInsertRollback(String code) {

        String deleteQuery = "\n" +
                "START TRANSACTION; \n" +
                "SET FOREIGN_KEY_CHECKS = 0; \n" +
                "DELETE FROM SubIssuer WHERE code = " + code + ";\n" +
                "SET FOREIGN_KEY_CHECKS = 1; \n" +
                "COMMIT;";

        pathGenerator(deleteQuery);

        return ROLLBACK_FILE_NAME;
    }

    private void pathGenerator(String sql) {
        Path newFilePath = Paths.get(path);
        try {
            if (Files.exists(newFilePath)) {
                sql = System.getProperty("line.separator") + sql;
                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(newFilePath, UTF_8, APPEND)) {
                    bufferedWriter.write(sql);
                }
            } else {
                Path fileDirectory = Paths.get(FILE_PATH);
                Files.createDirectories(fileDirectory);
                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(newFilePath, UTF_8)) {
                    bufferedWriter.write(sql);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
