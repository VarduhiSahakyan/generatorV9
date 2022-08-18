package com.energizeglobal.sqlgenerator.service;

import com.energizeglobal.sqlgenerator.domain.ImageEntity;
import com.energizeglobal.sqlgenerator.dto.ImageDTO;
import com.energizeglobal.sqlgenerator.mapper.ImageMapper;
import com.energizeglobal.sqlgenerator.repository.ImageRepository;
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
public class ImageService {

    private Boolean dbAction = false;
    private final ImageRepository imageRepository;
    private final ImageRollbackService imageRollbackService;

    private String FILE_PATH = "src/main/resources/sql_scripts/";
    private String INSERT_FILE_NAME = "image.sql";
    private String path = FILE_PATH + INSERT_FILE_NAME;
    private Timestamp thisMomentTime = new Timestamp(System.currentTimeMillis());


    public ImageService(ImageRepository imageRepository, ImageRollbackService imageRollbackService) {
        this.imageRepository = imageRepository;
        this.imageRollbackService = imageRollbackService;
    }

    @Transactional(readOnly = true)
    public List<ImageEntity> getAllImages() {
        List<ImageEntity> imageList = imageRepository.findAll();
        return imageList;
    }

    @Transactional(readOnly = true)
    public ImageDTO findById(Long id) {
        ImageEntity imageEntity = imageRepository.getById(id);
        return ImageMapper.entityToDto(imageEntity);
    }

    @Transactional(readOnly = true)
    public ImageDTO findByName(String name) {
        ImageEntity imageEntity = imageRepository.findByName(name);
        return ImageMapper.entityToDto(imageEntity);
    }

    @Transactional
    public String generateInsertSqlScript(ImageDTO imageDto) {
        ImageEntity image = ImageMapper.dtoToEntity(imageDto);

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
                thisMomentTime + "', '" +
                image.getDescription() + "', '" +
                image.getLastUpdateBy() + "', '" +
                image.getName() + "', '" +
                image.getUpdateState() + "', '" +
                image.getBinaryData() + "', '" +
                image.getRelativePath() + "');";

        String sqlInsert = queryType + queryValue;

        insertPathGenerator(sqlInsert);

        imageRollbackService.generateSqlScriptForInsertRollback(image.getName());

        if (dbAction)
            imageRepository.save(image);

        return INSERT_FILE_NAME;
    }

    @Transactional
    public String generateUpdateSqlScript(ImageDTO imageDto) {
        ImageEntity oldImage = imageRepository.getById(imageDto.getId());
        ImageEntity newImage = ImageMapper.dtoToEntity(imageDto);
        newImage.setLastUpdateDate(thisMomentTime);
        newImage.setId(oldImage.getId());

        String queryUpdate = "UPDATE Image SET " +
                "createdBy = '" + imageDto.getCreatedBy() + "', " +
                "creationDate = '" + imageDto.getCreationDate() + "', " +
                "description = '" + imageDto.getDescription() + "', " +
                "lastUpdateBy = '" + imageDto.getLastUpdateBy() + "', " +
                "lastUpdateDate = '" + thisMomentTime + "', " +
                "name = '" + imageDto.getName() + "', " +
                "updateState = '" + imageDto.getUpdateState() + "', " +
                "binaryData = '" + imageDto.getBinaryData() + "', " +
                "relativePath = '" + imageDto.getRelativePath() + "' " +
                " WHERE id = " + imageDto.getId() + ";";

        insertPathGenerator(queryUpdate);

        imageRollbackService.generateSqlScriptForUpdateRollback(oldImage);
        if (dbAction)
            imageRepository.save(newImage);

        return INSERT_FILE_NAME;
    }

    @Transactional
    public String generateDeleteSqlScript(String name) {

        ImageDTO image = findByName(name);

        System.out.println(image);

        String deleteQuery = "\n" +
                "START TRANSACTION; \n" +
                "SET FOREIGN_KEY_CHECKS = 0; \n" +
                "DELETE FROM Image WHERE name = '" + name + "';\n" +
                "SET FOREIGN_KEY_CHECKS = 1; \n" +
                "COMMIT;";
        insertPathGenerator(deleteQuery);

        imageRollbackService.generateSqlScriptForDeleteRollback(image);

        if (dbAction)
            imageRepository.deleteById(image.getId());

        return INSERT_FILE_NAME;
    }

    private void insertPathGenerator(String sql) {

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
