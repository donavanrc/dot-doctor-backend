package com.donavanrc.dotdoctor.controller;

import com.donavanrc.dotdoctor.domain.appointment.AppointmentCreateDTO;
import com.donavanrc.dotdoctor.domain.appointment.AppointmentReadDTO;
import com.donavanrc.dotdoctor.domain.appointment.AppointmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {
    @Autowired
    private AppointmentService service;
    @PostMapping
    @Transactional
    public ResponseEntity<Object> schedule(@RequestBody @Valid AppointmentCreateDTO appointmentData){
        var appointmentReadDTO = service.schedule(appointmentData);
        return ResponseEntity.ok(appointmentReadDTO);
    }
}
