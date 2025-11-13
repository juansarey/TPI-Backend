INSERT INTO tarifa (id_tarifa, costo_por_km, costo_base_fijo) VALUES
(1, 10.0, 50.0),
(2, 12.5, 60.0),
(3, 15.0, 75.0);

INSERT INTO camion (id_camion, nombre_transportista, capacidad_peso_kg, telefono, capacidad_volumen_m3, disponibilidad, costo_base_km) VALUES
(1, 'Joaquin', 10000.0, '555-1111', 60.0, true, 1.5),
(2, 'Juan', 15000.0, '555-2222', 80.0, true, 2.0),
(3, 'Lucas', 12000.0, '555-3333', 70.0, false, 1.8);

INSERT INTO cliente (id_cliente, nombre, apellido, telefono, direccion) VALUES
(1, 'Alberto', 'Perez', '555-1234', '123 Main St'),
(2, 'Maria', 'Gomez', '555-5678', '456 Elm St'),
(3, 'Carlos', 'Lopez', '555-8765', '789 Oak St');

INSERT INTO estado_contenedor(id_estado, nombre) VALUES
(1, 'Retirado de origen'),
(2, 'Entregado en deposito'),
(3, 'Retirado de deposito'),
(4, 'Entregado en destino'),
(5, 'Pendiente de despacho');

INSERT INTO contenedor(id_contenedor, peso_kg, volumen_m3, id_estado) VALUES
(1, 2000.0, 12.0, 1),
(2, 2500.0, 15.0, 2),
(3, 1800.0, 10.0, 3);

INSERT INTO deposito (id_deposito, nombre, direccion, latitud, longitud, costo_diario) VALUES
(1, 'Deposito Central', 'Av. Central 1000', -34.6037, -58.3816, 150.0),
(2, 'Deposito Norte', 'Calle Norte 2000', -34.5678, -58.4567, 120.0),
(3, 'Deposito Sur', 'Ruta Sur 3000', -34.6789, -58.5678, 130.0);