package com.donavanrc.dotdoctor.domain.patient;

import com.donavanrc.dotdoctor.domain.location.LocationDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientCreateDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @NotNull
        @Valid
        LocationDTO location) {
}
