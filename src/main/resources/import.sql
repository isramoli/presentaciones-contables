
INSERT INTO `usuarios` (nombre, password, tipo_usuario) VALUES ('admin','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 'ROLE_FISCAL_GENERAL');
INSERT INTO `usuarios` (nombre, password, tipo_usuario) VALUES ('isra','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 'ROLE_ADMIN');

INSERT INTO `usuarios` (nombre, password, tipo_usuario) VALUES ('Cuentadan1','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG', 'ROLE_CUENTADANTE');
INSERT INTO `usuarios` (nombre, password, tipo_usuario) VALUES ('Cuentadan2','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG', 'ROLE_CUENTADANTE');
INSERT INTO `usuarios` (nombre, password, tipo_usuario) VALUES ('Cuentadan3','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG', 'ROLE_CUENTADANTE');
INSERT INTO `usuarios` (nombre, password, tipo_usuario) VALUES ('Fiscal1','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 'ROLE_FISCAL');
INSERT INTO `usuarios` (nombre, password, tipo_usuario) VALUES ('Fiscal2','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 'ROLE_FISCAL');
INSERT INTO `usuarios` (nombre, password, tipo_usuario) VALUES ('Fiscal3','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 'ROLE_FISCAL');
INSERT INTO `usuarios` (nombre, password, tipo_usuario) VALUES ('Fgeneral1','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 'ROLE_FISCAL_GENERAL');
INSERT INTO `usuarios` (nombre, password, tipo_usuario) VALUES ('Fgeneral2','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 'ROLE_FISCAL_GENERAL');
INSERT INTO `usuarios` (nombre, password, tipo_usuario) VALUES ('Fgeneral3','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 'ROLE_FISCAL_GENERAL');
INSERT INTO `usuarios` (nombre, password, tipo_usuario) VALUES ('Admin1','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 'ROLE_ADMIN');
INSERT INTO `usuarios` (nombre, password, tipo_usuario) VALUES ('Admin2','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 'ROLE_ADMIN');
INSERT INTO `usuarios` (nombre, password, tipo_usuario) VALUES ('Admin3','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', 'ROLE_ADMIN');

insert into `municipios` (categoria, cuentadante_nombre, nombre) values (100, 'Cuentadan1', 'Municipio1');
insert into `municipios` (categoria, cuentadante_nombre, nombre) values (200, 'Cuentadan2', 'Municipio2');
insert into `municipios` (categoria, cuentadante_nombre, nombre) values (300, 'Cuentadan3', 'Municipio3');


insert into convocatoria (abierta, descripcion, documentacion_respaldatoria_requerida, fecha_apertura, fecha_cierre, nombre) values (1, 'Esta es la convocatoria 2', 3, PARSEDATETIME('01 Jan 2021, 00:00:00 AM', 'dd MMM yyyy, hh:mm:ss a', 'en'), PARSEDATETIME('01 Jan 2022, 00:00:00 AM', 'dd MMM yyyy, hh:mm:ss a', 'en'), 'Convocatoria2');


-- INSERT INTO `authorities` (clave_usuario, authority) VALUES ('andres','ROLE_USER');
-- INSERT INTO `authorities` (clave_usuario, authority) VALUES ('admin','ROLE_ADMIN');
-- INSERT INTO `authorities` (clave_usuario, authority) VALUES ('admin','ROLE_USER');
-- INSERT INTO `authorities` (clave_usuario, authority) VALUES ('isra','ROLE_ADMIN');
