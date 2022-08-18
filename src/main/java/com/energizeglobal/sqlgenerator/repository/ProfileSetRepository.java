package com.energizeglobal.sqlgenerator.repository;

import com.energizeglobal.sqlgenerator.domain.ProfileSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileSetRepository extends JpaRepository<ProfileSet,Long> {

    @Query("SELECT p FROM ProfileSet p WHERE p.id=?1")
    ProfileSet getProfileSetById(long id);
}
