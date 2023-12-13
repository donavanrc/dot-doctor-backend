package com.donavanrc.dotdoctor.domain.patient;

import com.donavanrc.dotdoctor.domain.location.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    @Embedded
    private Location location;
    private Boolean active;

    public Patient(PatientCreateDTO patient) {
        this.active = true;
        this.name = patient.name();
        this.email = patient.email();
        this.phone = patient.phone();
        this.location = new Location(patient.location());
    }

    public Patient(PatientUpdateDTO patient) {
        this.name = patient.name();
        this.phone = patient.phone();
        this.location = new Location(patient.location());
    }

    public void update(PatientUpdateDTO patient) {
        if (patient.name() != null)
            this.name = patient.name();
        if (patient.phone() != null)
            this.phone = patient.phone();
        if (patient.location() != null)
            this.location.update(patient.location());
    }

    public void deactivate() {
        this.active = false;
    }
}
