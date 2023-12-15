package com.donavanrc.dotdoctor.domain.appointment;

import com.donavanrc.dotdoctor.domain.doctor.MedicalSpecialtyEnum;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentCreateDTO(
        Long doctorId,
        @NotNull
        Long patientId,
        @NotNull
        @Future
        LocalDateTime dateTime,

        MedicalSpecialtyEnum specialty) {
}
