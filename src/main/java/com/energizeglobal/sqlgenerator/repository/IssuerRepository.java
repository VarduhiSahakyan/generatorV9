package com.energizeglobal.sqlgenerator.repository;

import com.energizeglobal.sqlgenerator.domain.Issuer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuerRepository extends JpaRepository<Issuer, Long> {

    @Query("SELECT i FROM Issuer i WHERE i.code = ?1")
    Issuer getIssuerByCode(String code);
}
