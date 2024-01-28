-- Create SQL table for Temperature & Population Database
PRAGMA foreign_keys = OFF;
drop table if exists CountryTemperature;
drop table if exists CityTemperature;
drop table if exists StateTemperature;
drop table if exists Population;
drop table if exists Country;
drop table if exists WorldTemperature;
PRAGMA foreign_keys = ON;

-- Create Country table
CREATE TABLE Country(
    country_code      VARCHAR(3) PRIMARY KEY,
    country_name      VARCHAR(100) NOT NULL
);

-- Create Population table
CREATE TABLE Population(
    pop_id            INTEGER PRIMARY KEY AUTOINCREMENT,
    country_code      VARCHAR(3) NOT NULL,
    year              INTEGER NOT NULL,
    pop_num           INTEGER,
    FOREIGN KEY (country_code) REFERENCES Country(country_code)
);

-- Create World Temperature table
CREATE TABLE WorldTemperature(
    World_temp_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    year              INTEGER NOT NULL,
    country_code      VARCHAR(3) NOT NULL,
    AVG_temp         DECIMAL(5,2),
    MAX_temp         DECIMAL(5,2),
    MIN_temp         DECIMAL(5,2),
    L_O_AVG_temp     DECIMAL(5,2),
    L_O_MIN_temp    DECIMAL(5,2),
    L_O_MAX_temp    DECIMAL(5,2),
    FOREIGN KEY (country_code) REFERENCES Country(country_code)
);

-- Create Country temperature table
CREATE TABLE CountryTemperature(
    ctry_temp_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    country_code      VARCHAR(3) NOT NULL,
    year              INTEGER NOT NULL,
    AVG_temp              DECIMAL(5,2),
    MIN_temp              DECIMAL(5,2),
    MAX_temp              DECIMAL(5,2),
    FOREIGN KEY (country_code) REFERENCES Country(country_code)
);

-- Create State temperature table 
CREATE TABLE StateTemperature(
    state_temp_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    country_code     VARCHAR(3) NOT NULL,
    state_name      VARCHAR(25) NOT NULL,
    year              INTEGER NOT NULL,
    AVG_temp              DECIMAL(5,2),
    MIN_temp              DECIMAL(5,2),
    MAX_temp              DECIMAL(5,2),
    FOREIGN KEY (country_code) REFERENCES CountryTemperature(country_code)
);

-- Create City temperature table
CREATE TABLE CityTemperature(
    city_temp_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    country_code     VARCHAR(3) NOT NULL,
    city_name      VARCHAR(25) NOT NULL,
    year              INTEGER NOT NULL,
    AVG_temp              DECIMAL(5,2),
    MIN_temp              DECIMAL(5,2),
    MAX_temp              DECIMAL(5,2),
    FOREIGN KEY (country_code) REFERENCES CountryTemperature(country_code)
); 