INSERT INTO user (user_id, user_username, user_password, user_email, user_native_language, user_enabled) VALUES (2, 'johnny', 'password', 'rniziolek@hotmail.com', 'PL', 1);

INSERT INTO role (role_id, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO role (role_id, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
