package com.donavanrc.dotdoctor.domain.appointment.validators;

import com.donavanrc.dotdoctor.domain.CustomValidatorException;
import com.donavanrc.dotdoctor.domain.appointment.AppointmentConstants;
import com.donavanrc.dotdoctor.domain.appointment.AppointmentCreateDTO;
import com.donavanrc.dotdoctor.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientDailyScheduleValidator implements IAppointmentValidator {
    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentCreateDTO appointmentData){
        var patientId = appointmentData.patientId();
        var dateTime = appointmentData.dateTime();
        var firstHour = dateTime.withHour(AppointmentConstants.START_BUSINESS_HOUR);
        var lastHour = dateTime.withHour(AppointmentConstants.getLastAppointmentHour());
        var hasAppointmentOnThisDay = repository.existsByPatientIdAndDateTimeBetween(patientId, firstHour, lastHour);
        if(hasAppointmentOnThisDay){
            throw new CustomValidatorException("The patient already has an appointment scheduled for that day");
        }
    }
}
