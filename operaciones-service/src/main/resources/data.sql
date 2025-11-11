-- Datos iniciales para el microservicio de Operaciones
-- Tablas: estado_tramo, tipo_tramo, solicitud, ruta y tramo

-- Estados de tramo (pendiente, en curso, finalizado)
INSERT INTO estado_tramo (id, nombre, descripcion) VALUES
  (1, 'PENDIENTE', 'El tramo está estimado pero aún no ha comenzado'),
  (2, 'EN_CURSO',  'El tramo se encuentra actualmente en ejecución'),
  (3, 'FINALIZADO','El tramo ha sido completado');

-- Tipos de tramo de acuerdo con el enunciado (origen‑depósito, depósito‑depósito, depósito‑destino, origen‑destino)
INSERT INTO tipo_tramo (id, nombre, descripcion) VALUES
  (1, 'ORIGEN_A_DEPOSITO',    'Traslado desde el origen hasta el primer depósito'),
  (2, 'DEPOSITO_A_DEPOSITO',  'Traslado entre depósitos intermedios'),
  (3, 'DEPOSITO_A_DESTINO',   'Traslado desde el último depósito al destino final'),
  (4, 'ORIGEN_A_DESTINO',     'Traslado directo desde el origen al destino, sin depósitos');

-- Solicitudes de transporte (referencian lógicamente al cliente, contenedor y tarifa mediante sus identificadores de Maestros)
INSERT INTO solicitud (id, cliente_id, contenedor_id, tarifa_id, costo_estimado, tiempo_estimado_min, costo_final, tiempo_real_min) VALUES
  (1, 101, 1001, 10, 5000.00, 600, 5200.00, 650),
  (2, 102, 1002, 11, 8000.00, 960, NULL,     NULL);

-- Rutas asociadas a cada solicitud (1:1)
INSERT INTO ruta (id, cantidad_tramos, cantidad_depositos, id_solicitud) VALUES
  (1, 2, 1, 1),
  (2, 3, 2, 2);

-- Tramos correspondientes a la Ruta 1 (Solicitud 1)
INSERT INTO tramo (id, camion_ref, origen_deposito_ref, destino_deposito_ref,
                   distancia_m, duracion_sec, costo_aprox, costo_real,
                   fecha_inicio_estimada, fecha_inicio,
                   fecha_fin_estimada,    fecha_fin,
                   id_ruta, id_tipo_tramo, id_estado)
VALUES
  -- Primer tramo de la ruta 1: del origen al depósito
  (1, 201, NULL,       301, 150000.0,  7200, 2000.0, 2100.0,
   '2025-10-01', '2025-10-02', '2025-10-03', '2025-10-03',
   1, 1, 3),
  -- Segundo tramo de la ruta 1: del depósito al destino
  (2, 202, 301,        NULL, 300000.0, 14400, 3000.0, 3100.0,
   '2025-10-03', '2025-10-03', '2025-10-05', '2025-10-05',
   1, 3, 3);

-- Tramos correspondientes a la Ruta 2 (Solicitud 2)
INSERT INTO tramo (id, camion_ref, origen_deposito_ref, destino_deposito_ref,
                   distancia_m, duracion_sec, costo_aprox, costo_real,
                   fecha_inicio_estimada, fecha_inicio,
                   fecha_fin_estimada,    fecha_fin,
                   id_ruta, id_tipo_tramo, id_estado)
VALUES
  -- Primer tramo de la ruta 2: origen al primer depósito
  (3, 203, NULL,       302, 100000.0, 3600, 1200.0, NULL,
   '2025-11-01', NULL, '2025-11-02', NULL,
   2, 1, 1),
  -- Segundo tramo de la ruta 2: entre depósitos
  (4, 204, 302,        303, 200000.0, 7200, 2500.0, NULL,
   '2025-11-02', NULL, '2025-11-04', NULL,
   2, 2, 1),
  -- Tercer tramo de la ruta 2: depósito al destino
  (5, 205, 303,        NULL, 250000.0, 9000, 3500.0, NULL,
   '2025-11-04', NULL, '2025-11-06', NULL,
   2, 3, 1);