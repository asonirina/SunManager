CREATE DATABASE IF NOT EXISTS sun_manager CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS customers (
      customer_id    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
      mobile         VARCHAR(11),
      name           VARCHAR(150)
) ENGINE=INNODB;

CREATE  TABLE IF NOT EXISTS users (
      login          VARCHAR(15) NOT NULL UNIQUE PRIMARY KEY,
      name           VARCHAR(150),
      password       VARCHAR(15),
      role           VARCHAR(15)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS cosmetics (
      cosmetics_id    int NOT NULL AUTO_INCREMENT PRIMARY KEY,
      name            VARCHAR(150),
      price           INT,
      cosmetics_count int
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS vertical_sun (
      vertical_sun_id    int NOT NULL AUTO_INCREMENT PRIMARY KEY,
      start_date         date,
      total_minute       INT,
      total_price        int,
      l2                 double,
      one_minute_price   int
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS gorizontal_blue_sun (
      gorizontal_blue_sun_id    int NOT NULL AUTO_INCREMENT PRIMARY KEY,
      start_date         date,
      total_minute       INT,
      total_price        int,
      l2                 double,
      one_minute_price   int
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS gorizontal_green_sun (
      gorizontal_green_sun_id    int NOT NULL AUTO_INCREMENT PRIMARY KEY,
      start_date         date,
      total_minute       INT,
      total_price        int,
      l2                 double,
      one_minute_price   int
) ENGINE=INNODB;


CREATE TABLE IF NOT EXISTS vertical_sun_data (
      data_id            int NOT NULL AUTO_INCREMENT PRIMARY KEY,
      start_date         date,
      minutes            INT,
      total_price        int,
      abonement_number   VARCHAR(10)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS gorizontal_blue_sun_data (
      data_id            int NOT NULL AUTO_INCREMENT PRIMARY KEY,
      start_date         date,
      minutes            INT,
      total_price        int,
      abonement_number   VARCHAR(10)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS gorizontal_green_sun_data (
      data_id            int NOT NULL AUTO_INCREMENT PRIMARY KEY,
      start_date         date,
      minutes            INT,
      total_price        int,
      abonement_number   VARCHAR(10)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS abonements_data (
      data_id            int NOT NULL AUTO_INCREMENT PRIMARY KEY,
      start_date         date,
      code               int,
      letter             VARCHAR(100),
      client_name        VARCHAR(100),
      phone              VARCHAR(100),
      minutes            int
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS cosmetics_data (
      data_id            int NOT NULL AUTO_INCREMENT PRIMARY KEY,
      start_date         date,
      cosm_count         int,
      cosm_name          VARCHAR(100),
      price              int
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS comments_data (
      comment_id         int NOT NULL AUTO_INCREMENT PRIMARY KEY,
      start_date         date,
      comment            VARCHAR(500)
) ENGINE=INNODB;


CREATE TABLE IF NOT EXISTS statistic_data (
      statistic_id         int NOT NULL AUTO_INCREMENT PRIMARY KEY,
      start_date           date,
      residue              int,
      booking_per_day      int,
      bank                 int,
      official_salary      int,
      quenching            int,
      accumulation         int,
      user_login           VARCHAR(15)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS available_abonements (
      letter               VARCHAR(15) NOT NULL PRIMARY KEY,
      price                int,
      minutes              int,
      duration             int
) ENGINE=INNODB;