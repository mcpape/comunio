CREATE TABLE custom_bonus
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime              NOT NULL,
    updated_at     datetime              NOT NULL,
    amount         BIGINT                NULL,
    reason         VARCHAR(255)          NULL,
    bonus_time     date                  NULL,
    user_entity_id BIGINT                NULL,
    CONSTRAINT pk_extra_bonus PRIMARY KEY (id)
);
