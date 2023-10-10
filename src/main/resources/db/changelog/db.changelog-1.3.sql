--liquibase formatted sql

--changeset vnavesnoj:1
ALTER TABLE tournament
    ADD COLUMN min_age           INT,
    ADD COLUMN max_age           INT,
    ADD COLUMN participant_count INT,
    ADD COLUMN description       VARCHAR(256),
    ADD COLUMN scope             VARCHAR(32) NOT NULL DEFAULT 'ADMIN';
