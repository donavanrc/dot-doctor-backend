package com.donavanrc.dotdoctor.domain.appointment;

public class AppointmentConstants {
    public static final int START_BUSINESS_HOUR = 7;
    public static final int END_BUSINESS_HOUR = 19;
    public static final int APPOINTMENT_DURATION = 1;
    public static final int MIN_ADVANCE_MINUTES = 30;
    public static int getLastAppointmentHour() {
        return END_BUSINESS_HOUR - APPOINTMENT_DURATION;
    }
}
