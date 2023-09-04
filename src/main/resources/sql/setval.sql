SELECT setval('users_id_seq', (SELECT max(id) FROM users));

SELECT setval('coach_athlete_id_seq', (SELECT max(id) FROM coach_athlete));

SELECT setval('sport_id_seq', (SELECT max(id) FROM sport));

SELECT setval('users_sport_id_seq', (SELECT max(id) FROM users_sport));

SELECT setval('tournament_id_seq', (SELECT max(id) FROM tournament));

SELECT setval('tournament_users_id_seq', (SELECT max(id) FROM tournament_users));

SELECT setval('blog_id_seq', (SELECT max(id) FROM blog));

SELECT setval('blog_body_id_seq', (SELECT max(id) FROM blog_body));