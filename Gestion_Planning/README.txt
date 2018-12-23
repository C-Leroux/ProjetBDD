Lancer notre projet : Gestion projet


Etape 1:

Pour télécharger la base de donnée dans eclipse:
- télécharger AMPPS
- cliquer sur lancer Apache et MySQL
- cliquer sur home
- Dans la categorie Database Tools cliquer sur phpMyAdmin
- Une fenetre avec phpMyAdmin s'ouvre, cliquer sur Databases en haut a gauche de l'ecran
- aller dans create database, donner le nom de tmp. Puis cliquer sur create
- cliquer sur tmp
- cliquer sur import
- choisir le script SQl (script_Grp3.sql), puis cliquer sur "Go"

Etape 2:
Configuration dans eclispe :
- sur internet allerau lien ci-dessous : https://dev.mysql.com/downloads/file/?id=480291
  afin de telecharger la librairy qui sert à se connecter à la base de donnée
- 
-
-

Etape 3:
Configuration dans le fichier DbConnexion: 
- dans le champs url : mettre votre url
- dans le champs utilisateur : entrer votre login 
(si lors de l'installation de AMMPS vous ne l'avez pas changé,
par défaut, c'est : root)
- dans le champs motDePasse : entrer votre mot de passe
(si lors de l'installation de AMMPS vous ne l'avez pas changé,
par défaut, c'est : mysql)

Etape 4: 
- cliquer sur le Main.java dans le package main
- cliquer sur run pour lancer l'interface graphique
