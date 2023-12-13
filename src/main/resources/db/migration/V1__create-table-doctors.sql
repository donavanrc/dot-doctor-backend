CREATE TABLE doctors(
    id BIGINT NOT NULL auto_increment,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    crm VARCHAR(6) NOT NULL UNIQUE,
    specialty VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL,
    number VARCHAR(20),
    detail VARCHAR(100),
    zipcode VARCHAR(9) NOT NULL,
    district VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(2) NOT NULL,
    country VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
)