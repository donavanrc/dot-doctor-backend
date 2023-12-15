package com.donavanrc.dotdoctor.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Boolean existsByDoctorIdAndDateTime(Long doctorId, LocalDateTime dateTime);

    Boolean existsByPatientIdAndDateTimeBetween(Long patientId, LocalDateTime firstHour, LocalDateTime lastHour);
}
