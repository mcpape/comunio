ALTER TABLE extra_bonus
    ADD COLUMN reason VARCHAR(255);

ALTER TABLE users_bonus
    ADD COLUMN reason VARCHAR(255);
