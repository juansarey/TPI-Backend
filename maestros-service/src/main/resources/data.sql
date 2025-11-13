INSERT INTO tarifa (costo_por_km, costo_base_fijo) VALUES
(10.0, 50.0),
(12.5, 60.0),
(15.0, 75.0);

INSERT INTO camion (nombre_transportista, capacidad_peso_kg, telefono, capacidad_volumen_m3, disponibilidad, costo_base_km) VALUES
('Joaquin', 10000.0, '555-1111', 60.0, true, 1.5),
('Juan', 15000.0, '555-2222', 80.0, true, 2.0),
('Lucas', 12000.0, '555-3333', 70.0, false, 1.8);

INSERT INTO cliente (nombre, apellido, telefono, direccion) VALUES
('Alberto', 'Perez', '555-1234', '123 Main St'),
('Maria', 'Gomez', '555-5678', '456 Elm St'),
('Carlos', 'Lopez', '555-8765', '789 Oak St');

INSERT INTO estado_contenedor(nombre) VALUES
('Retirado de origen'),
('Entregado en deposito'),
('Retirado de deposito'),
('Entregado en destino'),
('Pendiente de despacho');

INSERT INTO contenedor(peso_kg, volumen_m3, id_estado_contenedor) VALUES
(2000.0, 12.0, 1),
(2500.0, 15.0, 2),
(1800.0, 10.0, 3);

INSERT INTO deposito (nombre, direccion, latitud, longitud, costo_diario) VALUES
('Deposito Central', 'Av. Central 1000', -34.6037, -58.3816, 150.0),
('Deposito Norte', 'Calle Norte 2000', -34.5678, -58.4567, 120.0),
('Deposito Sur', 'Ruta Sur 3000', -34.6789, -58.5678, 130.0);