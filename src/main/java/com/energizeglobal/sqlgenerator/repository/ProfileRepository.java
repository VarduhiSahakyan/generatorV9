package com.energizeglobal.sqlgenerator.repository;

import com.energizeglobal.sqlgenerator.domain.Issuer;
import com.energizeglobal.sqlgenerator.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile,Long> {

    List<Profile> findAll();
    @Query("SELECT p FROM Profile p WHERE p.id = ?1")
    Profile getProfileById(long id);

    @Query("SELECT p FROM Profile p WHERE p.name = ?1")
    Profile getProfileByName(String name);

}
