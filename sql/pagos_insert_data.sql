-- datos demo para tabla persona
INSERT INTO persona (nombre, apellido_paterno, apellido_materno, email, telefono, direccion, fecha_nacimiento)
VALUES
('Juan', 'Pérez', 'Gómez', 'juan.perez@example.com', '555-1234', 'Calle Ficticia 123', '1985-05-10'),
('María', 'López', 'Martínez', 'maria.lopez@example.com', '555-5678', 'Avenida Real 456', '1990-08-25'),
('Carlos', 'García', 'Ramírez', 'carlos.garcia@example.com', '555-9876', 'Boulevard Central 789', '1982-11-30');

-- insert para catalago roles
INSERT INTO roles_pago (nombre_rol)
VALUES
('EMISOR'),
('RECEPTOR');

-- registo de personas con rol pagador
INSERT INTO emisores_pago (id_persona, id_rol) VALUES (1, 1);  -- Juan Pérez es el EMISOR
INSERT INTO emisores_pago (id_persona, id_rol) VALUES (3, 1);  -- Carlos García es el EMISOR

-- registo de personas con rol receptor
INSERT INTO receptores_pago (id_persona, id_rol) VALUES (2, 2);  -- María López es la RECEPTORA
INSERT INTO receptores_pago (id_persona, id_rol) VALUES (3, 2);  -- Carlos García también es RECEPTOR (por ejemplo, en otro pago)


-- insert para catalago estatus_pago
INSERT INTO estatus_pago (nombre_estatus)
VALUES
('PENDIENTE'),
('APROBADO'),
('RECHAZADO');

-- datos demo para tabla transaccion que sirve para hacer CRUD de pagos
INSERT INTO pagos (id_estatus, id_emisor_pago, id_receptor_pago, cantidad_productos, monto, concepto) VALUES (1, 1, 2, 3, 150.00, 'Compra de productos electrónicos');
INSERT INTO pagos (id_estatus, id_emisor_pago, id_receptor_pago, cantidad_productos, monto, concepto) VALUES (2, 2, 2, 1, 45.50, 'Pago de servicio de internet');
INSERT INTO pagos (id_estatus, id_emisor_pago, id_receptor_pago, cantidad_productos, monto, concepto) VALUES (3, 1, 2, 5, 200.00, 'Pago de alquiler de oficina');