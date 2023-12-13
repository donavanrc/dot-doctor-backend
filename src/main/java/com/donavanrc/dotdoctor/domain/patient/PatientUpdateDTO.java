package com.donavanrc.dotdoctor.domain.patient;

import com.donavanrc.dotdoctor.domain.location.LocationDTO;

public record PatientUpdateDTO(
        String name,
        String phone,
        LocationDTO location) {
}
