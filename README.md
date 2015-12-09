LAT — Loan A Thing
==================================================

What you need to build your own LAT Project
-------------------------------------------

1. Libraries
    Add embedded libraries to your IDE and your deployment plan :  
    ```
    (web/WEB-INF/lib)
    ```

2. SQL with MySQL
    - Change with your SGBD settings in :  
        ```
        src/com/lat/dao/dao.properties
        ```
    - Create database named lat :  
        ```
        create database lat;
        ```
    - Load dump :  
        ```
        web/WEB-INF/lib/dumpLAT.sql
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