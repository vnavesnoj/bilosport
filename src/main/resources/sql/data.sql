INSERT INTO users(username, firstname, lastname, birth_date, role)
VALUES ('ivan@gmail.com', 'Іван', 'Иванов', '1981-10-13', 'COACH'),
       ('petro@gmail.com', 'Петро', 'Петров', '1972-02-01', 'COACH'),
       ('sveta@gmail.com', 'Свєта', 'Свєтікова', '1993-08-19', 'COACH'),
       ('dmitro@gmail.com', 'Дмитро', 'Дмитров', '1979-06-23', 'ADMIN'),
       ('shevchenko@gmail.com', 'Іван', 'Шевченко', '2010-08-25', 'ATHLETE'),
       ('skovoroda@gmail.com', 'Григорій', 'Сковорода', '2006-03-03', 'ATHLETE'),
       ('mask@gmail.com', 'Ілон', 'Маск', '2012-01-15', 'ATHLETE'),
       ('ukrainka@gmail.com', 'Леся', 'Українка', '2009-11-11', 'ATHLETE'),
       ('chikchik@gmail.com', 'Данило', 'Чикатило', '1979-06-23', 'ATHLETE'),
       ('zelia@gmail.com', 'Володимир', 'Зеленський', '1999-09-19', 'ATHLETE'),
       ('timoha@gmail.com', 'Юлія', 'Тимошенко', '2013-04-01', 'ATHLETE'),
       ('volk@gmail.com', 'Александр', 'Волков', '1997-12-07', 'ATHLETE');

SELECT setval('users_id_seq', (SELECT max(id) FROM users));

INSERT INTO coach
VALUES ((SELECT id FROM users WHERE role = 'COACH'));

INSERT INTO athlete
VALUES ((SELECT id FROM users WHERE role = 'ATHLETE'));

INSERT INTO coach_athlete(coach_id, athlete_id)
VALUES ((SELECT id FROM users WHERE username = 'ivan@gmail.com'),
        (SELECT id FROM users WHERE username = 'shevchenko@gmail.com')),
       ((SELECT id FROM users WHERE username = 'ivan@gmail.com'),
        (SELECT id FROM users WHERE username = 'skovoroda@gmail.com')),
       ((SELECT id FROM users WHERE username = 'ivan@gmail.com'),
        (SELECT id FROM users WHERE username = 'mask@gmail.com')),
       ((SELECT id FROM users WHERE username = 'petro@gmail.com'),
        (SELECT id FROM users WHERE username = 'ukrainka@gmail.com')),
       ((SELECT id FROM users WHERE username = 'petro@gmail.com'),
        (SELECT id FROM users WHERE username = 'chikchik@gmail.com')),
       ((SELECT id FROM users WHERE username = 'petro@gmail.com'),
        (SELECT id FROM users WHERE username = 'zelia@gmail.com')),
       ((SELECT id FROM users WHERE username = 'sveta@gmail.com'),
        (SELECT id FROM users WHERE username = 'timoha@gmail.com')),
       ((SELECT id FROM users WHERE username = 'sveta@gmail.com'),
        (SELECT id FROM users WHERE username = 'volk@gmail.com')),
       ((SELECT id FROM users WHERE username = 'sveta@gmail.com'),
        (SELECT id FROM users WHERE username = 'shevchenko@gmail.com'));

SELECT setval('coach_athlete_id_seq', (SELECT max(id) FROM coach_athlete));

INSERT INTO sport(name)
VALUES ('футбол'),
       ('теніс'),
       ('шахи');

SELECT setval('sport_id_seq', (SELECT max(id) FROM sport));

INSERT INTO users_sport(user_id, sport_id)
VALUES ((SELECT id FROM users WHERE username = 'ivan@gmail.com'), (SELECT id FROM sport WHERE name = 'футбол')),
       ((SELECT id FROM users WHERE username = 'petro@gmail.com'), (SELECT id FROM sport WHERE name = 'теніс')),
       ((SELECT id FROM users WHERE username = 'sveta@gmail.com'), (SELECT id FROM sport WHERE name = 'шахи')),
       ((SELECT id FROM users WHERE username = 'shevchenko@gmail.com'), (SELECT id FROM sport WHERE name = 'футбол')),
       ((SELECT id FROM users WHERE username = 'skovoroda@gmail.com'), (SELECT id FROM sport WHERE name = 'футбол')),
       ((SELECT id FROM users WHERE username = 'mask@gmail.com'), (SELECT id FROM sport WHERE name = 'футбол')),
       ((SELECT id FROM users WHERE username = 'ukrainka@gmail.com'), (SELECT id FROM sport WHERE name = 'теніс')),
       ((SELECT id FROM users WHERE username = 'chikchik@gmail.com'), (SELECT id FROM sport WHERE name = 'теніс')),
       ((SELECT id FROM users WHERE username = 'zelia@gmail.com'), (SELECT id FROM sport WHERE name = 'теніс')),
       ((SELECT id FROM users WHERE username = 'timoha@gmail.com'), (SELECT id FROM sport WHERE name = 'шахи')),
       ((SELECT id FROM users WHERE username = 'volk@gmail.com'), (SELECT id FROM sport WHERE name = 'шахи')),
       ((SELECT id FROM users WHERE username = 'shevchenko@gmail.com'), (SELECT id FROM sport WHERE name = 'шахи'));

SELECT setval('users_sport_id_seq', (SELECT max(id) FROM users_sport));

INSERT INTO tournament (name, sport_id, tournament_date)
VALUES ('Великий футбол', (SELECT id FROM sport WHERE sport.name = 'футбол'), '2023-08-01'),
       ('Шахи над шахами', (SELECT id FROM sport WHERE sport.name = 'шахи'), '2023-11-15');

SELECT setval('tournament_id_seq', (SELECT max(id) FROM tournament));

INSERT INTO tournament_users (tournament_id, user_id, member_role)
VALUES ((SELECT id FROM tournament WHERE name = 'Великий футбол'),
        (SELECT id FROM users WHERE username = 'ivan@gmail.com'), 'REFEREE'),
       ((SELECT id FROM tournament WHERE name = 'Великий футбол'),
        (SELECT id FROM users WHERE username = 'shevchenko@gmail.com'), 'PARTICIPANT'),
       ((SELECT id FROM tournament WHERE name = 'Великий футбол'),
        (SELECT id FROM users WHERE username = 'skovoroda@gmail.com'), 'PARTICIPANT'),
       ((SELECT id FROM tournament WHERE name = 'Великий футбол'),
        (SELECT id FROM users WHERE username = 'mask@gmail.com'), 'PARTICIPANT'),
       ((SELECT id FROM tournament WHERE name = 'Великий футбол'),
        (SELECT id FROM users WHERE username = 'ukrainka@gmail.com'), 'PARTICIPANT'),
       ((SELECT id FROM tournament WHERE name = 'Великий футбол'),
        (SELECT id FROM users WHERE username = 'chikchik@gmail.com'), 'PARTICIPANT'),
       ((SELECT id FROM tournament WHERE name = 'Великий футбол'),
        (SELECT id FROM users WHERE username = 'zelia@gmail.com'), 'PARTICIPANT'),
       ((SELECT id FROM tournament WHERE name = 'Шахи над шахами'),
        (SELECT id FROM users WHERE username = 'sveta@gmail.com'), 'REFEREE'),
       ((SELECT id FROM tournament WHERE name = 'Шахи над шахами'),
        (SELECT id FROM users WHERE username = 'volk@gmail.com'), 'PARTICIPANT'),
       ((SELECT id FROM tournament WHERE name = 'Шахи над шахами'),
        (SELECT id FROM users WHERE username = 'shevchenko@gmail.com'), 'PARTICIPANT');

SELECT setval('tournament_users_id_seq', (SELECT max(id) FROM tournament_users));