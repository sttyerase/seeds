-- MySQL Workbench Forward Engineering

-- SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
-- SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
-- SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SeedInspectionDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS SeedInspectionDB ;

-- -----------------------------------------------------
-- Schema SeedInspectionDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS SeedInspectionDB ;
-- USE SeedInspectionDB ;

-- -----------------------------------------------------
-- Table SeedInspectionDB.producers
-- -----------------------------------------------------
DROP TABLE IF EXISTS SeedInspectionDB.producers ;

CREATE TABLE IF NOT EXISTS SeedInspectionDB.producers (
  producer_id BIGINT NOT NULL,
  producer_short_name VARCHAR(50) NULL,
  producer_name VARCHAR(50) NULL,
  producer_address1 VARCHAR(50) NULL,
  producer_address2 VARCHAR(50) NULL,
  producer_city VARCHAR(50) NULL,
  producer_state VARCHAR(2) NULL,
  producer_zip VARCHAR(10) NULL,
  PRIMARY KEY (producer_id))
-- ENGINE = InnoDB
;

CREATE UNIQUE INDEX producer_name_UNIQUE ON SeedInspectionDB.producers (producer_short_name);


-- -----------------------------------------------------
-- Table crops
-- -----------------------------------------------------
DROP TABLE IF EXISTS SeedInspectionDB.crops ;

CREATE TABLE IF NOT EXISTS SeedInspectionDB.crops (
  crop_id BIGINT NOT NULL,
  crop_name VARCHAR(200) NOT NULL,
  crop_description VARCHAR(2000) NULL,
  crop_icc_code BIGINT NOT NULL DEFAULT 0,
  PRIMARY KEY (crop_id))
-- ENGINE = InnoDB
-- DEFAULT CHARACTER SET = ascii
;

CREATE UNIQUE INDEX uk_crop_name ON SeedInspectionDB.crops (crop_name);


-- -----------------------------------------------------
-- Table varieties
-- -----------------------------------------------------
DROP TABLE IF EXISTS SeedInspectionDB.varieties ;

CREATE TABLE IF NOT EXISTS SeedInspectionDB.varieties (
  variety_id BIGINT NOT NULL,
  variety_name VARCHAR(100) NOT NULL,
  variety_crop_id BIGINT NOT NULL,
  variety_description VARCHAR(2000) NULL,
  PRIMARY KEY (variety_id),
  CONSTRAINT fk_varieties_crops
    FOREIGN KEY (variety_crop_id)
    REFERENCES SeedInspectionDB.crops (crop_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
-- ENGINE = InnoDB
-- = 0
;

CREATE INDEX fk_varieties_crops_idx ON SeedInspectionDB.varieties (variety_crop_id);

CREATE INDEX uk_variety_name_crop ON SeedInspectionDB.varieties (variety_name, variety_crop_id);


-- -----------------------------------------------------
-- Table samples
-- -----------------------------------------------------
DROP TABLE IF EXISTS SeedInspectionDB.samples ;

CREATE TABLE IF NOT EXISTS samples (
  sample_id BIGINT NOT NULL,
  sample_entry_date DATE NOT NULL,
  sample_producer_id BIGINT NOT NULL,
  sample_variety_id BIGINT NOT NULL,
  sample_lot_id VARCHAR(45) NULL,
  PRIMARY KEY (sample_id),
  CONSTRAINT fk_samples_producers
    FOREIGN KEY (sample_producer_id)
    REFERENCES SeedInspectionDB.producers (producer_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_samples_varieties
    FOREIGN KEY (sample_variety_id)
    REFERENCES SeedInspectionDB.varieties (variety_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
-- ENGINE = InnoDB
;

CREATE INDEX fk_samples_producers1_idx ON SeedInspectionDB.samples (sample_producer_id);

CREATE INDEX fk_samples_varieties1_idx ON SeedInspectionDB.samples (sample_variety_id);


-- -----------------------------------------------------
-- Table germinations
-- -----------------------------------------------------
DROP TABLE IF EXISTS SeedInspectionDB.germinations ;

CREATE TABLE IF NOT EXISTS germinations (
  germination_id INT NOT NULL,
  percent_germinated DOUBLE NOT NULL DEFAULT 0,
  percent_dead DOUBLE NOT NULL DEFAULT 0,
  percent_weak DOUBLE NOT NULL DEFAULT 0,
  germination_comments VARCHAR(2000) NULL,
  germination_sample_id BIGINT NOT NULL,
  PRIMARY KEY (germination_id),
  CONSTRAINT fk_germinations_samples
    FOREIGN KEY (germination_sample_id)
    REFERENCES samples (sample_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
-- ENGINE = InnoDB
;

CREATE INDEX fk_germinations_samples1_idx ON SeedInspectionDB.germinations (germination_sample_id);


-- -----------------------------------------------------
-- Table purities
-- -----------------------------------------------------
DROP TABLE IF EXISTS SeedInspectionDB.purities ;

CREATE TABLE IF NOT EXISTS SeedInspectionDB.purities (
  purity_id BIGINT NOT NULL,
  purity_sample_id BIGINT NULL,
  pure_seed DOUBLE NOT NULL,
  foreign_matter DOUBLE NOT NULL,
  PRIMARY KEY (purity_id),
  CONSTRAINT fk_purities_samples
    FOREIGN KEY (purity_sample_id)
    REFERENCES samples (sample_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
-- ENGINE = InnoDB
-- COMMENT = 'purity results for sample.'
;

CREATE INDEX fk_purities_samples1_idx ON SeedInspectionDB.purities (purity_sample_id);


-- -----------------------------------------------------
-- Table producer_privacy_keys
-- -----------------------------------------------------
DROP TABLE IF EXISTS SeedInspectionDB.producer_privacy_keys ;

CREATE TABLE IF NOT EXISTS SeedInspectionDB.producer_privacy_keys (
  producer_pkkeys_id BIGINT NOT NULL,
  producer_pkkeys_key VARCHAR(2048) NOT NULL,
  producer_pkkeys_producer_id BIGINT NOT NULL,
  PRIMARY KEY (producer_pkkeys_id),
  CONSTRAINT fk_producer_pkkeys_producers1
    FOREIGN KEY (producer_pkkeys_producer_id)
    REFERENCES SeedInspectionDB.producers (producer_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
-- ENGINE = InnoDB
-- COMMENT = 'Producer-privacy key is a unique encrypted key, registered at the time of the account creation.  The producer-privacy key is then used to produce a unique\ntoken upon entry to the system.  Each token expires at the end of the transaction.   The producer privacy key also identifies which records the producer may access.\n\n';
;

CREATE INDEX fk_producer_pkkeys_producers1_idx ON SeedInspectionDB.producer_privacy_keys (producer_pkkeys_producer_id);


-- -----------------------------------------------------
-- Table fields
-- -----------------------------------------------------
DROP TABLE IF EXISTS SeedInspectionDB.fields ;

CREATE TABLE IF NOT EXISTS SeedInspectionDB.fields (
  field_id BIGINT NOT NULL,
  field_location VARCHAR(1000) NULL,
  field_producer_id BIGINT NOT NULL,
  field_variety_id BIGINT NOT NULL,
  field_inspection_date DATE NULL,
  field_inspection_results VARCHAR(100) NULL,
  field_inspection_comments VARCHAR(2000) NULL,
  field_application_date DATE NOT NULL,
  PRIMARY KEY (field_id),
  CONSTRAINT fk_fields_producers1
    FOREIGN KEY (field_producer_id)
    REFERENCES producers (producer_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_fields_varieties1
    FOREIGN KEY (field_variety_id)
    REFERENCES SeedInspectionDB.varieties (variety_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
-- ENGINE = InnoDB
;

CREATE INDEX fk_fields_producers1_idx ON SeedInspectionDB.fields (field_producer_id);

CREATE INDEX fk_fields_varieties1_idx ON SeedInspectionDB.fields (field_variety_id);


-- -----------------------------------------------------
-- Table fieldinspections
-- -----------------------------------------------------
DROP TABLE IF EXISTS SeedInspectionDB.fieldinspections ;

CREATE TABLE IF NOT EXISTS SeedInspectionDB.fieldinspections (
  fieldinspection_id BIGINT NOT NULL,
  fieldinspection_pass VARCHAR(10) NULL,
  field_inspection_results VARCHAR(500) NULL,
  fieldinspection_field_id BIGINT NOT NULL,
  fieldinspection_application_number BIGINT NOT NULL,
  PRIMARY KEY (fieldinspection_id),
  CONSTRAINT fk_fieldinspections_fields
    FOREIGN KEY (fieldinspection_field_id)
    REFERENCES SeedInspectionDB.fields (field_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
-- ENGINE = InnoDB
;

CREATE INDEX fk_fieldinspections_fields_idx ON SeedInspectionDB.fieldinspections (fieldinspection_field_id);


-- -----------------------------------------------------
-- Table application_audits
-- -----------------------------------------------------
DROP TABLE IF EXISTS SeedInspectionDB.application_audits ;

CREATE TABLE IF NOT EXISTS SeedInspectionDB.application_audits (
  audit_entry_id INT NOT NULL,
  audit_entry_table VARCHAR(45) NOT NULL,
  audit_entry_time DATE NOT NULL,
  audit_entry_agent VARCHAR(200) NOT NULL,
  PRIMARY KEY (audit_entry_id))
-- ENGINE = InnoDB
;


-- -----------------------------------------------------
-- Table producer_contacts
-- -----------------------------------------------------
DROP TABLE IF EXISTS SeedInspectionDB.producer_contacts ;

CREATE TABLE IF NOT EXISTS SeedInspectionDB.producer_contacts (
  producer_contact_id BIGINT NOT NULL,
  producer_contact_type VARCHAR(45) NOT NULL,
  producer_contact_person VARCHAR(45) NULL,
  producer_contact_information VARCHAR(200) NULL,
  producer_contact_producer_id BIGINT NOT NULL,
  PRIMARY KEY (producer_contact_id),
  CONSTRAINT fk_producer_contacts_producers
    FOREIGN KEY (producer_contact_producer_id)
    REFERENCES SeedInspectionDB.producers (producer_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
-- ENGINE = InnoDB
;

CREATE INDEX fk_producer_contacts_producers_idx ON SeedInspectionDB.producer_contacts (producer_contact_producer_id);

-- SET SQL_MODE = '';
DROP USER IF EXISTS seedmgr;
-- SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'seedmgr' IDENTIFIED BY 'password';

GRANT ALL ON * TO 'seedmgr';
GRANT ALL ON TABLE fields TO 'seedmgr';
GRANT ALL ON TABLE crops TO 'seedmgr';
GRANT ALL ON TABLE germinations TO 'seedmgr';
GRANT ALL ON TABLE producer_privacy_keys TO 'seedmgr';
GRANT ALL ON TABLE producers TO 'seedmgr';
GRANT ALL ON TABLE purities TO 'seedmgr';
GRANT ALL ON TABLE samples TO 'seedmgr';
GRANT ALL ON TABLE varieties TO 'seedmgr';

-- SET SQL_MODE=@OLD_SQL_MODE;
-- SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
-- SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
