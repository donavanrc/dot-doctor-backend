package com.donavanrc.dotdoctor.domain.patient;

import com.donavanrc.dotdoctor.domain.location.LocationDTO;

public record PatientReadDTO(Long id, String name, String email, String phone, LocationDTO location) {
    public PatientReadDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), new LocationDTO(patient.getLocation()));
    }
}
