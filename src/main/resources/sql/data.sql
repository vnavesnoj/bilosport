INSERT INTO users(email, username, firstname, lastname, birth_date, role, enabled)
VALUES ('ivan@gmail.com', 'Ivan', 'Іван', 'Іванов', '1981-10-13', 'COACH', 'true'),
       ('petro@gmail.com', 'Petr', 'Петро', 'Петров', '1972-02-01', 'COACH', 'true'),
       ('sveta@gmail.com', 'Sveta', 'Свєта', 'Свєтікова', '1993-08-19', 'COACH', 'true'),
       ('dmitro@gmail.com', 'Dmitro', 'Дмитро', 'Дмитров', '1979-06-23', 'ADMIN', 'true'),
       ('shevchenko@gmail.com', 'shevchenko', 'Іван', 'Шевченко', '2010-08-25', 'ATHLETE', 'true'),
       ('skovoroda@gmail.com', 'skovoroda', 'Григорій', 'Сковорода', '2006-03-03', 'ATHLETE', 'true'),
       ('mask@gmail.com', 'Mask', 'Ілон', 'Маск', '2012-01-15', 'ATHLETE', 'true'),
       ('ukrainka@gmail.com', 'ukrainka', 'Леся', 'Українка', '2009-11-11', 'ATHLETE', 'true'),
       ('chikchik@gmail.com', 'ChikChik', 'Данило', 'Чикатило', '1979-06-23', 'ATHLETE', 'true'),
       ('zelia@gmail.com', 'Zelia', 'Володимир', 'Зеленський', '1999-09-19', 'ATHLETE', 'true'),
       ('timoha@gmail.com', 'Timoha', 'Юлія', 'Тимошенко', '2013-04-01', 'ATHLETE', 'true'),
       ('volk@gmail.com', 'Volk', 'Олександр', 'Волков', '1997-12-07', 'ATHLETE', 'true');

SELECT setval('users_id_seq', (SELECT max(id) FROM users));

INSERT INTO person(firstname, lastname, birth_date, role, user_id)
VALUES ('Іван', 'Іванов', '1981-10-13', 'COACH', (SELECT id FROM users WHERE email = 'ivan@gmail.com')),
       ('Петро', 'Петров', '1972-02-01', 'COACH', (SELECT id FROM users WHERE email = 'petro@gmail.com')),
       ('Свєта', 'Свєтікова', '1993-08-19', 'COACH', (SELECT id FROM users WHERE email = 'sveta@gmail.com')),
       ('Дмитро', 'Дмитров', '1979-06-23', 'ADMIN', (SELECT id FROM users WHERE email = 'dmitro@gmail.com')),
       ('Іван', 'Шевченко', '2010-08-25', 'ATHLETE', (SELECT id FROM users WHERE email = 'shevchenko@gmail.com'));

INSERT INTO coach_athlete(coach_id, athlete_id)
VALUES ((SELECT id FROM users WHERE email = 'ivan@gmail.com'),
        (SELECT id FROM users WHERE email = 'shevchenko@gmail.com')),
       ((SELECT id FROM users WHERE email = 'ivan@gmail.com'),
        (SELECT id FROM users WHERE email = 'skovoroda@gmail.com')),
       ((SELECT id FROM users WHERE email = 'ivan@gmail.com'),
        (SELECT id FROM users WHERE email = 'mask@gmail.com')),
       ((SELECT id FROM users WHERE email = 'petro@gmail.com'),
        (SELECT id FROM users WHERE email = 'ukrainka@gmail.com')),
       ((SELECT id FROM users WHERE email = 'petro@gmail.com'),
        (SELECT id FROM users WHERE email = 'chikchik@gmail.com')),
       ((SELECT id FROM users WHERE email = 'petro@gmail.com'),
        (SELECT id FROM users WHERE email = 'zelia@gmail.com')),
       ((SELECT id FROM users WHERE email = 'sveta@gmail.com'),
        (SELECT id FROM users WHERE email = 'timoha@gmail.com')),
       ((SELECT id FROM users WHERE email = 'sveta@gmail.com'),
        (SELECT id FROM users WHERE email = 'volk@gmail.com')),
       ((SELECT id FROM users WHERE email = 'sveta@gmail.com'),
        (SELECT id FROM users WHERE email = 'shevchenko@gmail.com'));

SELECT setval('coach_athlete_id_seq', (SELECT max(id) FROM coach_athlete));

INSERT INTO sport(name)
VALUES ('футбол'),
       ('теніс'),
       ('шахи');

SELECT setval('sport_id_seq', (SELECT max(id) FROM sport));

INSERT INTO person_sport(person_id, sport_id)
VALUES ((SELECT id FROM users WHERE email = 'ivan@gmail.com'), (SELECT id FROM sport WHERE name = 'футбол')),
       ((SELECT id FROM users WHERE email = 'petro@gmail.com'), (SELECT id FROM sport WHERE name = 'теніс')),
       ((SELECT id FROM users WHERE email = 'sveta@gmail.com'), (SELECT id FROM sport WHERE name = 'шахи')),
       ((SELECT id FROM users WHERE email = 'shevchenko@gmail.com'), (SELECT id FROM sport WHERE name = 'футбол'));
--        ((SELECT id FROM users WHERE email = 'skovoroda@gmail.com'), (SELECT id FROM sport WHERE name = 'футбол')),
--        ((SELECT id FROM users WHERE email = 'mask@gmail.com'), (SELECT id FROM sport WHERE name = 'футбол')),
--        ((SELECT id FROM users WHERE email = 'ukrainka@gmail.com'), (SELECT id FROM sport WHERE name = 'теніс')),
--        ((SELECT id FROM users WHERE email = 'chikchik@gmail.com'), (SELECT id FROM sport WHERE name = 'теніс')),
--        ((SELECT id FROM users WHERE email = 'zelia@gmail.com'), (SELECT id FROM sport WHERE name = 'теніс')),
--        ((SELECT id FROM users WHERE email = 'timoha@gmail.com'), (SELECT id FROM sport WHERE name = 'шахи')),
--        ((SELECT id FROM users WHERE email = 'volk@gmail.com'), (SELECT id FROM sport WHERE name = 'шахи')),
--        ((SELECT id FROM users WHERE email = 'shevchenko@gmail.com'), (SELECT id FROM sport WHERE name = 'шахи'));
--
-- SELECT setval('users_sport_id_seq', (SELECT max(id) FROM users_sport));

INSERT INTO tournament (name, sport_id, tournament_date)
VALUES ('Великий футбол', (SELECT id FROM sport WHERE sport.name = 'футбол'), '2023-08-01'),
       ('Шахи над шахами', (SELECT id FROM sport WHERE sport.name = 'шахи'), '2023-11-15');

SELECT setval('tournament_id_seq', (SELECT max(id) FROM tournament));

-- INSERT INTO tournament_users (tournament_id, user_id, member_role)
-- VALUES ((SELECT id FROM tournament WHERE name = 'Великий футбол'),
--         (SELECT id FROM users WHERE email = 'ivan@gmail.com'), 'REFEREE'),
--        ((SELECT id FROM tournament WHERE name = 'Великий футбол'),
--         (SELECT id FROM users WHERE email = 'shevchenko@gmail.com'), 'PARTICIPANT'),
--        ((SELECT id FROM tournament WHERE name = 'Великий футбол'),
--         (SELECT id FROM users WHERE email = 'skovoroda@gmail.com'), 'PARTICIPANT'),
--        ((SELECT id FROM tournament WHERE name = 'Великий футбол'),
--         (SELECT id FROM users WHERE email = 'mask@gmail.com'), 'PARTICIPANT'),
--        ((SELECT id FROM tournament WHERE name = 'Великий футбол'),
--         (SELECT id FROM users WHERE email = 'ukrainka@gmail.com'), 'PARTICIPANT'),
--        ((SELECT id FROM tournament WHERE name = 'Великий футбол'),
--         (SELECT id FROM users WHERE email = 'chikchik@gmail.com'), 'PARTICIPANT'),
--        ((SELECT id FROM tournament WHERE name = 'Великий футбол'),
--         (SELECT id FROM users WHERE email = 'zelia@gmail.com'), 'PARTICIPANT'),
--        ((SELECT id FROM tournament WHERE name = 'Шахи над шахами'),
--         (SELECT id FROM users WHERE email = 'sveta@gmail.com'), 'REFEREE'),
--        ((SELECT id FROM tournament WHERE name = 'Шахи над шахами'),
--         (SELECT id FROM users WHERE email = 'volk@gmail.com'), 'PARTICIPANT'),
--        ((SELECT id FROM tournament WHERE name = 'Шахи над шахами'),
--         (SELECT id FROM users WHERE email = 'shevchenko@gmail.com'), 'PARTICIPANT');

-- SELECT setval('tournament_users_id_seq', (SELECT max(id) FROM tournament_users));

INSERT INTO blog_body(body)
VALUES ('Перша статья'),
       ('Друга статья'),
       ('Третя статья'),
       ('Четверта статья');

INSERT INTO blog(title, announcement, publication_time, blog_body_id)
VALUES ('Перший заголовок', 'Перший анонс', '2023-09-01', (SELECT id FROM blog_body WHERE body = 'Перша статья')),
       ('Другий заголовок', 'Другий анонс', '2023-09-02', (SELECT id FROM blog_body WHERE body = 'Друга статья')),
       ('Третій заголовок', 'Третій анонс', '2023-09-03', (SELECT id FROM blog_body WHERE body = 'Третя статья')),
       ('Четвертий заголовок', 'Четвертий анонс', '2023-09-04',
        (SELECT id FROM blog_body WHERE body = 'Четверта статья'));