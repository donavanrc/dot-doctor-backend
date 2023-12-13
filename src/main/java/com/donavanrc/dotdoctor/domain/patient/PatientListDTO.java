package com.donavanrc.dotdoctor.domain.patient;

public record PatientListDTO(Long id, String name, String email, String phone) {
    public PatientListDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone());
    }
}
