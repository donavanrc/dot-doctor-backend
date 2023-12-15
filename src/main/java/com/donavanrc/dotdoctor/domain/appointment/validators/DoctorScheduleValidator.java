package com.donavanrc.dotdoctor.domain.appointment.validators;

import com.donavanrc.dotdoctor.domain.CustomValidatorException;
import com.donavanrc.dotdoctor.domain.appointment.AppointmentCreateDTO;
import com.donavanrc.dotdoctor.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorScheduleValidator implements IAppointmentValidator {
    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentCreateDTO appointmentData){
        var doctorId = appointmentData.doctorId();
        var dateTime = appointmentData.dateTime();
        var unavailable = repository.existsByDoctorIdAndDateTime(doctorId, dateTime);
        if(unavailable){
            throw new CustomValidatorException("Doctor unavailable for this date/time");
        }
    }
}
