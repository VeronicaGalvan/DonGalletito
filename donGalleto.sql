drop database if exists donGalleto;
create database donGalleto;
use donGalleto;

CREATE TABLE Usuarios (
    idUsuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL
);

    
INSERT INTO Usuarios (usuario, contrasena) VALUES
('dongalleto', '1234');

select * from Usuarios;

create table recetas(
  idReceta INT AUTO_INCREMENT PRIMARY KEY,
  nombreReceta varchar(255) not null,
  receta LONGTEXT
);

select * from recetas;