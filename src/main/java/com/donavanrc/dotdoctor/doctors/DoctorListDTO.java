package com.donavanrc.dotdoctor.doctors;

public record DoctorListDTO(Long id, String name, String email, String crm, MedicalSpecialtyEnum specialty) {

    public DoctorListDTO(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
