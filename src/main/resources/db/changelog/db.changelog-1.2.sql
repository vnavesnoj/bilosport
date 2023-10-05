--liquibase formatted sql

--changeset vnavesnoj:1
ALTER TABLE tournament_person
    ADD COLUMN status VARCHAR(32) NOT NULL DEFAULT 'REGISTERED';
