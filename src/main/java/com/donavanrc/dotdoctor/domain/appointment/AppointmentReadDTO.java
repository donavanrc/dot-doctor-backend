package com.donavanrc.dotdoctor.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentReadDTO(Long id, Long doctorId, Long patientId, LocalDateTime dateTime) {
    public AppointmentReadDTO(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDateTime());
    }
}
