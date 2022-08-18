package com.energizeglobal.sqlgenerator.repository;

import com.energizeglobal.sqlgenerator.domain.CryptoConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoConfigRepository extends JpaRepository<CryptoConfigurationEntity, Long> {

    @Query(value = "SELECT coalesce(max(id), 0) FROM CryptoConfigurationEntity")
    Long getMaxId();
}
