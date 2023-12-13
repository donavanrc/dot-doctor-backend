package com.donavanrc.dotdoctor.doctors;

import com.donavanrc.dotdoctor.locations.LocationDTO;

public record DoctorUpdateDTO(
        String name,
        String phone,
        LocationDTO location) {
}
