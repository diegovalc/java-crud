CREATE DATABASE IF NOT EXISTS clientes;
USE clientes;

CREATE TABLE tipo_cliente (
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    created_by VARCHAR(50) DEFAULT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP () ON UPDATE CURRENT_TIMESTAMP (),
    updated_by VARCHAR(50) DEFAULT NULL,
    version INT(10) DEFAULT 0
);

INSERT INTO tipo_cliente (descripcion) VALUES ('Bronce');
INSERT INTO tipo_cliente (descripcion) VALUES ('Plata');
INSERT INTO tipo_cliente (descripcion) VALUES ('Oro');

select * from tipo_cliente ;

CREATE TABLE cliente (
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombre VARCHAR(100) DEFAULT NULL,
    email VARCHAR(100) DEFAULT NULL,
    telefono VARCHAR(10) DEFAULT NULL,
    tipo_cliente INT(11) DEFAULT NULL,
    FOREIGN KEY (tipo_cliente)
        REFERENCES tipo_cliente (id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP (),
    created_by VARCHAR(50) DEFAULT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP () ON UPDATE CURRENT_TIMESTAMP (),
    updated_by VARCHAR(50) DEFAULT NULL,
    version INT(10) DEFAULT 0
);

INSERT INTO cliente (nombre, email, telefono, tipo_cliente) VALUES ('Juan Perez', 'juan@correo.com', '78491236', 1 );

select * from cliente;

drop database clientes;

