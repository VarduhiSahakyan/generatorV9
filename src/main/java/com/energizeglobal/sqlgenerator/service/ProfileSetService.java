package com.energizeglobal.sqlgenerator.service;

import com.energizeglobal.sqlgenerator.domain.ProfileSet;;
import com.energizeglobal.sqlgenerator.dto.ProfileSetDTO;
import com.energizeglobal.sqlgenerator.repository.ProfileSetRepository;
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
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;

@Service
public class ProfileSetService {

    private String FILE_PATH = "src/main/resources/sql_scripts/";
    private String DATA_FILE_NAME = "profileSet_query.sql";
    private String DATA_ROLLBACK_FILE_NAME = "profileSet_rollback_query.sql";

    ProfileSetRepository profileSetRepository;

    public ProfileSetService(ProfileSetRepository profileSetRepository) {
        this.profileSetRepository = profileSetRepository;
    }

    @Transactional
    public List < ProfileSet > getAllProfileSets() {
        return this.profileSetRepository.findAll();
    }

    @Transactional
    public ProfileSet getProfileSet(long id) {
        return this.profileSetRepository.getProfileSetById(id);
    }

    public String generateInsertSqlScript(ProfileSetDTO dto) {
        String Insert = "INSERT INTO ProfileSet (createdBy,creationDate,description,lastUpdateBy,name,updateState,fk_id_subIssuer) VALUES ('"+ dto.getCreatedBy() +"', '"+dto.getCreationDate()+"', '"+ dto.getDescription()+"', '"+dto.getLastUpdateBy()+"', '"+dto.getName()+"','"+dto.getUpdateState()+"', '"+dto.getSubissuer()+"');";
        String path = FILE_PATH + DATA_FILE_NAME;
        return this.storeQueryInFile(path, Insert);
    }

    public String generateInsertSqlScriptWithRollback(ProfileSetDTO dto) {
        String delete = "DELETE FROM ProfileSet WHERE name ='" + dto.getName() + "';";
        String path = FILE_PATH + DATA_ROLLBACK_FILE_NAME;
        return this.storeQueryInFile(path, delete);
    }

    public String generateEditSqlScript(ProfileSetDTO dto, String id) {
        String sql = "UPDATE ProfileSet SET " + "createdBy='" + dto.getCreatedBy() + "', " + "creationDate='" + dto.getCreationDate() + "', description='"+ dto.getDescription() +"', lastUpdateBy='" + dto.getLastUpdateBy() + "' ," + "name='" + dto.getName() + "', " + "updateState='" + dto.getUpdateState() + "' " + " WHERE id='" + id + "';";
        String path = FILE_PATH + DATA_FILE_NAME;
        return this.storeQueryInFile(path, sql);
    }

    public String generateEditSqlScriptWithRollback(String id) {
        ProfileSet profileSet = this.profileSetRepository.getProfileSetById(Long.parseLong(id));
        String sql = "UPDATE ProfileSet SET " + "createdBy='" + profileSet.getCreatedBy() + "', " + "creationDate='" + profileSet.getCreationDate() + "', description='"+ profileSet.getDescription() +"', lastUpdateBy='" + profileSet.getLastUpdateBy() + "' ," + "name='" + profileSet.getName() + "', " + "updateState='" + profileSet.getUpdateState() + "' " + " WHERE id='" + id + "';";
        String path = FILE_PATH + DATA_ROLLBACK_FILE_NAME;
        return this.storeQueryInFile(path, sql);
    }

    public String generateDeleteSqlScript(String id) {
        String sql = "SET foreign_key_checks = 0; " +
                "DELETE FROM ProfileSet  WHERE id='" + id + "'; " +
                "SET foreign_key_checks = 1;";
        String path = FILE_PATH + DATA_FILE_NAME;
        return this.storeQueryInFile(path, sql);
    }

    public String generateDeleteSqlScriptWithRollback(String id) {
        ProfileSet profileSet = this.profileSetRepository.getProfileSetById(Long.parseLong(id));
        String sql = "INSERT INTO ProfileSet (createdBy,creationDate,description,lastUpdateBy,name,updateState,fk_id_subIssuer) VALUES ('"+ profileSet.getCreatedBy() +"', '"+profileSet.getCreationDate()+"', '"+ profileSet.getDescription()+"', '"+profileSet.getLastUpdateBy()+"', '"+profileSet.getName()+"','"+profileSet.getUpdateState()+"','"+profileSet.getSubIssuer().getId()+"');";
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
