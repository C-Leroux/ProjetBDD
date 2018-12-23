Lancer notre projet : Gestion Planning


Etape 1:

Pour telecharger la base de donnee dans Eclipse :
- telecharger AMPPS
- une fois l'application ouverte, lancer Apache et MySQL si ce n'est pas deja fait
- cliquer sur "Home"
- dans la categorie Database Tools, cliquer sur phpMyAdmin
- une fenetre avec phpMyAdmin s'ouvre, cliquer sur "Databases" (ou "Base de donnees") en haut a gauche de l'ecran
- aller dans create database, donner le nom de "tmp", puis cliquer sur create
- cliquer sur tmp
- cliquer sur "Import"
- choisir le script SQL (script_Grp3.sql), puis cliquer sur "Go"


Etape 2:

Configuration dans Eclispe :
- aller sur ce lien : https://dev.mysql.com/downloads/file/?id=480291
- telecharger la library (qui sert a se connecter a la base de donnees)


Etape 3:

Configuration dans le fichier DbConnexion.java de notre projet : 
- dans le champs "utilisateur" : entrer votre login AMPPS (par defaut : root)
- dans le champs "motDePasse" : entrer votre mot de passe AMPPS (par defaut : mysql)


Etape 4:

- cliquer sur le Main.java dans le package main
- cliquer sur run pour lancer l'interface graphique
- la creation des eleves, des cours, des groupes, des salles, des professeurs, des administrateurs est deja faite
