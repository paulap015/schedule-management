
INSERT INTO area (area_id, area_nombre) VALUES ('1', 'Desarrollo de software');
INSERT INTO area (area_id, area_nombre) VALUES ('2', 'Gastronomia');
INSERT INTO area (area_id, area_nombre) VALUES ('3', 'Dimension ambiental');
INSERT INTO area (area_id, area_nombre) VALUES ('4', 'Maquinaria industrial');

INSERT INTO programa (prog_codigo, prog_nombre) VALUES ('1', 'Analisis De Glosa Y Cartera De Cuentas Medicas');
INSERT INTO programa (prog_codigo, prog_nombre) VALUES ('2', 'Analisis Y Desarrollo De Sistemas De Informacion');
INSERT INTO programa (prog_codigo, prog_nombre) VALUES ('3', 'Gestion Bancaria Y De Entidades Financieras');
INSERT INTO programa (prog_codigo, prog_nombre) VALUES ('4', 'Gestion Logística');
INSERT INTO programa (prog_codigo, prog_nombre) VALUES ('5', 'Implementacion De Sistemas De Informacion Geografica');
INSERT INTO programa (prog_codigo, prog_nombre) VALUES ('6', 'Mecanico De Maquinaria Industrial');
INSERT INTO programa (prog_codigo, prog_nombre) VALUES ('7', 'Financieras');

INSERT INTO competencia (comp_codigo, prog_codigo, comp_tipo, comp_nombre) VALUES ('1', '7', 'especifica', 'Analizar los resultados contables y financieros segun los criterios establecidos');
INSERT INTO competencia (comp_codigo, prog_codigo, comp_tipo, comp_nombre) VALUES ('2', '4', 'especifica', 'Operar herramientas ofimaticas y digitales de acuerdo con protocolosy manuales tecnicos.');
INSERT INTO competencia (comp_codigo, prog_codigo, comp_tipo, comp_nombre) VALUES ('3', '2', 'general', 'Orientar investigacion formativa segun referentes tecnicos.');
INSERT INTO competencia (comp_codigo, prog_codigo, comp_tipo, comp_nombre) VALUES ('4', '1', 'especifica', 'Administrar la cartera de credito de acuerdo con normas legales, institucionales');
INSERT INTO competencia (comp_codigo, prog_codigo, comp_tipo, comp_nombre) VALUES ('5', '3', 'general', 'Controlar sistema de gestion de acuerdo con normativa de seguridad y salud en el trabjao');
INSERT INTO competencia (comp_codigo, prog_codigo, comp_tipo, comp_nombre) VALUES ('6', '5', 'general', 'Promover acciones de prevencion de acuerdo con normativas y protocolos de gestión de riesgo de desastres');
INSERT INTO competencia (comp_codigo, prog_codigo, comp_tipo, comp_nombre) VALUES ('7', '6', 'especifica', 'Integrar sistema de automatizacion de acuerdo a procedimientos y requerimientos técnicos.');

INSERT INTO ambiente (amb_codigo, amb_nombre, amb_tipo_ambiente, amb_capacidad, amb_ubicacion) VALUES ('AA001', 'Salon fundacion del area andina', 'presencial', '40', 'zona norte');
INSERT INTO ambiente (amb_codigo, amb_nombre, amb_tipo_ambiente, amb_capacidad, amb_ubicacion) VALUES ('AA002', 'Salon fundadores region norte', 'presencial', '30', 'Popayan');
INSERT INTO ambiente (amb_codigo, amb_nombre, amb_tipo_ambiente, amb_capacidad, amb_ubicacion) VALUES ('AA003', 'Cocina especializada en frio', 'presencial', '30', 'zona este');
INSERT INTO ambiente (amb_codigo, amb_nombre, amb_tipo_ambiente, amb_capacidad, amb_ubicacion) VALUES ('AA004', 'Salon de operaciones industriales', 'presencial', '40', 'Popayan');
INSERT INTO ambiente (amb_codigo, amb_nombre, amb_tipo_ambiente, amb_capacidad, amb_ubicacion) VALUES ('BB001', 'Salon virtual', 'virtual', '50', 'Google Meet');

INSERT INTO usuario (us_id, us_tipo_id, us_nombre, us_apellido, us_rol,us_password,username) VALUES ('1000444666', 'CC', 'Fernanda Karin', 'Chavez Alegria', 'admin','123','admin');
INSERT INTO usuario (us_id, us_tipo_id, us_nombre, us_apellido, us_rol,us_password,username) VALUES ('111555999', 'CC', 'Camilo Andres', 'Piedra Cañon', 'docente','123','camiloP');
INSERT INTO usuario (us_id, us_tipo_id, us_nombre, us_apellido, us_rol,us_password,username) VALUES ('555000111', 'CC', 'Andrea', 'Ramirez', 'docente','123','andrea');

INSERT INTO cordinador (us_id, cor_username,cor_fecha_inicio) VALUES ('1000444666', 'admin', '2021-11-01 11:30:21');

INSERT INTO docente (us_id, AREA_ID, prog_codigo, doc_tipo_docente, doc_tipo_contrato, doc_horas_realizadas,doc_is_available) VALUES ('111555999', '4', '6', 'profesional', 'PT','0','');
INSERT INTO docente (us_id, AREA_ID, prog_codigo, doc_tipo_docente, doc_tipo_contrato, doc_horas_realizadas,doc_is_available) VALUES ('555000111', '2', '1', 'profesional', 'CNT','0','');
