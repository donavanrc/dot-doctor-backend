package com.donavanrc.dotdoctor.locations;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private String address;
    private String number;
    private String detail;
    private String zipcode;
    private String district;
    private String city;
    private String state;
    private String country;

    public Location(LocationDTO location) {
        this.address = location.address();
        this.number = location.number();
        this.detail = location.detail();
        this.zipcode = location.zipcode();
        this.district = location.district();
        this.city = location.city();
        this.state = location.state();
        this.country = location.country();
    }

    public void update(LocationDTO location) {
        if(location.address() != null)
            this.address = location.address();
        if(location.number() != null)
            this.number = location.number();
        if(location.detail() != null)
            this.detail = location.detail();
        if(location.zipcode() != null)
            this.zipcode = location.zipcode();
        if(location.district() != null)
            this.district = location.district();
        if(location.city() != null)
            this.city = location.city();
        if(location.state() != null)
            this.state = location.state();
        if(location.country() != null)
            this.country = location.country();
    }
}
