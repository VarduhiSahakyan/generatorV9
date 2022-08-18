package com.energizeglobal.sqlgenerator.mapper;

import com.energizeglobal.sqlgenerator.domain.ImageEntity;
import com.energizeglobal.sqlgenerator.dto.ImageDTO;

import java.time.Instant;

public class ImageMapper {

    public static ImageEntity dtoToEntity(ImageDTO imageDto) {
        ImageEntity imageEntity = new ImageEntity();

        imageEntity.setId(imageDto.getId());
        imageEntity.setCreatedBy(imageDto.getCreatedBy());
        imageEntity.setCreationDate(imageDto.getCreationDate());
        imageEntity.setDescription(imageDto.getDescription());
        imageEntity.setLastUpdateBy(imageDto.getLastUpdateBy());
        imageEntity.setLastUpdateDate(imageDto.getLastUpdateDate());
        imageEntity.setName(imageDto.getName());
        imageEntity.setUpdateState(imageDto.getUpdateState());
        imageEntity.setBinaryData(imageDto.getBinaryData());
        imageEntity.setRelativePath(imageDto.getRelativePath());

        return imageEntity;

    }

    public static ImageDTO entityToDto(ImageEntity imageEntity) {
        ImageDTO imageDto = new ImageDTO();

        imageDto.setId(imageEntity.getId());
        imageDto.setCreatedBy(imageEntity.getCreatedBy());
        imageDto.setCreationDate(imageEntity.getCreationDate());
        imageDto.setDescription(imageEntity.getDescription());
        imageDto.setLastUpdateBy(imageEntity.getLastUpdateBy());
        imageDto.setLastUpdateDate(imageEntity.getLastUpdateDate());
        imageDto.setName(imageEntity.getName());
        imageDto.setUpdateState(imageEntity.getUpdateState());
        imageDto.setBinaryData(imageEntity.getBinaryData());
        imageDto.setRelativePath(imageEntity.getRelativePath());

        return imageDto;
    }


}
