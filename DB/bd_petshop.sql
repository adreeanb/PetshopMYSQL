CREATE DATABASE IF NOT EXISTS bd_petshop;
USE bd_petshop;

CREATE TABLE IF NOT EXISTS Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS Animal (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    especie VARCHAR(100) NOT NULL,
    idade INT,
    peso DECIMAL(5, 2),
    tipo_animal ENUM('Cachorro', 'Gato') NOT NULL
);

CREATE TABLE IF NOT EXISTS Cachorro (
    animal_id INT PRIMARY KEY,
    raca VARCHAR(100),
    tem_carteira_vacinacao BOOLEAN,
    FOREIGN KEY (animal_id) REFERENCES Animal(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Gato (
    animal_id INT PRIMARY KEY,
    raca VARCHAR(100),
    gosta_de_arranhar BOOLEAN,
    FOREIGN KEY (animal_id) REFERENCES Animal(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    duracao_estimada_min INT
);

CREATE TABLE IF NOT EXISTS Atendimento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    animal_id INT NOT NULL,
    servico_id INT NOT NULL,
    data_hora DATETIME NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id),
    FOREIGN KEY (animal_id) REFERENCES Animal(id),
    FOREIGN KEY (servico_id) REFERENCES Servico(id)
);

