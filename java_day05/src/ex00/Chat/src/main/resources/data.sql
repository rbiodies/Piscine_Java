INSERT INTO chat.users (login, password)
VALUES ('Max', '123456');
INSERT INTO chat.users (login, password)
VALUES ('Alex', 'qwerty');
INSERT INTO chat.users (login, password)
VALUES ('Sergey', '123qwe');
INSERT INTO chat.users (login, password)
VALUES ('Andrew', '111111');
INSERT INTO chat.users (login, password)
VALUES ('Bob', '000000');

INSERT INTO chat.rooms (name, owner)
VALUES ('Guest', 1);
INSERT INTO chat.rooms (name, owner)
VALUES ('Children', 2);
INSERT INTO chat.rooms (name, owner)
VALUES ('Sleep', 3);
INSERT INTO chat.rooms (name, owner)
VALUES ('Kitchen', 4);
INSERT INTO chat.rooms (name, owner)
VALUES ('WC', 5);

INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (1, 1, 'Hey, Bro!', '2022-01-01 00:00:01');
INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (2, 3, 'How are you?', '2022-01-01 00:00:01');
INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (5, 3, 'You should me 300$!', '2022-01-01 00:00:02');
INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (4, 4, 'lol', '2022-01-01 00:00:04');
INSERT INTO chat.messages (author, room, text, timestamp)
VALUES (5, 5, 'kek', '2022-01-01 00:00:05');