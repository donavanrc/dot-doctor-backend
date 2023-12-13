package com.donavanrc.dotdoctor.doctors;

import com.donavanrc.dotdoctor.locations.Location;
import jakarta.persistence.*;
import lombok.*;

@Table(name="doctors")
@Entity(name="Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private MedicalSpecialtyEnum specialty;
    @Embedded
    private Location location;
    private Boolean active;

    public Doctor(DoctorCreateDTO doctor) {
        this.active = true;
        this.name = doctor.name();
        this.email = doctor.email();
        this.phone = doctor.phone();
        this.crm = doctor.crm();
        this.specialty = doctor.specialty();
        this.location = new Location(doctor.location());
    }

    public Doctor(DoctorUpdateDTO doctor) {
        this.name = doctor.name();
        this.phone = doctor.phone();
        this.location = new Location(doctor.location());
    }

    public void update(DoctorUpdateDTO doctor) {
        if(doctor.name() != null)
            this.name = doctor.name();
        if(doctor.phone() != null)
            this.phone = doctor.phone();
        if(doctor.location() != null)
            this.location.update(doctor.location());
    }

    public void deactivate() {
        this.active = false;
    }
}
