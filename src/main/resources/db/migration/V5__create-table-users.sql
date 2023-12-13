CREATE TABLE users(
    id BIGINT NOT NULL auto_increment,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
)