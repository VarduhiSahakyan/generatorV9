package com.energizeglobal.sqlgenerator.service;

import com.energizeglobal.sqlgenerator.domain.ImageEntity;
import com.energizeglobal.sqlgenerator.dto.ImageDTO;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;

@Service
public class ImageRollbackService {
    String FILE_PATH = "src/main/resources/sql_scripts/";
    String ROLLBACK_FILE_NAME = "image_rollback.sql";
    String path = FILE_PATH + ROLLBACK_FILE_NAME;
    Timestamp thisMomentTime = new Timestamp(System.currentTimeMillis());

    public String generateSqlScriptForInsertRollback(String name) {

        String deleteQuery = "\n" +
                "START TRANSACTION; \n" +
                "SET FOREIGN_KEY_CHECKS = 0; \n" +
                "DELETE FROM Image WHERE name = '" + name + "';\n" +
                "SET FOREIGN_KEY_CHECKS = 1; \n" +
                "COMMIT;";

        pathGenerator(deleteQuery);

        return ROLLBACK_FILE_NAME;
    }

    public String generateSqlScriptForUpdateRollback(ImageEntity oldImage) {

        String queryUpdate = "UPDATE Image SET " +
                "createdBy = '" + oldImage.getCreatedBy() + "', " +
                "creationDate = '" + oldImage.getCreationDate() + "', " +
                "description = '" + oldImage.getDescription() + "', " +
                "lastUpdateBy = '" + oldImage.getLastUpdateBy() + "', " +
                "lastUpdateDate = '" + thisMomentTime + "', " +
                "name = '" + oldImage.getName() + "', " +
                "updateState = '" + oldImage.getUpdateState() + "', " +
                "binaryData = '" + oldImage.getBinaryData() + "', " +
                "relativePath = '" + oldImage.getRelativePath() + "' " +
                " WHERE id = " + oldImage.getId() + ";";

        pathGenerator(queryUpdate);

        return ROLLBACK_FILE_NAME;
    }

    public String generateSqlScriptForDeleteRollback(ImageDTO image) {

        String queryType = "INSERT INTO Image  ( " +
                "createdBy, " +
                "creationDate, " +
                "description, " +
                "lastUpdateBy, " +
                "name, " +
                "updateState, " +
                "binaryData, " +
                "relativePath )";

        String queryValue = "  VALUES (" + " '" +
                image.getCreatedBy() + "', '" +
                image.getCreationDate() + "', '" +
                image.getDescription() + "', '" +
                image.getLastUpdateBy() + "', '" +
                image.getName() + "', '" +
                image.getUpdateState() + "', '" +
                image.getBinaryData() + "', '" +
                image.getRelativePath() + "');";

        String sqlInsert = queryType + queryValue;

        pathGenerator(sqlInsert);

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
