DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES (to_timestamp('13.04.17 9 00', 'DD.MM.YY HH24:MI'), 'Админ завтрак', 300, 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES (to_timestamp('13.04.17 12 00', 'DD.MM.YY HH24:MI'), 'Админ обед', 600, 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES (to_timestamp('13.04.17 9 00', 'DD.MM.YY HH24:MI'), 'Юзер завтрак', 300, 100000);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES (to_timestamp('13.04.17 11 00', 'DD.MM.YY HH24:MI'), 'Юзер полдник', 250, 100000);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES (to_timestamp('13.04.17 13 00', 'DD.MM.YY HH24:MI'), 'Юзер обед', 550, 100000);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES (to_timestamp('13.04.17 19 00', 'DD.MM.YY HH24:MI'), 'Юзер ужин', 400, 100000);

