CREATE TABLE appointments(
    id BIGINT NOT NULL auto_increment,
    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    date_time DATETIME NOT NULL,
    PRIMARY KEY(id),
    constraint fk_appointments_doctor_id foreign key(doctor_id) references doctors(id),
    constraint fk_appointments_patient_id foreign key(patient_id) references patients(id)
)