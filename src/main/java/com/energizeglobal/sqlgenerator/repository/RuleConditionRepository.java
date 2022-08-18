package com.energizeglobal.sqlgenerator.repository;

import com.energizeglobal.sqlgenerator.domain.RuleCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleConditionRepository extends JpaRepository<RuleCondition, Long> {
}
