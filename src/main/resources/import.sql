INSERT INTO `users` (username, password, enabled, name, lastname, email) VALUES ('maria','pass123',1, 'Maria', 'Alzate','maria@ejemplo.com');
INSERT INTO `users` (username, password, enabled, name, lastname, email) VALUES ('admin','pass123',1, 'Admin', 'Prueba','admin@ejemplo.com');

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `users_roles` (user_id, role_id) VALUES (1, 1);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 2);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 1);
