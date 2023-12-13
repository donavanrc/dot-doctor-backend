package com.donavanrc.dotdoctor.domain.doctor;

import com.donavanrc.dotdoctor.domain.location.LocationDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorCreateDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}", message = "Invalid CRM")
        String crm,
        @NotNull
        MedicalSpecialtyEnum specialty,
        @NotNull
        @Valid
        LocationDTO location) {
}
