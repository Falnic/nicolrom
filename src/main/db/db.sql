-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema nicolrom
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema nicolrom
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nicolrom` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `nicolrom` ;

-- -----------------------------------------------------
-- Table `nicolrom`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nicolrom`.`employee` (
  `idEmployee` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `position` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEmployee`),
  UNIQUE INDEX `idemployee_UNIQUE` (`idEmployee` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nicolrom`.`hole`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nicolrom`.`hole` (
  `holeId` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `streetNr` VARCHAR(8) NOT NULL,
  `locality` VARCHAR(45) NOT NULL,
  `district` VARCHAR(45) NOT NULL,
  `phaseId` INT(11) NOT NULL,
  `holeLength` DOUBLE NOT NULL,
  `holeWidth` DOUBLE NOT NULL,
  `holeDepth` DOUBLE NOT NULL,
  `holeVolume` DOUBLE NOT NULL,
  PRIMARY KEY (`holeId`),
  UNIQUE INDEX `holeId_UNIQUE` (`holeId` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nicolrom`.`machinery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nicolrom`.`machinery` (
  `machineryId` INT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `licensePlate` VARCHAR(10) NOT NULL,
  `machineryType` ENUM('UTILAJ', 'AUTO') NOT NULL,
  `capacity` INT(11) NOT NULL,
  `employeeId` INT(11) NOT NULL,
  PRIMARY KEY (`machineryId`),
  UNIQUE INDEX `machineryId_UNIQUE` (`machineryId` ASC) VISIBLE,
  INDEX `employeeId_idx` (`employeeId` ASC) VISIBLE,
  CONSTRAINT `employeeId`
    FOREIGN KEY (`employeeId`)
    REFERENCES `nicolrom`.`employee` (`idEmployee`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nicolrom`.`material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nicolrom`.`material` (
  `materialId` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`materialId`),
  UNIQUE INDEX `materialId_UNIQUE` (`materialId` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nicolrom`.`team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nicolrom`.`team` (
  `idTeam` INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idTeam`),
  UNIQUE INDEX `idTeam_UNIQUE` (`idTeam` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nicolrom`.`phase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nicolrom`.`phase` (
  `phaseId` INT(10) NOT NULL AUTO_INCREMENT,
  `holeId` INT(11) NOT NULL,
  `phaseType` ENUM('SAPATURA', 'UMPLERE', 'ASFALTARE') NOT NULL,
  `phaseDate` DATE NOT NULL,
  `teamId` INT(11) NOT NULL,
  PRIMARY KEY (`phaseId`),
  UNIQUE INDEX `phaseId_UNIQUE` (`phaseId` ASC) VISIBLE,
  INDEX `holeId_idx` (`holeId` ASC) VISIBLE,
  INDEX `teamId_idx` (`teamId` ASC) VISIBLE,
  CONSTRAINT `holeId`
    FOREIGN KEY (`holeId`)
    REFERENCES `nicolrom`.`hole` (`holeId`),
  CONSTRAINT `teamId`
    FOREIGN KEY (`teamId`)
    REFERENCES `nicolrom`.`team` (`idTeam`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nicolrom`.`phase_material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nicolrom`.`phase_material` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `phaseId` INT(11) NOT NULL,
  `quantity` INT(11) NOT NULL,
  `materialId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `phaseId_idx` (`phaseId` ASC) VISIBLE,
  INDEX `materialId_idx` (`materialId` ASC) VISIBLE,
  CONSTRAINT `materialId`
    FOREIGN KEY (`materialId`)
    REFERENCES `nicolrom`.`material` (`materialId`),
  CONSTRAINT `phaseId`
    FOREIGN KEY (`phaseId`)
    REFERENCES `nicolrom`.`phase` (`phaseId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nicolrom`.`team_employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nicolrom`.`team_employee` (
  `team_employeeId` INT(11) NOT NULL AUTO_INCREMENT,
  `employeeId` INT(11) NOT NULL,
  `teamId` INT(11) NOT NULL,
  PRIMARY KEY (`team_employeeId`),
  UNIQUE INDEX `team_employeeId_UNIQUE` (`team_employeeId` ASC) VISIBLE,
  INDEX `employeeId_idx` (`employeeId` ASC) VISIBLE,
  INDEX `teamId_idx` (`teamId` ASC) VISIBLE,
  CONSTRAINT `idEmployee`
    FOREIGN KEY (`employeeId`)
    REFERENCES `nicolrom`.`employee` (`idEmployee`),
  CONSTRAINT `idTeam`
    FOREIGN KEY (`teamId`)
    REFERENCES `nicolrom`.`team` (`idTeam`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `nicolrom`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nicolrom`.`user` (
  `idUser` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `userRight` ENUM('ADMIN', 'ANGAJAT') NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `idUser_UNIQUE` (`idUser` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
