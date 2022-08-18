package com.energizeglobal.sqlgenerator.repository;

import com.energizeglobal.sqlgenerator.domain.SubIssuer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubIssuerRepository extends JpaRepository<SubIssuer, Long> {

    SubIssuer findByCode(String code);

    void deleteByCode(String code);

}
