package com.donavanrc.dotdoctor.domain.appointment.validators;

import com.donavanrc.dotdoctor.domain.CustomValidatorException;
import com.donavanrc.dotdoctor.domain.appointment.AppointmentConstants;
import com.donavanrc.dotdoctor.domain.appointment.AppointmentCreateDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AdvanceSchedulingValidator implements IAppointmentValidator {
    public void validate(AppointmentCreateDTO appointmentData){
        var dateTime = appointmentData.dateTime();
        var now = LocalDateTime.now();
        var minAdvanceTime = now.plusMinutes(AppointmentConstants.MIN_ADVANCE_MINUTES);
        var isAfter = dateTime.isAfter(minAdvanceTime);
        if(!isAfter){
            throw new CustomValidatorException("Appointment must be scheduled at least 30 minutes in advance");
        }
    }
}
