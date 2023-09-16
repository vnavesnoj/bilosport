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

CREATE TABLE sport
(
    id   SERIAL PRIMARY KEY,
    name varchar(32) NOT NULL UNIQUE
);

CREATE TABLE users_sport
(
    id       BIGSERIAL PRIMARY KEY,
    user_id  BIGINT NOT NULL REFERENCES users,
    sport_id INT    NOT NULL REFERENCES sport,
    UNIQUE (user_id, sport_id)
);

CREATE TABLE achievement
(
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT      NOT NULL REFERENCES users,
    name        VARCHAR(64) not null,
    description TEXT
);

CREATE TABLE coach_athlete
(
    id         BIGSERIAL PRIMARY KEY,
    coach_id   BIGINT NOT NULL REFERENCES users,
    athlete_id BIGINT NOT NULL REFERENCES users,
    UNIQUE (coach_id, athlete_id)
);

CREATE TABLE tournament
(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(128) NOT NULL,
    sport_id        INT REFERENCES sport,
    tournament_date DATE
);

CREATE TABLE tournament_users
(
    id            BIGSERIAL PRIMARY KEY,
    tournament_id BIGINT      NOT NULL REFERENCES tournament,
    user_id       BIGINT      NOT NULL REFERENCES users,
    member_role   VARCHAR(32) NOT NULL,
    UNIQUE (tournament_id, user_id)
);

CREATE TABLE blog_body
(
    id   SERIAL PRIMARY KEY,
    body TEXT NOT NULL
);

CREATE TABLE blog
(
    id               SERIAL PRIMARY KEY,
    title            VARCHAR(128) NOT NULL UNIQUE,
    announcement     VARCHAR(256),
    publication_time TIMESTAMP    NOT NULL UNIQUE,
    blog_body_id     INT REFERENCES blog_body
);

CREATE TABLE verification_token
(
    id         BIGSERIAL PRIMARY KEY,
    token      VARCHAR(64) NOT NULL UNIQUE,
    created_at TIMESTAMP   NOT NULL,
    user_id BIGINT NOT NULL REFERENCES users ON DELETE CASCADE
);