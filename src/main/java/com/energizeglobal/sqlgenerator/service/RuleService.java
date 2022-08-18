package com.energizeglobal.sqlgenerator.service;

import com.energizeglobal.sqlgenerator.domain.Profile;
import com.energizeglobal.sqlgenerator.domain.RuleEntity;
import com.energizeglobal.sqlgenerator.dto.RuleDTO;
import com.energizeglobal.sqlgenerator.mapper.RuleMapper;
import com.energizeglobal.sqlgenerator.repository.ProfileRepository;
import com.energizeglobal.sqlgenerator.repository.RuleRepository;
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
public class RuleService {

    private Boolean dbAction = false;
    private final RuleRepository ruleRepository;
    ProfileRepository profileRepository;
    RuleMapper ruleMapper;

    private String FILE_PATH = "src/main/resources/sql_scripts/";
    private String MAIN_FILE_NAME = "rule.sql";
    private String ROLLBACK_FILE_NAME = "rule_rollback.sql";
    private String mainPath = FILE_PATH + MAIN_FILE_NAME;
    private String rollbackPath = FILE_PATH + ROLLBACK_FILE_NAME;
    Timestamp thisMomentTime = new Timestamp(System.currentTimeMillis());

    public RuleService(RuleRepository ruleRepository, RuleMapper ruleMapper, ProfileRepository profileRepository) {
        this.ruleRepository = ruleRepository;
        this.ruleMapper = ruleMapper;
        this.profileRepository = profileRepository;
    }

    @Transactional(readOnly = true)
    public List<RuleEntity> getAllRules() {
        List<RuleEntity> ruleList = ruleRepository.findAll();
        return ruleList;
    }

    @Transactional(readOnly = true)
    public RuleDTO findById(Long id) {
        RuleEntity ruleEntity = ruleRepository.getById(id);
        ruleEntity.setLastUpdateDate(thisMomentTime);
        return RuleMapper.entityToDto(ruleEntity);
    }

    @Transactional
    public String generateInsertSqlScript(RuleDTO ruleDto) {

        Profile profile = profileRepository.getById(ruleDto.getProfile_id());
        RuleEntity ruleEntity = ruleMapper.dtoToEntity(ruleDto, profile);
        ruleEntity.setCreationDate(thisMomentTime);

        String queryType = "INSERT INTO Rule  ( " +
                "createdBy, " +
                "creationDate, " +
                "description, " +
                "lastUpdateBy, " +
                "lastUpdateDate, " +
                "name, " +
                "updateState, " +
                "orderRule, " +
                "fk_id_profile )";

        String queryValue = " \n" +
                "VALUES (" + " '" +
                ruleEntity.getCreatedBy() + "', '" +
                thisMomentTime + "', '" +
                ruleEntity.getDescription() + "', '" +
                ruleEntity.getLastUpdateBy() + "', '" +
                thisMomentTime + "', '" +
                ruleEntity.getName() + "', '" +
                ruleEntity.getUpdateState() + "', " +
                ruleEntity.getOrderRule() + ", " +
                ruleDto.getProfile_id() + ");";

        String sqlInsert = queryType + queryValue;

        pathGenerator(sqlInsert, mainPath);

        generateSqlScriptForInsertRollback(ruleEntity.getId());

        if (dbAction)
            ruleRepository.save(ruleEntity);

        return MAIN_FILE_NAME;
    }

    public String generateSqlScriptForInsertRollback(Long id) {

        String deleteQuery = "\n" +
                "START TRANSACTION; \n" +
                "SET FOREIGN_KEY_CHECKS = 0; \n" +
                "DELETE FROM Rule WHERE id = " + id + ";\n" +
                "SET FOREIGN_KEY_CHECKS = 1; \n" +
                "COMMIT;";

        pathGenerator(deleteQuery, rollbackPath);

        return ROLLBACK_FILE_NAME;
    }

    @Transactional
    public String generateUpdateSqlScript(RuleDTO ruleDto) {
        ruleDto.setLastUpdateDate(thisMomentTime);
        RuleEntity oldRule = ruleRepository.getById(ruleDto.getId());
        RuleEntity newRule = ruleMapper.dtoToEntity(ruleDto);

        String queryUpdate = "UPDATE Rule SET " +
                "createdBy = '" + ruleDto.getCreatedBy() + "', " +
                "creationDate = '" + ruleDto.getCreationDate() + "', " +
                "description = '" + ruleDto.getDescription() + "', " +
                "lastUpdateBy = '" + ruleDto.getLastUpdateBy() + "', " +
                "lastUpdateDate = '" + thisMomentTime + "', " +
                "name = '" + ruleDto.getName() + "', " +
                "updateState = '" + ruleDto.getUpdateState() + "', " +
                "orderRule = " + ruleDto.getOrderRule() + " " +
                " WHERE id = " + ruleDto.getId() + ";";

        pathGenerator(queryUpdate, mainPath);

        generateSqlScriptForUpdateRollback(oldRule);

        if (dbAction)
            ruleRepository.save(newRule);

        return MAIN_FILE_NAME;
    }

    public String generateSqlScriptForUpdateRollback(RuleEntity oldRule) {

        String queryUpdate = "UPDATE Rule SET " +
                "createdBy = '" + oldRule.getCreatedBy() + "', " +
                "creationDate = '" + oldRule.getCreationDate() + "', " +
                "description = '" + oldRule.getDescription() + "', " +
                "lastUpdateBy = '" + oldRule.getLastUpdateBy() + "', " +
                "lastUpdateDate = '" + thisMomentTime + "', " +
                "name = '" + oldRule.getName() + "', " +
                "updateState = '" + oldRule.getUpdateState() + "', " +
                "orderRule = " + oldRule.getOrderRule() + " " +
                " WHERE id = " + oldRule.getId() + ";";

        pathGenerator(queryUpdate, rollbackPath);

        return ROLLBACK_FILE_NAME;
    }

    @Transactional
    public String generateDeleteSqlScript(Long id, Long profile_id) {

        RuleDTO rule = findById(id);

        String deleteQuery = "\n" +
                "START TRANSACTION; \n" +
                "SET FOREIGN_KEY_CHECKS = 0; \n" +
                "DELETE FROM Rule WHERE id = " + id + ";\n" +
                "SET FOREIGN_KEY_CHECKS = 1; \n" +
                "COMMIT;";
        pathGenerator(deleteQuery, mainPath);

        generateSqlScriptForDeleteRollback(rule, profile_id);

        if (dbAction)
            ruleRepository.deleteById(id);

        return MAIN_FILE_NAME;
    }

    public String generateSqlScriptForDeleteRollback(RuleDTO rule, Long profile_id) {

        String queryType = "INSERT INTO Rule  ( " +
                "createdBy, " +
                "creationDate, " +
                "description, " +
                "lastUpdateBy, " +
                "name, " +
                "updateState, " +
                "orderRule, " +
                "fk_id_profile  )";

        String queryValue = " \n" +
                " VALUES (" + " '" +
                rule.getCreatedBy() + "', '" +
                thisMomentTime + "', '" +
                rule.getDescription() + "', '" +
                rule.getLastUpdateBy() + "', '" +
                rule.getName() + "', '" +
                rule.getUpdateState() + "', " +
                rule.getOrderRule() + ", " +
                profile_id + ");";

        String sqlInsert = queryType + queryValue;

        pathGenerator(sqlInsert, rollbackPath);

        return ROLLBACK_FILE_NAME;
    }

    private void pathGenerator(String sql, String path) {

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
            throw new RuntimeException("IOException: " + e.getMessage());
        }
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
