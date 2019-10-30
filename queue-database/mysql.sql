SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`users` (
  `_id` INT NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `role` ENUM('receptionist','technician') NULL DEFAULT NULL ,
  `status` TINYINT(1) NULL ,
  `number` INT NULL ,
  `createdAt` TIMESTAMP NOT NULL ,
  `updatedAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (`_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`person`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`person` (
  `_id` INT NOT NULL ,
  `username` VARCHAR(45) NULL ,
  `password` VARCHAR(255) NULL ,
  `createdAt` TIMESTAMP NOT NULL ,
  `updatedAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  `users__id` INT NOT NULL ,
  PRIMARY KEY (`_id`) ,
  INDEX `fk_person_users1_idx` (`users__id` ASC) ,
  CONSTRAINT `fk_person_users1`
    FOREIGN KEY (`users__id` )
    REFERENCES `mydb`.`users` (`_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`queues`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`queues` (
  `_id` INT NOT NULL ,
  `queueNumber` VARCHAR(45) NULL ,
  `createdAt` TIMESTAMP NOT NULL ,
  `updatedAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  `technicians__id` INT NOT NULL ,
  `users__id` INT NOT NULL ,
  PRIMARY KEY (`_id`) ,
  INDEX `fk_queues_users1_idx` (`users__id` ASC) ,
  CONSTRAINT `fk_queues_users1`
    FOREIGN KEY (`users__id` )
    REFERENCES `mydb`.`users` (`_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mydb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
