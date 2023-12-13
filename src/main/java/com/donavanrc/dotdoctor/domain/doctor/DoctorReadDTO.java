package com.donavanrc.dotdoctor.domain.doctor;

import com.donavanrc.dotdoctor.domain.location.LocationDTO;

public record DoctorReadDTO(Long id, String name, String email, String phone, String crm,
                            MedicalSpecialtyEnum specialty, LocationDTO location) {
    public DoctorReadDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(),
                doctor.getSpecialty(), new LocationDTO(doctor.getLocation()));
    }
}
