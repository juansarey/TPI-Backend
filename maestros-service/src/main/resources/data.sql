-- =================================================================
-- CLIENTES
-- (Campos ERD: public_id, nombre, apellido, email, telefono, direccion)
-- =================================================================
INSERT INTO cliente (public_id, nombre, apellido, email, telefono, direccion) VALUES
(UUID(), 'Juan', 'Pérez', 'juan@example.com', '111-2222', 'Av. Siempre Viva 123'),
(UUID(), 'Ana', 'Gómez', 'ana@example.com', '333-4444', 'Calle Falsa 456'),
(UUID(), 'Carlos', 'Díaz', 'carlos@example.com', '555-6666', 'Ruta 7 km 102'),
(UUID(), 'Laura', 'Torres', 'laura@example.com', '777-8888', 'Av. Corrientes 2400');

-- =================================================================
-- CAMIONES
-- (Campos ERD: public_id, dominio, nombre_transportista, capacidad_peso_kg, capacidad_volumen_m3, disponibilidad)
-- =================================================================
INSERT INTO camion (public_id, dominio, nombre_transportista, capacidad_peso_kg, capacidad_volumen_m3, disponibilidad, costo_base_km, consumo_1_km) VALUES
(UUID(), 'AA123AA', 'Transportes Veloz', 12000.0, 90.0, TRUE, 150.0, 0.3),
(UUID(), 'AB456BC', 'Logística Sur', 15000.0, 100.0, FALSE, 180.0, 0.4),
(UUID(), 'AC789CD', 'Cargas Rápidas', 8000.0, 60.0, TRUE, 130.0, 0.25),
(UUID(), 'AD012DE', 'Fletes Express', 2000.0, 1000.0, TRUE, 200.0, 0.5);

-- =================================================================
-- DEPÓSITOS
-- (Campos ERD: public_id, nombre, direccion, latitud, longitud, costo_diario)
-- =================================================================
INSERT INTO deposito (public_id, nombre, direccion, latitud, longitud, costo_diario) VALUES
(UUID(), 'Depósito Córdoba Centro', 'Av. Siempre Viva 123', -31.4201, -64.1888, 500.0),
(UUID(), 'Depósito Rosario Norte', 'Calle Falsa 456', -32.9511, -60.6677, 450.0),
(UUID(), 'Depósito Mendoza Este', 'Ruta 7 km 102', -32.8915, -68.8279, 480.0),
(UUID(), 'Depósito CABA Central', 'Av. Corrientes 2400', -34.6045, -58.3821, 600.0),
(UUID(), 'Depósito Salta', 'Mitre 321', -24.7850, -65.4238, 400.0);

-- =================================================================
-- TARIFAS
-- (Campos ERD: public_id, descripcion, rangos..., costos...)
-- =================================================================
INSERT INTO tarifa (public_id, descripcion, rango_peso_min_kg, rango_peso_max_kg, rango_volumen_min_m3, rango_volumen_max_m3, costo_base_fijo, costo_km_base) VALUES
(UUID(), 'Tarifa Liviana Local', 0, 1000, 0, 10, 500.0, 10.0),
(UUID(), 'Tarifa Media Distancia', 1001, 5000, 10.1, 50, 1000.0, 8.5),
(UUID(), 'Tarifa Pesada Larga Distancia', 5001, 20000, 50.1, 100, 2500.0, 7.0);

-- =================================================================
-- CONTENEDORES
-- (Campos ERD: public_id, identificacion_unica, peso_kg, volumen_m3, estado, id_cliente)
-- IDs de cliente (1=Juan, 2=Ana, 3=Carlos, 4=Laura)
-- =================================================================
INSERT INTO contenedor (public_id, identificacion_unica, peso_kg, volumen_m3, estado, id_cliente) VALUES
(UUID(), 'CONT-001', 500.0, 4.0, 'Retirado de origen', 1),
(UUID(), 'CONT-002', 800.0, 6.5, 'Entregado en depósito', 2),
(UUID(), 'CONT-003', 1200.0, 8.0, 'Retirado de depósito', 3),
(UUID(), 'CONT-004', 700.0, 5.0, 'Retirado de origen', 4),
(UUID(), 'CONT-005', 900.0, 20.0, 'Retirado de origen', 2), -- Ajustado volumen de 200000 a 20.0
(UUID(), 'CONT-006', 10000.0, 40.0, 'Retirado de origen', 1), -- Ajustado peso de 1000000 a 10000.0
(UUID(), 'CONT-007', 1300.0, 9.0, 'Entregado en destino', 1),
(UUID(), 'CONT-008', 950.0, 6.8, 'Entregado en destino', 2),
(UUID(), 'CONT-009', 620.0, 4.0, 'Pendiente de despacho', 1),
(UUID(), 'CONT-010', 10.0, 10.0, 'Pendiente de despacho', 1);