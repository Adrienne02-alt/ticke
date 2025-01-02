-- Base de données pour le système de gestion de ticketing
-- SGBD : PostgreSQL

-- Création de la base de données
CREATE DATABASE ticketing_system;

-- Connexion à la base de données
\c ticketing_system;

-- Table : Utilisateurs
CREATE TABLE utilisateurs (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    role VARCHAR(50) CHECK (role IN ('employe', 'technicien', 'administrateur')) NOT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table : Tickets
CREATE TABLE tickets (
    id SERIAL PRIMARY KEY,
    titre VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    categorie VARCHAR(100),
    priorite VARCHAR(50) CHECK (priorite IN ('basse', 'moyenne', 'haute')) NOT NULL,
    statut VARCHAR(50) CHECK (statut IN ('ouvert', 'en_cours', 'ferme', 'reouvert')) DEFAULT 'ouvert',
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_mise_a_jour TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deadline TIMESTAMP,
    employe_id INT REFERENCES utilisateurs(id) ON DELETE CASCADE,
    technicien_id INT REFERENCES utilisateurs(id) ON DELETE SET NULL,
    pourcentage int
);

-- Table : Notations
CREATE TABLE notations (
    id SERIAL PRIMARY KEY,
    ticket_id INT REFERENCES tickets(id) ON DELETE CASCADE,
    technicien_id INT REFERENCES utilisateurs(id) ON DELETE CASCADE,
    note INT CHECK (note BETWEEN 1 AND 5),
    commentaire TEXT,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table : Historique des tickets
CREATE TABLE historique_tickets (
    id SERIAL PRIMARY KEY,
    ticket_id INT REFERENCES tickets(id) ON DELETE CASCADE,
    action VARCHAR(255) NOT NULL,
    utilisateur_id INT REFERENCES utilisateurs(id) ON DELETE SET NULL,
    date_action TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table : Rapports
CREATE TABLE rapports (
    id SERIAL PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    contenu TEXT,
    tickets int REFERENCES tickets(id) on delete CASCADE,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    createur_id INT REFERENCES utilisateurs(id) ON DELETE SET NULL
);

create table categories(
    id serial primary key,
    nom varchar(300)
);

create table reouvertures(
    tickets int REFERENCES tickets(id) on delete CASCADE,
    utilisateurs int REFERENCES utilisateurs(id) on delete CASCADE,
    commentaire TEXT,
    date_reouverture TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index pour accélérer les recherches
CREATE INDEX idx_tickets_statut ON tickets(statut);
CREATE INDEX idx_tickets_date_creation ON tickets(date_creation);
CREATE INDEX idx_historique_date_action ON historique_tickets(date_action);
