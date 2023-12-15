package com.donavanrc.dotdoctor.domain.appointment.validators;

import com.donavanrc.dotdoctor.domain.CustomValidatorException;
import com.donavanrc.dotdoctor.domain.appointment.AppointmentCreateDTO;
import com.donavanrc.dotdoctor.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements IAppointmentValidator {
    @Autowired
    private PatientRepository repository;

    public void validate(AppointmentCreateDTO appointmentData){
        var patientId = appointmentData.patientId();
        var patientIsActive = repository.findActiveById(patientId);
        if(!patientIsActive){
            throw new CustomValidatorException("Inactive patient cannot receive appointments");
        }
    }
}
