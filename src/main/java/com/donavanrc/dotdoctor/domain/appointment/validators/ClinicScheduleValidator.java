package com.donavanrc.dotdoctor.domain.appointment.validators;

import com.donavanrc.dotdoctor.domain.CustomValidatorException;
import com.donavanrc.dotdoctor.domain.appointment.AppointmentConstants;
import com.donavanrc.dotdoctor.domain.appointment.AppointmentCreateDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ClinicScheduleValidator implements IAppointmentValidator {
    public void validate(AppointmentCreateDTO appointmentData){
        var dateTime = appointmentData.dateTime();
        var isSunday = dateTime.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var isBeforeBusinessHour = dateTime.getHour() < AppointmentConstants.START_BUSINESS_HOUR;
        var isAfterBusinessHour = dateTime.getHour() > AppointmentConstants.getLastAppointmentHour();
        if(isSunday || isBeforeBusinessHour || isAfterBusinessHour){
            throw new CustomValidatorException("Date/time outside the clinic's business hours");
        }
    }
}
