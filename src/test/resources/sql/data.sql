INSERT INTO users(id, email, username, firstname, lastname, birth_date, role, enabled)
VALUES (1, 'ivan@gmail.com', 'Ivan', 'Іван', 'Іванов', '1981-10-13', 'COACH', 'true'),
       (2, 'petro@gmail.com', 'Petr', 'Петро', 'Петров', '1972-02-01', 'COACH', 'true'),
       (3, 'sveta@gmail.com', 'Sveta', 'Свєта', 'Свєтікова', '1993-08-19', 'COACH', 'true'),
       (4, 'dmitro@gmail.com', 'Dmitro', 'Дмитро', 'Дмитров', '1979-06-23', 'ADMIN', 'true'),
       (5, 'shevchenko@gmail.com', 'shevchenko', 'Іван', 'Шевченко', '2010-08-25', 'ATHLETE', 'true'),
       (6, 'skovoroda@gmail.com', 'skovoroda', 'Григорій', 'Сковорода', '2006-03-03', 'ATHLETE', 'true'),
       (7, 'mask@gmail.com', 'Mask', 'Ілон', 'Маск', '2012-01-15', 'ATHLETE', 'true'),
       (8, 'ukrainka@gmail.com', 'ukrainka', 'Леся', 'Українка', '2009-11-11', 'ATHLETE', 'true'),
       (9, 'chikchik@gmail.com', 'ChikChik', 'Данило', 'Чикатило', '1979-06-23', 'ATHLETE', 'true'),
       (10, 'zelia@gmail.com', 'Zelia', 'Володимир', 'Зеленський', '1999-09-19', 'ATHLETE', 'true'),
       (11, 'timoha@gmail.com', 'Timoha', null, null, null, 'ATHLETE', 'true'),
       (12, 'volk@gmail.com', 'Volk', null, null, null, 'ATHLETE', 'true');

SELECT setval('users_id_seq', (SELECT max(id) FROM users));

INSERT INTO person(id, firstname, lastname, birth_date, role, user_id)
VALUES (1, 'Іван', 'Іванов', '1981-10-13', 'COACH', (SELECT id FROM users WHERE email = 'ivan@gmail.com')),
       (2, 'Петро', 'Петров', '1972-02-01', 'COACH', (SELECT id FROM users WHERE email = 'petro@gmail.com')),
       (3, 'Свєта', 'Свєтікова', '1993-08-19', 'COACH', (SELECT id FROM users WHERE email = 'sveta@gmail.com')),
       (4, 'Дмитро', 'Дмитров', '1979-06-23', 'ADMIN', (SELECT id FROM users WHERE email = 'dmitro@gmail.com')),
       (5, 'Іван', 'Шевченко', '2010-08-25', 'ATHLETE', (SELECT id FROM users WHERE email = 'shevchenko@gmail.com')),
       (6, 'Григорій', 'Сковорода', '2006-03-03', 'ATHLETE',
        (SELECT id FROM users WHERE email = 'skovoroda@gmail.com')),
       (7, 'Ілон', 'Маск', '2012-01-15', 'ATHLETE', (SELECT id FROM users WHERE email = 'mask@gmail.com')),
       (8, 'Леся', 'Українка', '2009-11-11', 'ATHLETE', (SELECT id FROM users WHERE email = 'ukrainka@gmail.com')),
       (9, 'Данило', 'Чикатило', '1979-06-23', 'ATHLETE', (SELECT id FROM users WHERE email = 'chikchik@gmail.com')),
       (10, 'Володимир', 'Зеленський', '1999-09-19', 'ATHLETE', (SELECT id FROM users WHERE email = 'zelia@gmail.com')),
       (11, 'Юлія', 'Тимошенко', '2013-04-01', 'ATHLETE', (SELECT id FROM users WHERE email = 'timoha@gmail.com')),
       (12, 'Олександр', 'Волков', '1997-12-07', 'ATHLETE', (SELECT id FROM users WHERE email = 'volk@gmail.com')),
       (13, 'Гіга', 'Чад', '1999-09-19', 'ATHLETE', null),
       (14, 'Ганна', 'Лорак', '1991-03-07', 'ATHLETE', null);

SELECT setval('person_id_seq', (SELECT max(id) FROM person));

INSERT INTO coach_athlete(id, coach_id, athlete_id)
VALUES (1, (SELECT id FROM person WHERE lastname = 'Іванов'),
        (SELECT id FROM person WHERE lastname = 'Шевченко')),
       (2, (SELECT id FROM person WHERE lastname = 'Іванов'),
        (SELECT id FROM person WHERE lastname = 'Сковорода')),
       (3, (SELECT id FROM person WHERE lastname = 'Іванов'),
        (SELECT id FROM person WHERE lastname = 'Маск')),
       (4, (SELECT id FROM person WHERE lastname = 'Петров'),
        (SELECT id FROM person WHERE lastname = 'Українка')),
       (5, (SELECT id FROM person WHERE lastname = 'Петров'),
        (SELECT id FROM person WHERE lastname = 'Чикатило')),
       (6, (SELECT id FROM person WHERE lastname = 'Свєтікова'),
        (SELECT id FROM person WHERE lastname = 'Чад')),
       (7, (SELECT id FROM person WHERE lastname = 'Свєтікова'),
        (SELECT id FROM person WHERE lastname = 'Лорак')),
       (8, (SELECT id FROM person WHERE lastname = 'Свєтікова'),
        (SELECT id FROM person WHERE lastname = 'Волков'));

SELECT setval('coach_athlete_id_seq', (SELECT max(id) FROM coach_athlete));

INSERT INTO sport(id, name)
VALUES (1, 'футбол'),
       (2, 'теніс'),
       (3, 'шахи');

SELECT setval('sport_id_seq', (SELECT max(id) FROM sport));

INSERT INTO person_sport(id, person_id, sport_id)
VALUES (1, (SELECT id FROM person WHERE lastname = 'Іванов'), (SELECT id FROM sport WHERE name = 'футбол')),
       (2, (SELECT id FROM person WHERE lastname = 'Петров'), (SELECT id FROM sport WHERE name = 'теніс')),
       (3, (SELECT id FROM person WHERE lastname = 'Свєтікова'), (SELECT id FROM sport WHERE name = 'шахи')),
       (4, (SELECT id FROM person WHERE lastname = 'Шевченко'), (SELECT id FROM sport WHERE name = 'футбол')),
       (5, (SELECT id FROM person WHERE lastname = 'Сковорода'), (SELECT id FROM sport WHERE name = 'футбол')),
       (6, (SELECT id FROM person WHERE lastname = 'Маск'), (SELECT id FROM sport WHERE name = 'футбол')),
       (7, (SELECT id FROM person WHERE lastname = 'Українка'), (SELECT id FROM sport WHERE name = 'теніс')),
       (8, (SELECT id FROM person WHERE lastname = 'Чикатило'), (SELECT id FROM sport WHERE name = 'теніс')),
       (9, (SELECT id FROM person WHERE lastname = 'Чад'), (SELECT id FROM sport WHERE name = 'шахи')),
       (10, (SELECT id FROM person WHERE lastname = 'Лорак'), (SELECT id FROM sport WHERE name = 'шахи')),
       (11, (SELECT id FROM person WHERE lastname = 'Волков'), (SELECT id FROM sport WHERE name = 'шахи'));

SELECT setval('person_sport_id_seq', (SELECT max(id) FROM person_sport));

INSERT INTO tournament (id, name, sport_id, tournament_date)
VALUES (1, 'Великий футбол', (SELECT id FROM sport WHERE sport.name = 'футбол'), '2023-08-01'),
       (2, 'Шахи над шахами', (SELECT id FROM sport WHERE sport.name = 'шахи'), '2023-11-15');

SELECT setval('tournament_id_seq', (SELECT max(id) FROM tournament));

INSERT INTO tournament_person (id, tournament_id, person_id, member_role)
VALUES (1, (SELECT id FROM tournament WHERE name = 'Великий футбол'),
        (SELECT id FROM person WHERE lastname = 'Іванов'), 'JUDGE'),
       (2, (SELECT id FROM tournament WHERE name = 'Великий футбол'),
        (SELECT id FROM person WHERE lastname = 'Шевченко'), 'PARTICIPANT'),
       (3, (SELECT id FROM tournament WHERE name = 'Великий футбол'),
        (SELECT id FROM person WHERE lastname = 'Сковорода'), 'PARTICIPANT'),
       (4, (SELECT id FROM tournament WHERE name = 'Великий футбол'),
        (SELECT id FROM person WHERE lastname = 'Маск'), 'PARTICIPANT'),
       (5, (SELECT id FROM tournament WHERE name = 'Великий футбол'),
        (SELECT id FROM person WHERE lastname = 'Українка'), 'PARTICIPANT'),
       (6, (SELECT id FROM tournament WHERE name = 'Великий футбол'),
        (SELECT id FROM person WHERE lastname = 'Чикатило'), 'PARTICIPANT'),
       (7, (SELECT id FROM tournament WHERE name = 'Великий футбол'),
        (SELECT id FROM person WHERE lastname = 'Зеленський'), 'PARTICIPANT'),
       (8, (SELECT id FROM tournament WHERE name = 'Шахи над шахами'),
        (SELECT id FROM person WHERE lastname = 'Свєтікова'), 'JUDGE'),
       (9, (SELECT id FROM tournament WHERE name = 'Шахи над шахами'),
        (SELECT id FROM person WHERE lastname = 'Чад'), 'PARTICIPANT'),
       (10, (SELECT id FROM tournament WHERE name = 'Шахи над шахами'),
        (SELECT id FROM person WHERE lastname = 'Лорак'), 'PARTICIPANT');

SELECT setval(tournament_person_id_seq, (SELECT max(id) FROM tournament_person));

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