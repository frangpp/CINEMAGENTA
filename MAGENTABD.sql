-- Crear base de datos
CREATE DATABASE IF NOT EXISTS Cine_DB;
USE Cine_DB;

-- Crear tabla Cartelera con todos los campos requeridos
CREATE TABLE IF NOT EXISTS Cartelera (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    director VARCHAR(50) NOT NULL,
    año INT NOT NULL,
    duracion INT NOT NULL,
    genero ENUM('Comedia', 'Drama', 'Acción', 'Terror', 'Ciencia Ficción', 'Romance') NOT NULL
);

-- Insertar datos de prueba (OPCIONAL - para testing)
INSERT INTO Cartelera (titulo, director, año, duracion, genero) VALUES
('Avengers: Endgame', 'Hermanos Russo', 2019, 181, 'Ciencia Ficción'),
('Toy Story 4', 'Josh Cooley', 2019, 100, 'Comedia'),
('Joker', 'Todd Phillips', 2019, 122, 'Drama');

SELECT * FROM Cartelera;