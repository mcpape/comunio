ALTER TABLE users
    ADD CONSTRAINT uc_users_name UNIQUE (name);