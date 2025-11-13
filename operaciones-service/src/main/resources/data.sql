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
INSERT INTO solicitud (id, cliente_id, contenedor_id, tarifa_id, costo_estimado, tiempo_estimado) VALUES
  (1, 1, 1, 1, 5000.00, 15.5),
  (2, 2, 2, 2, 8000.00, 20.0);

-- Rutas asociadas a cada solicitud (1:1)
INSERT INTO ruta (id, cantidad_tramos, cantidad_depositos, id_solicitud) VALUES
  (1, 2, 1, 1),
  (2, 3, 2, 2);

-- Tramos correspondientes a la Ruta 1 (Solicitud 1)
INSERT INTO tramo (id, camion_ref, origen, destino, costo_aprox, costo_real,
                   fecha_hora_inicio_estimada, fecha_hora_inicio,
                   fecha_hora_fin_estimada,    fecha_hora_fin,
                   id_ruta, id_tipo_tramo, id_estado)
VALUES
  -- Primer tramo de la ruta 1: origen al depósito
  (1, 201, 'Origen A', 'Deposito Central', 2000.0, NULL,
   '2025-10-01 08:00:00', NULL, '2025-10-02 08:00:00', NULL,
   1, 1, 1),
  -- Segundo tramo de la ruta 1: depósito al destino
  (2, 202, 'Deposito Central', 'Destino A', 3000.0, NULL,
   '2025-10-02 09:00:00', NULL, '2025-10-03 09:00:00', NULL,
   1, 3, 1);
