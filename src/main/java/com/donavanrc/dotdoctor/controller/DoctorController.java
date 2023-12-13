package com.donavanrc.dotdoctor.controller;

import com.donavanrc.dotdoctor.domain.doctor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorReadDTO> create(@RequestBody @Valid DoctorCreateDTO body, UriComponentsBuilder uriBuilder) {
        var doctor = new Doctor(body);
        repository.save(doctor);
        var uri = uriBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorReadDTO(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListDTO>> list(@PageableDefault(size=10, sort = {"name"}) Pageable pagination) {
        var page = repository.findAllByActiveTrue(pagination).map(DoctorListDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DoctorReadDTO> read(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorReadDTO(doctor));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DoctorReadDTO> update(@PathVariable Long id, @RequestBody @Valid DoctorUpdateDTO body){
        var doctor = repository.getReferenceById(id);
        doctor.update(body);
        return ResponseEntity.ok(new DoctorReadDTO(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        doctor.deactivate();
        return ResponseEntity.noContent().build();
        //repository.deleteById(id);
    }
}
