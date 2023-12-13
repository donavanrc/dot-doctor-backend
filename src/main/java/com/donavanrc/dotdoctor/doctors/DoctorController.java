package com.donavanrc.dotdoctor.doctors;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid DoctorCreateDTO doctor) {
        repository.save(new Doctor(doctor));
    }

    @GetMapping
    public Page<DoctorListDTO> list(@PageableDefault(size=10, sort = {"name"}) Pageable pagination) {
        return repository.findAllByActiveTrue(pagination).map(DoctorListDTO::new);
    }

    @PutMapping("/{id}")
    @Transactional
    public void update(@PathVariable Long id, @RequestBody @Valid DoctorUpdateDTO doctor){
        var doc = repository.getReferenceById(id);
        doc.update(doctor);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        var doc = repository.getReferenceById(id);
        doc.deactivate();
        //repository.deleteById(id);
    }
}
