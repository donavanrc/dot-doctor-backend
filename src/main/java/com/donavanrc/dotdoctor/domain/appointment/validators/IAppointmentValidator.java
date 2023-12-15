package com.donavanrc.dotdoctor.domain.appointment.validators;

import com.donavanrc.dotdoctor.domain.appointment.AppointmentCreateDTO;

public interface IAppointmentValidator {
    void validate(AppointmentCreateDTO appointmentData);
}
