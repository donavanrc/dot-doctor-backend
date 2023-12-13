package com.donavanrc.dotdoctor.domain.doctor;

import com.donavanrc.dotdoctor.domain.location.LocationDTO;

public record DoctorUpdateDTO(
        String name,
        String phone,
        LocationDTO location) {
}
