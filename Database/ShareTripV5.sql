-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sharetrip
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sharetrip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sharetrip` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `sharetrip` ;

-- -----------------------------------------------------
-- Table `sharetrip`.`sido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sharetrip`.`sido` (
  `sido_code` INT NOT NULL,
  `sido_name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sharetrip`.`gugun`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sharetrip`.`gugun` (
  `gugun_code` INT NOT NULL,
  `gugun_name` VARCHAR(30) NULL DEFAULT NULL,
  `sido_code` INT NOT NULL,
  PRIMARY KEY (`gugun_code`, `sido_code`),
  INDEX `gugun_to_sido_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  CONSTRAINT `gugun_to_sido_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `sharetrip`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sharetrip`.`attraction_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sharetrip`.`attraction_info` (
  `content_id` INT NOT NULL,
  `content_type_id` INT NULL DEFAULT NULL,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `addr1` VARCHAR(100) NULL DEFAULT NULL,
  `addr2` VARCHAR(50) NULL DEFAULT NULL,
  `zipcode` VARCHAR(50) NULL DEFAULT NULL,
  `tel` VARCHAR(50) NULL DEFAULT NULL,
  `first_image` VARCHAR(200) NULL DEFAULT NULL,
  `first_image2` VARCHAR(200) NULL DEFAULT NULL,
  `readcount` INT NULL DEFAULT NULL,
  `sido_code` INT NULL DEFAULT NULL,
  `gugun_code` INT NULL DEFAULT NULL,
  `latitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `longitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `mlevel` VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  INDEX `attraction_to_content_type_id_fk_idx` (`content_type_id` ASC) VISIBLE,
  INDEX `attraction_to_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  INDEX `attraction_to_gugun_code_fk_idx` (`gugun_code` ASC) VISIBLE,
  CONSTRAINT `attraction_to_content_type_id_fk`
    FOREIGN KEY (`content_type_id`)
    REFERENCES `sharetrip`.`content_type` (`content_type_id`),
  CONSTRAINT `attraction_to_gugun_code_fk`
    FOREIGN KEY (`gugun_code`)
    REFERENCES `sharetrip`.`gugun` (`gugun_code`),
  CONSTRAINT `attraction_to_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `sharetrip`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sharetrip`.`attraction_description`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sharetrip`.`attraction_description` (
  `content_id` INT NOT NULL,
  `homepage` VARCHAR(100) NULL DEFAULT NULL,
  `overview` VARCHAR(10000) NULL DEFAULT NULL,
  `telname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_attraciton_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `sharetrip`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sharetrip`.`attraction_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sharetrip`.`attraction_detail` (
  `content_id` INT NOT NULL,
  `cat1` VARCHAR(3) NULL DEFAULT NULL,
  `cat2` VARCHAR(5) NULL DEFAULT NULL,
  `cat3` VARCHAR(9) NULL DEFAULT NULL,
  `created_time` VARCHAR(14) NULL DEFAULT NULL,
  `modified_time` VARCHAR(14) NULL DEFAULT NULL,
  `booktour` VARCHAR(5) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_basic_content_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `sharetrip`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sharetrip`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sharetrip`.`user` (
  `id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email_id` VARCHAR(45) NOT NULL,
  `email_domain` VARCHAR(45) NOT NULL,
  `join_date` DATETIME NOT NULL,
  `birth` DATE NULL,
  `gender` VARCHAR(20) NULL,
  `profile_image` LONGTEXT NULL,
  `token` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sharetrip`.`user_trip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sharetrip`.`user_trip` (
  `trip_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NOT NULL,
  `title` VARCHAR(200) NOT NULL,
  `create_time` DATETIME NOT NULL,
  `is_shared` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`trip_no`),
  INDEX `fk_user_trip_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_trip_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sharetrip`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sharetrip`.`attraction_fav`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sharetrip`.`attraction_fav` (
  `content_id` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `like_time` DATETIME NOT NULL,
  `category` INT NOT NULL,
  PRIMARY KEY (`content_id`, `user_id`),
  INDEX `fk_attraction_info_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_attraction_info_has_user_attraction_info1_idx` (`content_id` ASC) VISIBLE,
  CONSTRAINT `fk_attraction_info_has_user_attraction_info1`
    FOREIGN KEY (`content_id`)
    REFERENCES `sharetrip`.`attraction_info` (`content_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_attraction_info_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sharetrip`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sharetrip`.`trip_attraction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sharetrip`.`trip_attraction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `trip_no` INT NOT NULL,
  `content_id` INT NOT NULL,
  `day` INT NOT NULL DEFAULT 0,
  `order` INT NOT NULL DEFAULT 0,
  `pick_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trip_attraction_user_trip1_idx` (`trip_no` ASC) VISIBLE,
  CONSTRAINT `fk_trip_attraction_attraction_info1`
    FOREIGN KEY (`content_id`)
    REFERENCES `sharetrip`.`attraction_info` (`content_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_trip_attraction_user_trip1`
    FOREIGN KEY (`trip_no`)
    REFERENCES `sharetrip`.`user_trip` (`trip_no`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sharetrip`.`user_follow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sharetrip`.`user_follow` (
  `id_from` VARCHAR(45) NOT NULL,
  `id_to` VARCHAR(45) NOT NULL,
  `follow_time` DATETIME NOT NULL,
  PRIMARY KEY (`id_from`, `id_to`),
  INDEX `fk_user_has_user_user2_idx` (`id_to` ASC) VISIBLE,
  INDEX `fk_user_has_user_user1_idx` (`id_from` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_user_user1`
    FOREIGN KEY (`id_from`)
    REFERENCES `sharetrip`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2`
    FOREIGN KEY (`id_to`)
    REFERENCES `sharetrip`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sharetrip`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sharetrip`.`board` (
  `board_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NOT NULL,
  `user_trip_trip_no` INT NULL,
  `subject` VARCHAR(100) NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `regist_time` DATETIME NOT NULL,
  PRIMARY KEY (`board_no`),
  INDEX `fk_board_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_board_user_trip1_idx` (`user_trip_trip_no` ASC) VISIBLE,
  CONSTRAINT `fk_board_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sharetrip`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_board_user_trip1`
    FOREIGN KEY (`user_trip_trip_no`)
    REFERENCES `sharetrip`.`user_trip` (`trip_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sharetrip`.`board_fav`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sharetrip`.`board_fav` (
  `user_id` VARCHAR(45) NOT NULL,
  `board_no` INT NOT NULL,
  `category` INT NOT NULL,
  `bookmark_time` DATETIME NOT NULL,
  PRIMARY KEY (`user_id`, `board_no`),
  INDEX `fk_user_has_user_trip_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_trip_fav_board1_idx` (`board_no` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_user_trip_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sharetrip`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trip_fav_board1`
    FOREIGN KEY (`board_no`)
    REFERENCES `sharetrip`.`board` (`board_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sharetrip`.`board_memo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sharetrip`.`board_memo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NOT NULL,
  `board_no` INT NOT NULL,
  `content` VARCHAR(1000) NOT NULL,
  `regist_time` DATETIME NOT NULL,
  INDEX `fk_user_trip_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  INDEX `fk_trip_memo_board1_idx` (`board_no` ASC) VISIBLE,
  CONSTRAINT `fk_user_trip_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sharetrip`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trip_memo_board1`
    FOREIGN KEY (`board_no`)
    REFERENCES `sharetrip`.`board` (`board_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sharetrip`.`trip_share`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sharetrip`.`trip_share` (
  `trip_no` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `join_date` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`trip_no`, `user_id`),
  INDEX `fk_user_has_user_trip_user_trip2_idx` (`trip_no` ASC) VISIBLE,
  INDEX `fk_user_has_user_trip_user2_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_user_trip_user2`
    FOREIGN KEY (`user_id`)
    REFERENCES `sharetrip`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_trip_user_trip2`
    FOREIGN KEY (`trip_no`)
    REFERENCES `sharetrip`.`user_trip` (`trip_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sharetrip`.`attraction_memo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sharetrip`.`attraction_memo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content_id` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `content` VARCHAR(1000) NOT NULL,
  `regist_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_has_attraction_info_attraction_info1_idx` (`content_id` ASC) VISIBLE,
  INDEX `fk_user_has_attraction_info_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_attraction_info_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sharetrip`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_attraction_info_attraction_info1`
    FOREIGN KEY (`content_id`)
    REFERENCES `sharetrip`.`attraction_info` (`content_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
