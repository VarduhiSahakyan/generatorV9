package com.energizeglobal.sqlgenerator.service;

import com.energizeglobal.sqlgenerator.domain.AuthentMeansEntity;
import com.energizeglobal.sqlgenerator.dto.AuthentMeansDTO;
import com.energizeglobal.sqlgenerator.mapper.Mapping;
import com.energizeglobal.sqlgenerator.repository.AuthentMeansRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AuthentMeansService {

    public Logger logger = LoggerFactory.getLogger(AuthentMeansService.class);

    static boolean activeDB = false;

    private final String INSERT_SQL_FILE_NAME = "authent.sql";

    private final String ROLLBACK_SQL_FILE_NAME = "authent_rollback.sql";

    private final Mapping mappingAuthentMean;

    private final AuthentMeansRepository authentMeansRepository;

    private final GenerateSqlScriptService generateSqlScriptService;

    private final DownloadFileService downloadFileService;

    private Timestamp thisMomentTime = new Timestamp(System.currentTimeMillis());

    public AuthentMeansService(Mapping mappingAuthentMean,
                               AuthentMeansRepository authentMeansRepository,
                               GenerateSqlScriptService generateSqlScriptService,
                               DownloadFileService downloadFileService) {
        this.mappingAuthentMean = mappingAuthentMean;
        this.authentMeansRepository = authentMeansRepository;
        this.generateSqlScriptService = generateSqlScriptService;
        this.downloadFileService = downloadFileService;
    }

    @Transactional(readOnly = true)
    public List<AuthentMeansDTO> getAllAuthentMeans() {
        List<AuthentMeansEntity> authentMeansEntityList = authentMeansRepository.findAll();
        return mappingAuthentMean.mapList(authentMeansEntityList, AuthentMeansDTO.class);
    }

    @Transactional(readOnly = true)
    public AuthentMeansDTO getByIdAuthentMean(Long id) {
        AuthentMeansEntity authentMeansEntity = authentMeansRepository.getById(id);
        return mappingAuthentMean.convertToDto(authentMeansEntity, AuthentMeansDTO.class);
    }

    @Transactional
    public String deleteAuthentMeanById(Long id) {
        AuthentMeansEntity authentMeansEntity = authentMeansRepository.getById(id);

        String deleteQuery = "START TRANSACTION; \n" +
                "SET FOREIGN_KEY_CHECKS = 0; \n" +
                "DELETE FROM AuthentMeans WHERE id = '" + id + "';\n" +
                "SET FOREIGN_KEY_CHECKS = 1; \n" +
                "COMMIT;";

        String rollbackQuery = String.format("INSERT INTO `AuthentMeans` (createdBy, creationDate, description, lastUpdateBy, " +
                        "lastUpdateDate, name, updateState) VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                authentMeansEntity.getCreatedBy(),
                authentMeansEntity.getCreationDate(),
                authentMeansEntity.getDescription(),
                authentMeansEntity.getLastUpdateBy(),
                thisMomentTime,
                authentMeansEntity.getName(),
                authentMeansEntity.getUpdateState());

        generateSqlScriptService.insertSqlScript(deleteQuery, INSERT_SQL_FILE_NAME);
        generateSqlScriptService.insertSqlScript(rollbackQuery, ROLLBACK_SQL_FILE_NAME);
        if (activeDB) {
            authentMeansRepository.deleteById(id);
        }
        return INSERT_SQL_FILE_NAME;
    }

    @Transactional
    public String updateAuthentMean(AuthentMeansDTO authentMeansDTO) {

        AuthentMeansEntity authentMeansEntity = mappingAuthentMean.convertToEntity(authentMeansDTO, AuthentMeansEntity.class);
        authentMeansEntity.setLastUpdateDate(thisMomentTime);
        String updateQuery = String.format("UPDATE `AuthentMeans` SET createdBy='%s', creationDate='%s', description='%s'," +
                        " lastUpdateBy='%s', lastUpdateDate='%s', name='%s', updateState='%s' WHERE id='%s';",
                authentMeansEntity.getCreatedBy(),
                authentMeansEntity.getCreationDate(),
                authentMeansEntity.getDescription(),
                authentMeansEntity.getLastUpdateBy(),
                thisMomentTime,
                authentMeansEntity.getName(),
                authentMeansEntity.getUpdateState(),
                authentMeansEntity.getId());

        AuthentMeansEntity authentMeansEntityRollback = authentMeansRepository.getById(authentMeansDTO.getId());
        String rollbackUpdateQuery = String.format("UPDATE `AuthentMeans` SET createdBy='%s', creationDate='%s', description='%s'," +
                        " lastUpdateBy='%s', lastUpdateDate='%s', name='%s', updateState='%s' WHERE id='%s';",
                authentMeansEntityRollback.getCreatedBy(),
                authentMeansEntityRollback.getCreationDate(),
                authentMeansEntityRollback.getDescription(),
                authentMeansEntityRollback.getLastUpdateBy(),
                thisMomentTime,
                authentMeansEntityRollback.getName(),
                authentMeansEntityRollback.getUpdateState(),
                authentMeansEntityRollback.getId());


        generateSqlScriptService.insertSqlScript(updateQuery, INSERT_SQL_FILE_NAME);
        generateSqlScriptService.insertSqlScript(rollbackUpdateQuery, ROLLBACK_SQL_FILE_NAME);
        if (activeDB) {
            authentMeansRepository.save(authentMeansEntity);
        }
        return INSERT_SQL_FILE_NAME;
    }

    @Transactional
    public String saveAuthentMean(AuthentMeansDTO authentMeansDTO) {

        Long lastId = authentMeansRepository.getMaxId() + 1;
        AuthentMeansEntity authentMeansEntity = mappingAuthentMean
                .convertToEntity(authentMeansDTO, AuthentMeansEntity.class);
        if (authentMeansEntity.getCreationDate() == null) {
            authentMeansEntity.setCreationDate(thisMomentTime);
        }
        if (authentMeansEntity.getId() == null) {
            authentMeansEntity.setId(lastId);
        }

        String insertQuery = String.format("INSERT INTO `AuthentMeans` (createdBy, creationDate, description, lastUpdateBy, " +
                        "lastUpdateDate, name, updateState) VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                authentMeansEntity.getCreatedBy(),
                thisMomentTime,
                authentMeansEntity.getDescription(),
                authentMeansEntity.getLastUpdateBy(),
                thisMomentTime,
                authentMeansEntity.getName(),
                authentMeansEntity.getUpdateState());


        String deleteQuery = "START TRANSACTION; \n" +
                "SET FOREIGN_KEY_CHECKS = 0; \n" +
                "DELETE FROM AuthentMeans WHERE id = '" + authentMeansEntity.getId() + "';\n" +
                "SET FOREIGN_KEY_CHECKS = 1; \n" +
                "COMMIT;";

        generateSqlScriptService.insertSqlScript(insertQuery, INSERT_SQL_FILE_NAME);
        generateSqlScriptService.insertSqlScript(deleteQuery, ROLLBACK_SQL_FILE_NAME);

        if (activeDB) {
            authentMeansRepository.save(authentMeansEntity);
        }
        return INSERT_SQL_FILE_NAME;
    }

    public Resource getDownloadFile(String FILE_NAME) {
        String FILE_PATH = "src/main/resources/sql_scripts/";
        return downloadFileService.downloadFile(FILE_NAME, FILE_PATH);
    }
}
