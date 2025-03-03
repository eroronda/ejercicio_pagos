DROP USER IF EXISTS 'pagos'@'%';
CREATE USER 'pagos'@'%' IDENTIFIED BY 'p4g05.$';
GRANT ALL PRIVILEGES ON pagos.* TO 'pagos'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;


CREATE DATABASE `pagos`

-- Tabla persona
CREATE TABLE persona (
    id_persona INT PRIMARY KEY AUTO_INCREMENT,            -- Identificador unico de la persona
    nombre VARCHAR(100) NOT NULL,                          -- Nombre de la persona
    apellido_paterno VARCHAR(100) NOT NULL,                -- Apellido Paterno de la persona
    apellido_materno VARCHAR(100) NOT NULL,                -- Apellido Materno de la persona
    email VARCHAR(150) UNIQUE NOT NULL,                    -- Correo electronico unico
    telefono VARCHAR(15),                                  -- Número de telefono (opcional)
    direccion VARCHAR(255),                                 -- Direccion de la persona (opcional)
    fecha_nacimiento DATE,                                  -- Fecha de nacimiento
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP      -- Fecha de registro
);

-- Tabla roles_pago
CREATE TABLE roles_pago (
    id_rol INT PRIMARY KEY AUTO_INCREMENT,                 -- Identificador unico del rol
    nombre_rol ENUM('EMISOR', 'RECEPTOR') NOT NULL          -- Nombre del rol (EMISOR o RECEPTOR)
);

-- Tabla emisores_pago
CREATE TABLE emisores_pago (
    id INT PRIMARY KEY AUTO_INCREMENT,                     -- Identificador unico
    id_persona INT NOT NULL,                                -- ID de la persona (relacionado con la tabla persona)
    id_rol INT NOT NULL,                                    -- ID del rol (relacionado con la tabla roles_pago)
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona),   -- Relacion con la tabla persona
    FOREIGN KEY (id_rol) REFERENCES roles_pago(id_rol)  -- Relacion con la tabla roles_pago
);

-- Tabla receptores_pago
CREATE TABLE receptores_pago (
    id INT PRIMARY KEY AUTO_INCREMENT,                     -- Identificador unico
    id_persona INT NOT NULL,                                -- ID de la persona (relacionado con la tabla persona)
    id_rol INT NOT NULL,                                    -- ID del rol (relacionado con la tabla roles_pago)
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona),   -- Relación con la tabla persona
    FOREIGN KEY (id_rol) REFERENCES roles_pago(id_rol)  -- Relacion con la tabla roles_pago
);

-- Tabla estatus_pago
CREATE TABLE estatus_pago (
    id_estatus INT PRIMARY KEY AUTO_INCREMENT,             -- Identificador unico del estatus
    nombre_estatus ENUM('PENDIENTE', 'APROBADO', 'RECHAZADO') NOT NULL  -- Estado del pago
);

-- Tabla pagos
CREATE TABLE pagos (
    id_pago INT PRIMARY KEY AUTO_INCREMENT,         -- Identificador unico del pago
    id_estatus INT NOT NULL,                                -- Estado del pago (relacionado con la tabla estatus_pago)
    id_emisor_pago INT NOT NULL,                            -- Quien emite el pago, se relaciona con la tabla emisores_pago
    id_receptor_pago INT NOT NULL,                          -- Quien recibe el pago, se relaciona con la tabla receptores_pago
    cantidad_productos INT NOT NULL,                        -- Cantidad de productos involucrados en el pago
    monto DECIMAL(10, 2) NOT NULL,                          -- Monto involucrado en el pago
    concepto VARCHAR(100) NOT NULL,                         -- Concepto del pago
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,     -- Fecha de creacion del pago
    FOREIGN KEY (id_estatus) REFERENCES estatus_pago(id_estatus),   -- Relacion con la tabla estatus_pago
    FOREIGN KEY (id_emisor_pago) REFERENCES emisores_pago(id),       -- Relacion con la tabla emisores_pago
    FOREIGN KEY (id_receptor_pago) REFERENCES receptores_pago(id)    -- Relacion con la tabla receptores_pago
);


-- en caso de tener necesidad de borrar tablas
-- drop table pagos;
-- drop table emisores_pago;
-- drop table receptores_pago;
-- drop table estatus_pago;
-- drop table roles_pago;
-- drop table persona;

