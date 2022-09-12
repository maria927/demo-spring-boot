INSERT INTO `users` (username, password, enabled, name, lastname, email) VALUES ('maria','$2a$10$6C2FVIQSoa99sGeDuEo0tO8WSPMV5C2CTQlV3e5EXV32JlMSeJKmW',1, 'Maria', 'Alzate','maria@ejemplo.com');
INSERT INTO `users` (username, password, enabled, name, lastname, email) VALUES ('admin','$2a$10$htbIOfm/Wkk1MYS1rtEDsOHg2wEEIXApDaCkLM7cJ7vjZACNmeMDS',1, 'Admin', 'Prueba','admin@ejemplo.com');

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `users_roles` (user_id, role_id) VALUES (1, 1);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 2);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 1);
