CREATE TABLE patients(
    id BIGINT NOT NULL auto_increment,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(100) NOT NULL,
    number VARCHAR(20),
    detail VARCHAR(100),
    zipcode VARCHAR(9) NOT NULL,
    district VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(2) NOT NULL,
    country VARCHAR(100) NOT NULL,
    active TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY(id)
)