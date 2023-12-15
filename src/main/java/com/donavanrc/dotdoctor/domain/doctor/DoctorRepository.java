package com.donavanrc.dotdoctor.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pagination);

    @Query("""
            SELECT d FROM Doctor d
            WHERE
                d.active = true
            AND
                d.specialty = :specialty
            AND
                d.id NOT in(
                    SELECT a.doctor.id FROM Appointment a 
                    WHERE 
                    a.dateTime = :dateTime
                )
            ORDER BY rand()
            LIMIT 1
            """)
    Doctor selectAvailableDoctorRandomly(MedicalSpecialtyEnum specialty, LocalDateTime dateTime);

    @Query("SELECT d.active FROM Doctor d WHERE d.id = :doctorId")
    Boolean findActiveById(Long doctorId);
}
