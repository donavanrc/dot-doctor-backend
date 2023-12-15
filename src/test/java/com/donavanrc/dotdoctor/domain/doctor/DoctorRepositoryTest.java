package com.donavanrc.dotdoctor.domain.doctor;

import com.donavanrc.dotdoctor.domain.appointment.Appointment;
import com.donavanrc.dotdoctor.domain.location.LocationDTO;
import com.donavanrc.dotdoctor.domain.patient.Patient;
import com.donavanrc.dotdoctor.domain.patient.PatientCreateDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Should return null when unique doctor in database is not available in the date/time")
    void selectAvailableDoctorRandomlySample1() {
        // Given/Arrange
        var nextMonday10Am = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .toLocalDate().atTime(10, 0);

        var specialty = MedicalSpecialtyEnum.CARDIOLOGY;
        var doctor = saveDoctor("John", "john@dotdoctor.com", "123456", specialty);
        var patient = savePatient("Mary", "mary@email.com");
        saveAppointment(doctor, patient, nextMonday10Am);

        // When/Act
        var doctorAvailable = doctorRepository.selectAvailableDoctorRandomly(specialty, nextMonday10Am);

        // Then/Assert
        Assertions.assertThat(doctorAvailable).isNull();
    }

    @Test
    @DisplayName("Should return a doctor when doctor in database is available in the date/time")
    void selectAvailableDoctorRandomlySample2() {
        // Given/Arrange
        var nextMonday10Am = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .toLocalDate().atTime(10, 0);

        var specialty = MedicalSpecialtyEnum.CARDIOLOGY;
        var doctor = saveDoctor("Peter", "peter@dotdoctor.com", "123456", specialty);

        // When/Act
        var doctorAvailable = doctorRepository.selectAvailableDoctorRandomly(specialty, nextMonday10Am);
        // Then/Assert
        Assertions.assertThat(doctorAvailable).isEqualTo(doctor);
    }

    private void saveAppointment(Doctor doctor, Patient patient, LocalDateTime dateTime) {
        entityManager.persist(new Appointment(null, doctor, patient, dateTime));
    }

    private Doctor saveDoctor(String name, String email, String crm, MedicalSpecialtyEnum specialty) {
        var doctor = new Doctor(getDoctorData(name, email, crm, specialty));
        entityManager.persist(doctor);
        return doctor;
    }

    private Patient savePatient(String name, String email) {
        var patient = new Patient(getPatientData(name, email));
        entityManager.persist(patient);
        return patient;
    }

    private DoctorCreateDTO getDoctorData(String name, String email, String crm, MedicalSpecialtyEnum specialty) {
        return new DoctorCreateDTO(name, email, "1199996666", crm, specialty, getLocationData());
    }

    private PatientCreateDTO getPatientData(String name, String email) {
        return new PatientCreateDTO(name, email, "1199996666", getLocationData());
    }

    private LocationDTO getLocationData() {
        return new LocationDTO(
                "Rua primeira",
                "999",
                "Casa 2",
                "09988123",
                "Jardim Garden",
                "São Paulo",
                "SP",
                "Brasil");
    }
}