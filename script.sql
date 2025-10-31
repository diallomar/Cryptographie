-- Suppression de la base de données si elle existe déjà
DROP DATABASE IF EXISTS oumardiallo;

-- Création de la base de données
CREATE DATABASE oumardiallo;

-- Selection de la base de données
USE oumardiallo;

-- Creation de la table "utilisateurs"
CREATE TABLE utilisateurs (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE, -- La colonne utilisateur doit etre unique
    password VARCHAR(500) NOT NULL
);

