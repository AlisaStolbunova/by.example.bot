CREATE TABLE city (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(255) UNIQUE NOT NULL,
    info varchar(2550),
    PRIMARY KEY (id)
);