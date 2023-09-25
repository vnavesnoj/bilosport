--liquibase formatted sql

--changeset vnavesnoj:1
CREATE TABLE tournament_result
(
    id                   BIGSERIAL PRIMARY KEY,
    tournament_person_id BIGINT NOT NULL UNIQUE REFERENCES tournament_person,
    place                INT,
    out_of               INT
);

--changeset vnavesnoj:2
ALTER TABLE tournament
    ADD COLUMN status VARCHAR(32) NOT NULL DEFAULT 'PREPARATION';

