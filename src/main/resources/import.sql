INSERT INTO user (user_id, user_username, user_password, user_email, user_enabled) VALUES (2, 'johnny', 'password', 'rniziolek@hotmail.com', 1);

INSERT INTO role (role_id, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO role (role_id, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_role (user_id, role_id) VALUES (2, 2);


INSERT INTO word (word_id, word_text) VALUES (1, 'test1')
INSERT INTO word (word_id, word_text) VALUES (2, 'test2')
INSERT INTO word (word_id, word_text) VALUES (3, 'test3')
INSERT INTO user_word (user_word_id, word_id, user_word_amount, user_id) VALUES (1, 1, 13, 2)
INSERT INTO website (website_id, website_url, website_user_id) VALUES (1, 'www.geeksforgeeks.org/map-put-method-in-java-with-examples/', 2)
INSERT INTO website_word (website_word_id, website_word_amount, word_id, website_id) VALUES (1, 13, 1, 1)
INSERT INTO website_word (website_word_id, website_word_amount, word_id, website_id) VALUES (2, 5, 2, 1)
INSERT INTO website_word (website_word_id, website_word_amount, word_id, website_id) VALUES (3, 26, 3, 1)

