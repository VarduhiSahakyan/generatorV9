package com.energizeglobal.sqlgenerator.controller;


import com.energizeglobal.sqlgenerator.domain.Profile;
import com.energizeglobal.sqlgenerator.dto.ProfileDTO;
import com.energizeglobal.sqlgenerator.service.ProfileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Profile>> index() {
        List<Profile> profiles = this.service.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @PostMapping
    public ResponseEntity<String> generateSqlScript(@RequestBody ProfileDTO profileDTO) {
        String fileName_1 = this.service.generateInsertSqlScript(profileDTO);
        String fileName_2 = this.service.generateInsertSqlScriptWithRollback(profileDTO);
        return ResponseEntity.ok(fileName_1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> show(@PathVariable("id") long someId) {
        Profile profile = this.service.findByProfileById(someId);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> edit(@RequestBody ProfileDTO profileDTO, @PathVariable("id") long id) {
        String fileName_1 = this.service.generateEditSqlScript(profileDTO, id);
        String fileName_2 = this.service.generateEditSqlScriptWithRollback(profileDTO, id);
        return ResponseEntity.ok(fileName_1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroy(@PathVariable("id") long id) {
        String fileName_1 = this.service.generateDeleteSqlScript(id);
        String fileName_2 = this.service.generateDeleteSqlScriptWithRollback(id);
        return ResponseEntity.ok(fileName_1);
    }

    @GetMapping("/script/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

        Resource file = this.service.downloadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/sql")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFilename())
                .body(file);
    }
}
