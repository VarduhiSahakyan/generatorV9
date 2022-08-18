package com.energizeglobal.sqlgenerator.service;

import com.energizeglobal.sqlgenerator.domain.CryptoConfigurationEntity;
import com.energizeglobal.sqlgenerator.dto.CryptoConfigDTO;
import com.energizeglobal.sqlgenerator.mapper.Mapping;
import com.energizeglobal.sqlgenerator.repository.CryptoConfigRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CryptoConfigService {

    static boolean activeDB = false;

    private final String INSERT_SQL_FILE_NAME = "crypto.sql";

    private final String ROLLBACK_SQL_FILE_NAME = "crypto_rollback.sql";

    private final Mapping mappingCryptoConfig;

    private final CryptoConfigRepository cryptoConfigRepository;

    private final GenerateSqlScriptService generateSqlScriptService;

    private final DownloadFileService downloadFileService;

    public CryptoConfigService(
            CryptoConfigRepository cryptoConfigRepository,
            Mapping mappingCryptoConfig,
            DownloadFileService downloadFileService,
            GenerateSqlScriptService generateSqlScriptService) {
        this.cryptoConfigRepository = cryptoConfigRepository;
        this.mappingCryptoConfig = mappingCryptoConfig;
        this.generateSqlScriptService = generateSqlScriptService;
        this.downloadFileService = downloadFileService;
    }

    @Transactional
    public List<CryptoConfigDTO> findAllCryptoConfigs() {

        List<CryptoConfigurationEntity> cryptoConfigEntities = cryptoConfigRepository.findAll();

        return mappingCryptoConfig.mapList(cryptoConfigEntities, CryptoConfigDTO.class);
    }

    @Transactional
    public CryptoConfigDTO getById(Long id) {

        CryptoConfigurationEntity cryptoConfigurationEntity = cryptoConfigRepository.getById(id);

        return mappingCryptoConfig.convertToDto(cryptoConfigurationEntity, CryptoConfigDTO.class);
    }

    @Transactional
    public String deleteById(Long id) {

        CryptoConfigurationEntity cryptoConfigurationEntity = cryptoConfigRepository.getById(id);

        String deleteQuery =
                "START TRANSACTION; \n" +
                "SET FOREIGN_KEY_CHECKS = 0; \n" +
                "DELETE FROM CryptoConfig WHERE id = " + id + ";\n" +
                "SET FOREIGN_KEY_CHECKS = 1; \n" +
                "COMMIT;";

        String rollbackQuery = String.format("INSERT INTO `CryptoConfig` (`protocolOne`, `protocolTwo`, `description`) " +
                        "VALUES('%s', '%s', '%s');",
                cryptoConfigurationEntity.getProtocolOne(),
                cryptoConfigurationEntity.getProtocolTwo(),
                cryptoConfigurationEntity.getDescription());

        generateSqlScriptService.insertSqlScript(deleteQuery, INSERT_SQL_FILE_NAME);
        generateSqlScriptService.insertSqlScript(rollbackQuery, ROLLBACK_SQL_FILE_NAME);
        if (activeDB)
            cryptoConfigRepository.deleteById(id);
        return INSERT_SQL_FILE_NAME;
    }

    @Transactional
    public String saveCryptoConfig(CryptoConfigDTO cryptoConfigDTO) {

        CryptoConfigurationEntity cryptoConfigurationEntity = mappingCryptoConfig.convertToEntity(cryptoConfigDTO, CryptoConfigurationEntity.class);

        String queryType = String.format("INSERT INTO `CryptoConfig` (`protocolOne`, `protocolTwo`, `description`) VALUES('%s', '%s', '%s');",
                cryptoConfigurationEntity.getProtocolOne(),
                cryptoConfigurationEntity.getProtocolTwo(),
                cryptoConfigurationEntity.getDescription());

        String queryTypeRollback =
                        "START TRANSACTION; \n" +
                        "SET FOREIGN_KEY_CHECKS = 0; \n" +
                        "DELETE FROM CryptoConfig WHERE description = '" + cryptoConfigDTO.getDescription() + "';\n" +
                        "SET FOREIGN_KEY_CHECKS = 1; \n" +
                        "COMMIT;";


        cryptoConfigRepository.save(cryptoConfigurationEntity);

        generateSqlScriptService.insertSqlScript(queryType, INSERT_SQL_FILE_NAME);
        generateSqlScriptService.insertSqlScript(queryTypeRollback, ROLLBACK_SQL_FILE_NAME);

        Long lastId = cryptoConfigRepository.getMaxId();

        String deleteQuery =
                "START TRANSACTION; \n" +
                "SET FOREIGN_KEY_CHECKS = 0; \n" +
                "DELETE FROM CryptoConfig WHERE id = " + lastId + ";\n" +
                "SET FOREIGN_KEY_CHECKS = 1; \n" +
                "COMMIT;";

        if (activeDB)
            generateSqlScriptService.insertSqlScript(deleteQuery, ROLLBACK_SQL_FILE_NAME);

        return INSERT_SQL_FILE_NAME;
    }

    @Transactional
    public String updateCryptoConfig(CryptoConfigDTO cryptoConfigDTO, long id) {

        CryptoConfigurationEntity cryptoConfigurationEntity = mappingCryptoConfig.convertToEntity(cryptoConfigDTO, CryptoConfigurationEntity.class);
        cryptoConfigurationEntity.setId(id);
        CryptoConfigurationEntity rollbackCryptoConfigurationEntity = cryptoConfigRepository.getById(id);


        String updateQuery = String.format("UPDATE `CryptoConfig` SET `protocolOne` = '%s', `protocolTwo` = '%s', `description` = '%s' WHERE id = '%s';",
                cryptoConfigurationEntity.getProtocolOne(),
                cryptoConfigurationEntity.getProtocolTwo(),
                cryptoConfigurationEntity.getDescription(),
                cryptoConfigurationEntity.getId());

        String rollbackQuery = String.format("UPDATE `CryptoConfig` SET `protocolOne` = '%s', `protocolTwo` = '%s', `description` = '%s' WHERE id = '%s';",
                rollbackCryptoConfigurationEntity.getProtocolOne(),
                rollbackCryptoConfigurationEntity.getProtocolTwo(),
                rollbackCryptoConfigurationEntity.getDescription(),
                rollbackCryptoConfigurationEntity.getId());

        generateSqlScriptService.insertSqlScript(updateQuery, INSERT_SQL_FILE_NAME);

        generateSqlScriptService.insertSqlScript(rollbackQuery, ROLLBACK_SQL_FILE_NAME);

        if (activeDB)
            cryptoConfigRepository.save(cryptoConfigurationEntity);

        return INSERT_SQL_FILE_NAME;
    }

    public Resource getDownloadFile(String FILE_NAME) {
        String FILE_PATH = "src/main/resources/sql_scripts/";
        return downloadFileService.downloadFile(FILE_NAME, FILE_PATH);
    }
}
