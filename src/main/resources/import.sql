INSERT INTO user (user_id, user_username, user_password, user_email, user_enabled) VALUES (2, 'johnny', 'password', 'rniziolek@hotmail.com', 1);

INSERT INTO role (role_id, role_name) VALUES (1, 'ROLE_USER');
INSERT INTO role (role_id, role_name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_role (user_id, role_id) VALUES (2, 2);


INSERT INTO word (word_id, word_text) VALUES (1, 'test1')
INSERT INTO word (word_id, word_text) VALUES (2, 'test2')
INSERT INTO word (word_id, word_text) VALUES (3, 'test3')
INSERT INTO user_word (user_word_id, user_word_word_id, user_word_amount, user_word_user_id, user_word_known) VALUES (1, 1, 13, 2, 0)
INSERT INTO website (website_id, website_url, website_user_id) VALUES (2, 'www.geeksforgeeks.org/map-put-method-in-java-with-examples/', 2)
INSERT INTO website_word (website_word_id, website_word_amount, website_word_word_id, website_word_website_id) VALUES (1, 13, 1, 2)
INSERT INTO website_word (website_word_id, website_word_amount, website_word_word_id, website_word_website_id) VALUES (2, 5, 2, 2)
INSERT INTO website_word (website_word_id, website_word_amount, website_word_word_id, website_word_website_id) VALUES (3, 26, 3, 2)

-- INSERT INTO acl_sid (id, principal, sid) VALUES (1, 1, 'manager')
-- INSERT INTO acl_sid (id, principal, sid) VALUES (2, 1, 'hr')
-- INSERT INTO acl_sid (id, principal, sid) VALUES (3, 1, 'ROLE_USER')
--
-- INSERT INTO acl_class (id, class) VALUES (1, 'com.voltaireu.vocabularist.website');
--
-- INSERT INTO website (website_id, website_url, website_user_id) VALUES (2,'https://www.baeldung.com/spring-security-acl', 2)
-- INSERT INTO website (website_id, website_url, website_user_id) VALUES (3,'https://docs.spring.io/spring-security/site/docs/5.2.1.RELEASE/reference/htmlsingle', 2)
-- INSERT INTO website (website_id, website_url, website_user_id) VALUES (4,'https://stackoverflow.com/questions/2029358/should-i-write-table-and-column-names-always-lower-case', 2)
--
-- INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES (1, 1, 1, NULL, 3, 0)
-- INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES (2, 1, 2, NULL, 3, 0)
-- INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES (3, 1, 3, NULL, 3, 0)
--
-- INSERT INTO acl_entry(id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (1, 1, 1, 1, 1, 1, 1, 1)
-- INSERT INTO acl_entry(id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (2, 1, 2, 1, 2, 1, 1, 1)
-- INSERT INTO acl_entry(id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (3, 1, 3, 3, 1, 1, 1, 1)
-- INSERT INTO acl_entry(id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (4, 2, 1, 2, 1, 1, 1, 1)
-- INSERT INTO acl_entry(id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (5, 2, 2, 3, 1, 1, 1, 1)
-- INSERT INTO acl_entry(id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (6, 3, 1, 3, 1, 1, 1, 1)
-- INSERT INTO acl_entry(id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (7, 3, 2, 3, 2, 1, 1, 1)
