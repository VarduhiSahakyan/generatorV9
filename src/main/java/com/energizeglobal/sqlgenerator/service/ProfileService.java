package com.energizeglobal.sqlgenerator.service;

import com.energizeglobal.sqlgenerator.domain.AuthentMeansEntity;
import com.energizeglobal.sqlgenerator.domain.Profile;
import com.energizeglobal.sqlgenerator.domain.SubIssuer;
import com.energizeglobal.sqlgenerator.dto.ProfileDTO;
import com.energizeglobal.sqlgenerator.repository.AuthentMeansRepository;
import com.energizeglobal.sqlgenerator.repository.ProfileRepository;
import com.energizeglobal.sqlgenerator.repository.SubIssuerRepository;
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
public class ProfileService {

    private final String FILE_PATH = "src/main/resources/sql_scripts/";
    private final String DATA_FILE_NAME = "profile_data.sql";
    private final String DATA_ROLLBACK_FILE_NAME = "profile_rollback_data.sql";

    private ProfileRepository repository;
    private AuthentMeansRepository authentMeansRepository;
    private SubIssuerRepository subIssuerRepository;

    public ProfileService(
            ProfileRepository repository,
            AuthentMeansRepository authentMeansRepository,
            SubIssuerRepository subIssuerRepository
            ) {
        this.repository = repository;
        this.authentMeansRepository = authentMeansRepository;
        this.subIssuerRepository = subIssuerRepository;
    }

    @Transactional(readOnly = true)
    public List<Profile> getAllProfiles(){
        List<Profile> profiles = this.repository.findAll();
        return profiles;
    }

    public Profile findByProfileById(long id) {
        Profile profile = this.repository.getProfileById(id);
        return profile;
    }

    public String generateInsertSqlScript(ProfileDTO dto) {
        String Insert = "INSERT INTO Profile (createdBy,creationDate,description,lastUpdateBy,name,updateState,maxAttempts,dataEntryFormat,dataEntryAllowedPattern,fk_id_authentMeans,fk_id_subIssuer) VALUES ('"+ dto.getCreatedBy() +"', '"+dto.getCreationDate()+"','"+ dto.getDescription() +"', '"+ dto.getLastUpdateBy()+"', '"+dto.getName()+"','"+dto.getUpdateState()+"','"+ dto.getMaxAttempts()+"','"+dto.getDataEntryFormat()+"','"+ dto.getDataEntryAllowedPattern()+"', '"+dto.getAuthentMeans()+"', '"+dto.getSubissuer()+"');";
        String path = FILE_PATH + DATA_FILE_NAME;
        return this.storeQueryInFile(path, Insert);
    }

    public String generateInsertSqlScriptWithRollback(ProfileDTO dto) {
        String delete = "DELETE FROM Profile WHERE name ='" + dto.getName() + "';";
        String path = FILE_PATH + DATA_ROLLBACK_FILE_NAME;
        return this.storeQueryInFile(path, delete);
    }

    public String generateEditSqlScript(ProfileDTO dto, long id) {
        String sql = "UPDATE Profile SET createdBy='"+ dto.getCreatedBy() +"',creationDate='"+ dto.getCreationDate() +"',description='"+ dto.getDescription() +"',lastUpdateBy='"+ dto.getLastUpdateBy() +"',name='"+ dto.getName() +"',updateState='"+ dto.getUpdateState() +"', maxAttempts='"+ dto.getMaxAttempts() +"', dataEntryFormat='"+ dto.getDataEntryFormat() +"', dataEntryAllowedPattern='"+ dto.getDataEntryAllowedPattern() +"' WHERE id='"+id+"' ;";
        String path = FILE_PATH + DATA_FILE_NAME;
        return this.storeQueryInFile(path, sql);
    }

    public String generateEditSqlScriptWithRollback(ProfileDTO dto, long id) {
        Profile profile = this.repository.getProfileById(id);
        String sql = "UPDATE Profile SET createdBy='"+ profile.getCreatedBy() +"'," +
                "creationDate='"+ profile.getCreationDate() +"',description='"+ profile.getDescription() +"'," +
                "lastUpdateBy='"+ profile.getLastUpdateBy() +"',name='"+ profile.getName() +"'," +
                "updateState='"+ profile.getUpdateState() +"', maxAttempts='"+ profile.getMaxAttempts() +"', " +
                "dataEntryFormat='"+ profile.getDataEntryFormat() +"', " +
                "dataEntryAllowedPattern='"+ profile.getDataEntryAllowedPattern() +"'  WHERE id='"+id+"' ;";
        String path = FILE_PATH + DATA_ROLLBACK_FILE_NAME;
        return this.storeQueryInFile(path, sql);
    }

    public String generateDeleteSqlScript(long id) {
        String sql = "SET foreign_key_checks = 0; " +
                "DELETE FROM Profile WHERE id ='" + id + "'; " +
                "SET foreign_key_checks = 1;";
        String path = FILE_PATH + DATA_FILE_NAME;
        return this.storeQueryInFile(path, sql);
    }

    public String generateDeleteSqlScriptWithRollback(long id) {
        Profile profile = this.repository.getProfileById(id);
        String sql = "INSERT INTO Profile (createdBy,creationDate,description,lastUpdateBy,name,updateState,maxAttempts,dataEntryFormat,dataEntryAllowedPattern,fk_id_authentMeans,fk_id_subIssuer) VALUES ('"+ profile.getCreatedBy() +"', '"+profile.getCreationDate()+"','"+ profile.getDescription() +"', '"+ profile.getLastUpdateBy()+"', '"+profile.getName()+"','"+profile.getUpdateState()+"','"+ profile.getMaxAttempts()+"','"+profile.getDataEntryFormat()+"','"+ profile.getDataEntryAllowedPattern()+"','"+ profile.getAuthentMeans().getId() +"','"+ profile.getSubIssuer().getId() +"');";
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
