package com.donavanrc.dotdoctor.locations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LocationDTO(
        @NotBlank
        String address,
        String number,
        String detail,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String zipcode,
        @NotBlank
        String district,
        @NotBlank
        String city,
        @NotBlank
        String state,
        @NotBlank
        String country) {
}
