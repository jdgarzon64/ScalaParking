# Parking schema

# --- !Ups

CREATE TABLE Vehicle (
    id_vehicle INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    licence varchar(255) NOT NULL UNIQUE,
    vehicle_type varchar(255) NOT NULL,
    engine varchar(255) NOT NULL
);
# --- !Downs

DROP TABLE Vehicle;