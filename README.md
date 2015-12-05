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
- Create table user :
```
CREATE TABLE  user (
    id INT(11) NOT NULL AUTO_INCREMENT,
    email VARCHAR(60) NOT NULL,
    password CHAR(56) NOT NULL,
    name VARCHAR(20) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
) ENGINE = INNODB;
```

- Create table advert :
```
CREATE TABLE advert (
 id INT(11) NOT NULL AUTO_INCREMENT,
 title VARCHAR(150) NOT NULL,
 description VARCHAR(2048) NOT NULL,
 date_start DATE NOT NULL,
 date_end DATE,
 active BOOLEAN NOT NULL,
 PRIMARY KEY (id)
) ENGINE = INNODB;
```

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