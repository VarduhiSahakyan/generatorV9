package com.energizeglobal.sqlgenerator.repository;

import com.energizeglobal.sqlgenerator.domain.AuthentMeansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthentMeansRepository extends JpaRepository<AuthentMeansEntity, Long> {

    @Query(value = "SELECT coalesce(max(id), 0) FROM AuthentMeansEntity")
    Long getMaxId();
}
