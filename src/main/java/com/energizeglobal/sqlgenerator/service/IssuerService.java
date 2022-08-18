package com.energizeglobal.sqlgenerator.service;

import com.energizeglobal.sqlgenerator.domain.Issuer;
import com.energizeglobal.sqlgenerator.dto.IssuerDTO;
import com.energizeglobal.sqlgenerator.repository.IssuerRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;

@Service
public class IssuerService {

    private final String FILE_PATH = "src/main/resources/sql_scripts/";
    private final String DATA_FILE_NAME = "issuer.sql";
    private final String DATA_ROLLBACK_FILE_NAME = "rollback_issuer.sql";
    private Timestamp thisMomentTime = new Timestamp(System.currentTimeMillis());
    private final IssuerRepository issuerRepository;

    public IssuerService(IssuerRepository issuerRepository) {
        this.issuerRepository = issuerRepository;
    }

    @Transactional(readOnly = true)
    public List<Issuer> getAllIssuer() {
        List<Issuer> issuerList = issuerRepository.findAll();
        return issuerList;
    }

    public Issuer findByIssuerByCode(String code) {
        Issuer issuer = issuerRepository.getIssuerByCode(code);
        return issuer;
    }

    public String generateInsertSqlScript(IssuerDTO dto) {
        String Insert = "INSERT INTO Issuer (code, createdBy,creationDate, name, updateState,label, availaibleAuthentMeans) " +
                "VALUES ('" + dto.getCode() + "', '" + dto.getCreatedBy() + "','" + thisMomentTime + "','" + dto.getName() + "','" + dto.getUpdateState() + "','" + dto.getLabel() + "','" + dto.getAvailaibleAuthentMeans() + "');";
        String path = FILE_PATH + DATA_FILE_NAME;
        return this.storeQueryInFile(path, Insert);
    }

    public String generateInsertSqlScriptWithRollback(IssuerDTO dto) {
        String delete = "START TRANSACTION; \n" +
                "SET FOREIGN_KEY_CHECKS = 0; \n" +
                "DELETE FROM Issuer WHERE id = '" + dto.getCode() + "';\n" +
                "SET FOREIGN_KEY_CHECKS = 1; \n" +
                "COMMIT;";
        String path = FILE_PATH + DATA_ROLLBACK_FILE_NAME;
        return this.storeQueryInFile(path, delete);
    }

    public String generateEditSqlScript(IssuerDTO dto, String code) {
        String sql = "UPDATE Issuer SET " + "createdBy='" + dto.getCreatedBy() + "', " + "creationDate='" + dto.getCreationDate() + "' ," + "name='" + dto.getName() + "', " + "updateState='" + dto.getUpdateState() + "', " + "label='" + dto.getLabel() + "' , " + "availaibleAuthentMeans='" + dto.getAvailaibleAuthentMeans() + "'" + " WHERE code='" + code + "';";
        String path = FILE_PATH + DATA_FILE_NAME;
        return this.storeQueryInFile(path, sql);
    }

    public String generateEditSqlScriptWithRollback(String code) {
        Issuer issuer = this.issuerRepository.getIssuerByCode(code);
        String sql = "UPDATE Issuer SET " + "createdBy='" + issuer.getCreatedBy() + "', " + "creationDate='" + issuer.getCreationDate() + "' ," + "name='" + issuer.getName() + "', " + "updateState='" + issuer.getUpdateState() + "', " + "label='" + issuer.getLabel() + "' , " + "availaibleAuthentMeans='" + issuer.getAvailaibleAuthentMeans() + "'" + " WHERE code='" + issuer.getCode() + "';";
        String path = FILE_PATH + DATA_ROLLBACK_FILE_NAME;
        return this.storeQueryInFile(path, sql);
    }

    public String generateDeleteSqlScript(String code) {
        String sql = "START TRANSACTION; \n" +
                "SET FOREIGN_KEY_CHECKS = 0; \n" +
                "DELETE FROM Issuer WHERE id = '" + code + "';\n" +
                "SET FOREIGN_KEY_CHECKS = 1; \n" +
                "COMMIT;";

        String path = FILE_PATH + DATA_FILE_NAME;
        return this.storeQueryInFile(path, sql);
    }

    public String generateDeleteSqlScriptWithRollback(String code) {
        Issuer issuer = this.issuerRepository.getIssuerByCode(code);
        String sql = "INSERT INTO Issuer (code, createdBy,creationDate, name, updateState,label,availaibleAuthentMeans) " +
                "VALUES ('" + issuer.getCode() + "', '" + issuer.getCreatedBy() + "','" + issuer.getCreationDate() + "','" + issuer.getName() + "','" + issuer.getUpdateState() + "','" + issuer.getLabel() + "','" + issuer.getAvailaibleAuthentMeans() + "');";
        String path = FILE_PATH + DATA_ROLLBACK_FILE_NAME;
        return this.storeQueryInFile(path, sql);
    }

    private String storeQueryInFile(String path, String sql) {

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
        return DATA_FILE_NAME;
    }

    public Resource downloadFile(String filename) {
        try {
            Path file = Paths.get(FILE_PATH).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
