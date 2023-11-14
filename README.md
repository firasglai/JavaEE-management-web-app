#  University Management System (Java EE 8 Web Application JSP , Servlet )

 

## Tools and Technologies used

1. JSP
2. Servlets 3.0
3. MVC Design Pattern
4. MySQL Workbench
5. Bootstrap 4
7. Apache tomcat 9

## Steps to install

1.Clone the application

```javascript
https://github.com/firasglai/Java-Web.git
```

2.Create a MySQL Database

```sql
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb3 ;
```

3.Create a table and insert values

```sql
CREATE TABLE IF NOT EXISTS `mydb`.`enseignant` (
  `id_ens` INT NOT NULL AUTO_INCREMENT,
  `nom_ens` VARCHAR(45) NOT NULL,
  `prenom_ens` VARCHAR(45) NOT NULL,
  `email_ens` VARCHAR(45) NULL DEFAULT NULL,
  `telephone_ens` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_ens`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb3;


CREATE TABLE IF NOT EXISTS `mydb`.`niveau` (
  `idniveau` INT NOT NULL,
  PRIMARY KEY (`idniveau`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


CREATE TABLE IF NOT EXISTS `mydb`.`specialite` (
  `nom_specialite` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nom_specialite`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


CREATE TABLE IF NOT EXISTS `mydb`.`groupe` (
  `nom_groupe` VARCHAR(45) NOT NULL,
  `nom_spec` VARCHAR(45) NOT NULL,
  `id_niveau` INT NOT NULL,
  PRIMARY KEY (`nom_groupe`, `nom_spec`, `id_niveau`),
  INDEX `fk_groupe_specialite1_idx` (`nom_spec` ASC) VISIBLE,
  INDEX `fk_groupe_niveau1_idx` (`id_niveau` ASC) VISIBLE,
  CONSTRAINT `fk_groupe_niveau1`
    FOREIGN KEY (`id_niveau`)
    REFERENCES `mydb`.`niveau` (`idniveau`),
  CONSTRAINT `fk_groupe_specialite1`
    FOREIGN KEY (`nom_spec`)
    REFERENCES `mydb`.`specialite` (`nom_specialite`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE TABLE IF NOT EXISTS `mydb`.`etudiant` (
  `idetudiant` INT NOT NULL AUTO_INCREMENT,
  `nom_etu` VARCHAR(45) NOT NULL,
  `prenom_etu` VARCHAR(45) NOT NULL,
  `email_etu` VARCHAR(45) NULL DEFAULT NULL,
  `nom_groupe` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idetudiant`, `nom_groupe`),
  INDEX `fk_etudiant_groupe1_idx` (`nom_groupe` ASC) VISIBLE,
  CONSTRAINT `fk_etudiant_groupe1`
    FOREIGN KEY (`nom_groupe`)
    REFERENCES `mydb`.`groupe` (`nom_groupe`))
ENGINE = InnoDB
AUTO_INCREMENT = 49
DEFAULT CHARACTER SET = utf8mb3;



CREATE TABLE IF NOT EXISTS `mydb`.`matiere` (
  `code_mat` INT NOT NULL AUTO_INCREMENT,
  `nom_mat` VARCHAR(45) NOT NULL,
  `coef_mat` INT NOT NULL,
  `nom_specialite` VARCHAR(45) NOT NULL,
  `id_niveau` INT NOT NULL,
  PRIMARY KEY (`code_mat`, `nom_specialite`, `id_niveau`),
  INDEX `fk_matiere_specialite1_idx` (`nom_specialite` ASC) VISIBLE,
  INDEX `fk_matiere_niveau1_idx` (`id_niveau` ASC) VISIBLE,
  CONSTRAINT `fk_matiere_niveau1`
    FOREIGN KEY (`id_niveau`)
    REFERENCES `mydb`.`niveau` (`idniveau`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_matiere_specialite1`
    FOREIGN KEY (`nom_specialite`)
    REFERENCES `mydb`.`specialite` (`nom_specialite`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1018
DEFAULT CHARACTER SET = utf8mb3;



CREATE TABLE IF NOT EXISTS `mydb`.`note` (
  `idnote` INT NOT NULL AUTO_INCREMENT,
  `note_pratique` FLOAT NULL DEFAULT NULL,
  `note_examen` FLOAT NULL DEFAULT NULL,
  `note_controle` FLOAT NULL DEFAULT NULL,
  `id_etudiant` INT NOT NULL,
  `code_mat` INT NOT NULL,
  PRIMARY KEY (`idnote`, `id_etudiant`, `code_mat`),
  INDEX `fk_note_etudiant1_idx1` (`id_etudiant` ASC) VISIBLE,
  INDEX `fk_note_matiere1_idx` (`code_mat` ASC) VISIBLE,
  INDEX `fk_note_matiere1_idx1` (`code_mat` ASC) VISIBLE,
  CONSTRAINT `fk_note_etudiant1`
    FOREIGN KEY (`id_etudiant`)
    REFERENCES `mydb`.`etudiant` (`idetudiant`),
  CONSTRAINT `fk_note_matiere1`
    FOREIGN KEY (`code_mat`)
    REFERENCES `mydb`.`matiere` (`code_mat`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb3;


```
