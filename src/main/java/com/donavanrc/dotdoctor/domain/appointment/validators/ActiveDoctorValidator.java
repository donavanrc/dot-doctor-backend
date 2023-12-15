package com.donavanrc.dotdoctor.domain.appointment.validators;

import com.donavanrc.dotdoctor.domain.CustomValidatorException;
import com.donavanrc.dotdoctor.domain.appointment.AppointmentCreateDTO;
import com.donavanrc.dotdoctor.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidator implements IAppointmentValidator {
    @Autowired
    private DoctorRepository repository;

    public void validate(AppointmentCreateDTO appointmentData){
        var doctorId = appointmentData.doctorId();
        if(doctorId == null) {
            return;
        }
        var doctorIsActive = repository.findActiveById(doctorId);
        if(!doctorIsActive){
            throw new CustomValidatorException("Inactive doctor cannot receive appointments");
        }
    }
}
