/* Populate tabla clientes */
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Cesar', 'Toribio', 'ctoribio@sample.com', '2018-11-01');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Mr. John', 'Doe', 'john.doe@gmail.com', '2018-01-02');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2018-01-03');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2018-02-01');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2018-02-10');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2018-02-18');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2018-02-28');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Dr. James', 'Gosling', 'james.gosling@gmail.com', '2018-03-03');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Jade', 'Doe', 'jane.doe@gmail.com', '2018-03-06');

/*    the same test password for both users = P@ssw0rd!     */

INSERT INTO `user`( `username`,`enabled`,`password`) VALUES
('cesar',1,'$2a$10$wzigki/z51JMjBgUr/xoAun8CbH5P.XKKoSRF6Jq8z9/ypZSHcXGO')
,('admin',1,'$2a$10$jiZuOCwN/GNuVQz6w2EU.eibIPZfPNlOZc1fPASbFpodQFMfmFjpy');

INSERT INTO `role`(`name`) VALUES ('ROLE_USER'),('ROLE_ADMIN');

INSERT INTO `user_roles`(`user_id`,`roles_id`) VALUES (1,1),(2,1),(2,2);
