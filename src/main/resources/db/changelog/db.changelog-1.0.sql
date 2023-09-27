--liquibase formatted sql

--changeset vnavesnoj:1
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(128) NOT NULL UNIQUE,
    email      VARCHAR(128) NOT NULL UNIQUE,
    password   VARCHAR(128) NOT NULL DEFAULT '{noop}pass',
    firstname  VARCHAR(64),
    lastname   VARCHAR(64),
    surname    VARCHAR(64),
    birth_date DATE,
    role       VARCHAR(32),
    image      VARCHAR(64),
    enabled    BOOLEAN      NOT NULL DEFAULT 'false'
);

--changeset vnavesnoj:2
CREATE TABLE person
(
    id         BIGSERIAL PRIMARY KEY,
    firstname  VARCHAR(64),
    lastname   VARCHAR(64),
    surname    VARCHAR(64),
    birth_date DATE,
    role       VARCHAR(32),
    user_id BIGINT UNIQUE REFERENCES users
);

--changeset vnavesnoj:3
CREATE TABLE sport
(
    id   SERIAL PRIMARY KEY,
    name varchar(32) NOT NULL UNIQUE
);

--changeset vnavesnoj:4
CREATE TABLE person_sport
(
    id        BIGSERIAL PRIMARY KEY,
    person_id BIGINT NOT NULL REFERENCES person,
    sport_id  INT    NOT NULL REFERENCES sport,
    UNIQUE (person_id, sport_id)
);

--changeset vnavesnoj:5
CREATE TABLE achievement
(
    id          BIGSERIAL PRIMARY KEY,
    person_id   BIGINT      NOT NULL REFERENCES person,
    name        VARCHAR(64) not null,
    description TEXT
);

--changeset vnavesnoj:6
CREATE TABLE coach_athlete
(
    id         BIGSERIAL PRIMARY KEY,
    coach_id   BIGINT NOT NULL REFERENCES person,
    athlete_id BIGINT NOT NULL REFERENCES person,
    UNIQUE (coach_id, athlete_id)
);

--changeset vnavesnoj:7
CREATE TABLE tournament
(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(128) NOT NULL,
    sport_id        INT REFERENCES sport,
    tournament_date DATE
);

--changeset vnavesnoj:8
CREATE TABLE tournament_person
(
    id            BIGSERIAL PRIMARY KEY,
    tournament_id BIGINT      NOT NULL REFERENCES tournament,
    person_id     BIGINT      NOT NULL REFERENCES person,
    member_role   VARCHAR(32) NOT NULL,
    UNIQUE (tournament_id, person_id)
);

--changeset vnavesnoj:9
CREATE TABLE blog_body
(
    id   SERIAL PRIMARY KEY,
    body TEXT NOT NULL
);

--changeset vnavesnoj:10
CREATE TABLE blog
(
    id               SERIAL PRIMARY KEY,
    title            VARCHAR(128) NOT NULL UNIQUE,
    announcement     VARCHAR(256),
    publication_time TIMESTAMP    NOT NULL UNIQUE,
    blog_body_id     INT REFERENCES blog_body
);

--changeset vnavesnoj:11
CREATE TABLE verification_token
(
    id         BIGSERIAL PRIMARY KEY,
    token      VARCHAR(64) NOT NULL UNIQUE,
    created_at TIMESTAMP   NOT NULL,
    user_id    BIGINT      NOT NULL REFERENCES users ON DELETE CASCADE
);

--changeset vnavesnoj:12
CREATE TABLE reset_password_token
(
    id         BIGSERIAL PRIMARY KEY,
    token      VARCHAR(64) NOT NULL UNIQUE,
    created_at TIMESTAMP   NOT NULL,
    user_id    BIGINT      NOT NULL REFERENCES users ON DELETE CASCADE
);