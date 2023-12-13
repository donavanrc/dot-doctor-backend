package com.donavanrc.dotdoctor.domain.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LocationDTO(
        @NotBlank
        String address,
        String number,
        String detail,
        @NotBlank
        @Pattern(regexp = "\\d{8}", message = "Invalid Zip code")
        String zipcode,
        @NotBlank
        String district,
        @NotBlank
        String city,
        @NotBlank
        String state,
        @NotBlank
        String country) {

    public LocationDTO(Location location) {
        this(location.getAddress(), location.getNumber(), location.getDetail(), location.getZipcode(),
                location.getDistrict(), location.getCity(), location.getState(), location.getCountry());
    }
}
