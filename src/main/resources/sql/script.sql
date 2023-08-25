CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(128) NOT NULL UNIQUE,
    password   VARCHAR(128) DEFAULT '{noop}pass',
    firstname  VARCHAR(64)  NOT NULL,
    lastname   VARCHAR(64)  NOT NULL,
    surname    VARCHAR(64),
    birth_date DATE,
    role       VARCHAR(32),
    image      VARCHAR(64)
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

CREATE TABLE coach
(
    user_id BIGINT PRIMARY KEY REFERENCES users
);

CREATE TABLE athlete
(
    user_id BIGINT PRIMARY KEY REFERENCES users
);

CREATE TABLE coach_athlete
(
    id         BIGSERIAL PRIMARY KEY,
    coach_id   BIGINT NOT NULL REFERENCES coach,
    athlete_id BIGINT NOT NULL REFERENCES athlete,
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