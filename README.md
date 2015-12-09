LAT — Loan A Thing
==================================================

What you need to build your own LAT Project
-------------------------------------------

1. Libraries
Add embedded libraries,
```
(web/WEB-INF/lib)
```
to your IDE and your deployment plan

2. SQL with MySQL
- Change with your SGBD settings in :
```
src/com/lat/dao/dao.properties
```
- Create database named lat :
```
create database lat;
```

- Create table in order :
 1 - Create table GROUPS :
 ```
 CREATE TABLE GROUPS (
       id          INT(11)      NOT NULL AUTO_INCREMENT PRIMARY KEY,
       groups_name VARCHAR(255) NOT NULL
     )ENGINE = INNODB;
 2  - Create table USERS :
 ```
 CREATE TABLE  USERS (
   id                  INT(11)     NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name                VARCHAR(20) NOT NULL,
   first_name          VARCHAR(20),
   nick_name           VARCHAR(20),
   email               VARCHAR(60) NOT NULL,
   nb_street           INT(5),
   address             VARCHAR(255),
   city                VARCHAR(255),
   zipcode             CHAR(5),
   password            CHAR(56)    NOT NULL,
   birth_day           DATE,
   preferences         VARCHAR(255),
   school_domain       VARCHAR(255),
   education_formation VARCHAR(255),
   image               VARCHAR(255),
   id_groups           INT(11),

   FOREIGN KEY (id_groups) REFERENCES GROUPS (id)
     ON DELETE CASCADE
     ON UPDATE CASCADE

 ) ENGINE = INNODB;

  3 - Create table CATEGORY :
  ```
  CREATE TABLE  CATEGORY (
    id              INT(11)      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    category_name   VARCHAR(255) NOT NULL,
    description     VARCHAR(255) NOT NULL,
    parent_category INT(11),
    type_category   INT(11),

    FOREIGN KEY (parent_category) REFERENCES CATEGORY (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE

  ) ENGINE = INNODB;

  4 - Create table STATE
  ```
  CREATE TABLE STATE (
    id         INT(11)      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    state_name VARCHAR(255) NOT NULL
  )ENGINE = INNODB;

  5 - Create table ADVERTS
  ```
  CREATE TABLE ADVERTS (
    id                 INT(11)      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title              VARCHAR(150) NOT NULL,
    description        VARCHAR(2048),
    image              VARCHAR(255),
    date_start         DATE         NOT NULL,
    date_end           DATE,
    simple_descritpion VARCHAR(255),
    id_users           INT(11),
    id_category        INT(11),
    id_state           INT(11),

    CONSTRAINT FK_ADVERT FOREIGN KEY (id_users) REFERENCES USERS (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,

    CONSTRAINT FK1_ADVERT FOREIGN KEY (id_category) REFERENCES CATEGORY (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,

    CONSTRAINT FK2_ADVERT FOREIGN KEY (id_state) REFERENCES STATE (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE

  )ENGINE = INNODB;

  6 - Create table APPLY
  ```
  CREATE TABLE APPLY (
    id         INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    start_date DATE    NOT NULL,
    end_date   DATE,
    accepted   BOOLEAN NOT NULL,
    id_user    INT(11),
    id_adverts INT(11),
    CONSTRAINT FK_APPLY FOREIGN KEY (id_user) REFERENCES USERS (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,

    CONSTRAINT FK1_APPLY FOREIGN KEY (id_adverts) REFERENCES ADVERTS (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE

  )ENGINE = INNODB;

  7 - Create table LOAN
  ```
  CREATE TABLE LOAN (
    id                INT(11)      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    loan_code         VARCHAR(255) NOT NULL,
    state_loan_code   BOOLEAN      NOT NULL,
    return_code       VARCHAR(255) NOT NULL,
    state_return_code BOOLEAN      NOT NULL,
    id_apply          INT,
    FOREIGN KEY (id_apply) REFERENCES APPLY (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
  )ENGINE = INNODB;

  8 - Create table OPINION
  ```
  CREATE TABLE OPINION
  (
    id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    mark        INT             NOT NULL,
    description VARCHAR(255),
    id_user     INT(11),
    FOREIGN KEY (id_user) REFERENCES USERS (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
  )ENGINE = INNODB;

  9 - Create table REPORTING
  ```
  CREATE TABLE REPORTING
  (
    id               INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    reason           VARCHAR(255)        NOT NULL,
    more_information VARCHAR(255)
  )ENGINE = INNODB;

  10 - Create table REPORTING_ADVERT
  ```
  CREATE TABLE REPORTING_ADVERT
  (
    id        INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_advert INT(11),
    FOREIGN KEY (id) REFERENCES REPORTING (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
    FOREIGN KEY (id_advert) REFERENCES ADVERTS (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
  )ENGINE = INNODB;

  11 - Create table REPORTING_USER
  ```
  CREATE TABLE REPORTING_USER
  (
    id       INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_users INT(11),
    FOREIGN KEY (id) REFERENCES REPORTING (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
    FOREIGN KEY (id_users) REFERENCES USERS (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
  )ENGINE = INNODB;

  12 - Create table WARNING
  ```
  CREATE TABLE WARNING
  (
    id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    reason      VARCHAR(255)    NOT NULL,
    descritpion VARCHAR(255),
    id_user     INT,
    FOREIGN KEY (id_user) REFERENCES USERS (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
  )ENGINE = INNODB;

  13 - Create table FRIENDS
  ```
  CREATE TABLE FRIENDS
  (
    id_user  INT NOT NULL,
    id_user1 INT NOT NULL,
    PRIMARY KEY (id_user, id_user1),
    FOREIGN KEY (id_user) REFERENCES USERS (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
    FOREIGN KEY (id_user1) REFERENCES USERS (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
  )ENGINE = INNODB;




User Stories
-------------------------------------------

1. En tant qu'utilisateur je souhaite pouvoir m'inscrire sur le site (V)
2. En tant qu'utilisateur je souhaite pouvoir me connecter sur le site (V)
3. En tant qu'utilisateur je souhaite pouvoir visualiser et modifier mes informations personnelles (X)
4. En tant qu'utilisateur je souhaite pouvoir visualiser les objets disponible sur le site (V)
5. En tant qu'utilisateur je souhaite pouvoir accéder à la fiche produit d'un objet (V)
6. En tant qu'utilisateur je souhaite pouvoir effectuer une demande de prêt (X)
7. En tant qu'utilisateur je souhaite pouvoir afficher mes prêts (X)
8. En tant qu'utilisateur je souhaite créer une offre de prêt (V)