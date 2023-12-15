package com.donavanrc.dotdoctor.domain.appointment;

import com.donavanrc.dotdoctor.domain.CustomValidatorException;
import com.donavanrc.dotdoctor.domain.appointment.validators.IAppointmentValidator;
import com.donavanrc.dotdoctor.domain.doctor.Doctor;
import com.donavanrc.dotdoctor.domain.doctor.DoctorRepository;
import com.donavanrc.dotdoctor.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    List<IAppointmentValidator> validators;

    public AppointmentReadDTO schedule(AppointmentCreateDTO appointmentData) {
        if(!patientRepository.existsById(appointmentData.patientId())){
            throw new CustomValidatorException("Patient id not found");
        }
        if(appointmentData.doctorId() != null && !doctorRepository.existsById(appointmentData.doctorId())){
            throw new CustomValidatorException("Doctor id not found");
        }

        validators.forEach(validator -> validator.validate(appointmentData));

        var doctor = selectDoctor(appointmentData);
        if(doctor == null) {
            throw new CustomValidatorException("There is no doctor available at that date/time");
        }

        var patient = patientRepository.getReferenceById(appointmentData.patientId());
        var appointment = new Appointment(null, doctor, patient, appointmentData.dateTime());
        appointmentRepository.save(appointment);

        return new AppointmentReadDTO(appointment);
    }

    private Doctor selectDoctor(AppointmentCreateDTO appointmentData) {
        if(appointmentData.doctorId() != null){
            return doctorRepository.getReferenceById(appointmentData.doctorId());
        }
        if(appointmentData.specialty() == null){
            throw new CustomValidatorException("The specialty is necessary if the doctor is not indicated");
        }
        return doctorRepository.selectAvailableDoctorRandomly(appointmentData.specialty(), appointmentData.dateTime());
    }
}
