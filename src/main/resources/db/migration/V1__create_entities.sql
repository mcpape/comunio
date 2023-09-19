CREATE TABLE extra_bonus
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NOT NULL,
    amount         BIGINT                NULL,
    bonus_time     date                  NULL,
    user_entity_id BIGINT                NULL,
    CONSTRAINT pk_extra_bonus PRIMARY KEY (id)
);

CREATE TABLE news_container
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NOT NULL,
    news       TEXT                  NULL,
    CONSTRAINT pk_news_container PRIMARY KEY (id)
);

CREATE TABLE players
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NOT NULL,
    name       VARCHAR(255)          NULL,
    CONSTRAINT pk_players PRIMARY KEY (id)
);

CREATE TABLE table_container
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NOT NULL,
    table_data TEXT                  NULL,
    CONSTRAINT pk_table_container PRIMARY KEY (id)
);

CREATE TABLE transfers
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    created_at          datetime              NOT NULL,
    updated_at          datetime              NOT NULL,
    transfer_date       date                  NULL,
    transfer_time       VARCHAR(255)          NULL,
    price               BIGINT                NULL,
    player_entity_id    BIGINT                NULL,
    from_user_entity_id BIGINT                NULL,
    to_user_entity_id   BIGINT                NULL,
    CONSTRAINT pk_transfers PRIMARY KEY (id)
);

CREATE TABLE users
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    created_at   datetime              NOT NULL,
    updated_at   datetime              NOT NULL,
    name         VARCHAR(255)          NULL,
    points       BIGINT                NULL,
    balance      BIGINT                NULL,
    market_value BIGINT                NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_alias
(
    owner_id BIGINT       NOT NULL,
    alias    VARCHAR(255) NULL
);

CREATE TABLE users_bonus
(
    owner_id BIGINT NOT NULL,
    bonus    BIGINT NULL
);

ALTER TABLE extra_bonus
    ADD CONSTRAINT FK_EXTRA_BONUS_ON_USER_ENTITY FOREIGN KEY (user_entity_id) REFERENCES users (id);

ALTER TABLE transfers
    ADD CONSTRAINT FK_TRANSFERS_ON_FROM_USER_ENTITY FOREIGN KEY (from_user_entity_id) REFERENCES users (id);

ALTER TABLE transfers
    ADD CONSTRAINT FK_TRANSFERS_ON_PLAYER_ENTITY FOREIGN KEY (player_entity_id) REFERENCES players (id);

ALTER TABLE transfers
    ADD CONSTRAINT FK_TRANSFERS_ON_TO_USER_ENTITY FOREIGN KEY (to_user_entity_id) REFERENCES users (id);

ALTER TABLE users_alias
    ADD CONSTRAINT fk_users_alias_on_user_entity FOREIGN KEY (owner_id) REFERENCES users (id);

ALTER TABLE users_bonus
    ADD CONSTRAINT fk_users_bonus_on_user_entity FOREIGN KEY (owner_id) REFERENCES users (id);