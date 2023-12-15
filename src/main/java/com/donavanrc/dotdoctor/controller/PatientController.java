package com.donavanrc.dotdoctor.controller;

import com.donavanrc.dotdoctor.domain.patient.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/patient")
@SecurityRequirement(name = "bearer-key")
public class PatientController {
    @Autowired
    private PatientRepository repository;
    public ResponseEntity<PatientReadDTO> create(@RequestBody @Valid PatientCreateDTO body, UriComponentsBuilder uriBuilder) {
        Patient patient = new Patient(body);
        repository.save(patient);
        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientReadDTO(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListDTO>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){
        var page = repository.findAllByActiveTrue(pagination).map(PatientListDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientReadDTO> read(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientReadDTO(patient));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PatientReadDTO> update(@PathVariable Long id, @RequestBody @Valid PatientUpdateDTO body) {
        var patient = repository.getReferenceById(id);
        patient.update(body);
        return ResponseEntity.ok(new PatientReadDTO(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.deactivate();
        return ResponseEntity.noContent().build();
    }
}
