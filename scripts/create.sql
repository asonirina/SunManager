CREATE DATABASE IF NOT EXISTS sun_manager;

CREATE TABLE IF NOT EXISTS customers (
      customer_id    INT NOT NULL PRIMARY KEY,
      mobile         VARCHAR(11),
      name           VARCHAR(150)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS abonements (
      abonement_code VARCHAR(10) NOT NULL PRIMARY KEY,
      price          INT,
      customer_id    INT NOT NULL,
      buy_date       date,
      end_date       date,
      minutes        int,
      duration       int,
      is_free         boolean,

      FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
) ENGINE=INNODB;

CREATE  TABLE IF NOT EXISTS users (
      login          VARCHAR(15) NOT NULL UNIQUE PRIMARY KEY,
      name           VARCHAR(150),
      password       VARCHAR(15),
      role           VARCHAR(15)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS cosmetics (
      cosmetics_id    int NOT NULL PRIMARY KEY,
      name            VARCHAR(150),
      price           INT,
      cosmetics_count int
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS vertical_sun (
      vertical_sun_id    int NOT NULL PRIMARY KEY,
      start_date         date,
      total_minute       INT,
      total_price        int,
      l2                 int,
      one_minute_price   int
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS gorizontal_blue_sun (
      gorizontal_blue_sun_id    int NOT NULL PRIMARY KEY,
      start_date         date,
      total_minute       INT,
      total_price        int,
      l2                 int,
      one_minute_price   int
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS gorizontal_green_sun (
      gorizontal_green_sun_id    int NOT NULL PRIMARY KEY,
      start_date         date,
      total_minute       INT,
      total_price        int,
      l2                 int,
      one_minute_price   int
) ENGINE=INNODB;


CREATE TABLE IF NOT EXISTS vertical_sun_data (
      data_id            int NOT NULL PRIMARY KEY,
      start_date         date,
      minutes            INT,
      total_price        int,
      abonement_number   VARCHAR(10)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS gorizontal_blue_sun_data (
      data_id            int NOT NULL PRIMARY KEY,
      start_date         date,
      minutes            INT,
      total_price        int,
      abonement_number   VARCHAR(10)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS gorizontal_green_sun_data (
      data_id            int NOT NULL PRIMARY KEY,
      start_date         date,
      minutes            INT,
      total_price        int,
      abonement_number   VARCHAR(10)
) ENGINE=INNODB;