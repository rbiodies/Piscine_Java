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

INSERT INTO chat.rooms (chat_owner, chat_name)
VALUES (1, 'Guest');
INSERT INTO chat.rooms (chat_owner, chat_name)
VALUES (2, 'Children');
INSERT INTO chat.rooms (chat_owner, chat_name)
VALUES (3, 'Sleep');
INSERT INTO chat.rooms (chat_owner, chat_name)
VALUES (4, 'Kitchen');
INSERT INTO chat.rooms (chat_owner, chat_name)
VALUES (5, 'WC');

INSERT INTO chat.messages (room_id, sender, text, time)
VALUES (1, 1, 'Hey, Bro!', '2022-01-01 00:00:01');
INSERT INTO chat.messages (room_id, sender, text, time)
VALUES (3, 2, 'How are you?', '2022-01-01 00:00:01');
INSERT INTO chat.messages (room_id, sender, text, time)
VALUES (3, 5, 'You should me 300$!', '2022-01-01 00:00:02');
INSERT INTO chat.messages (room_id, sender, text, time)
VALUES (4, 4, 'lol', '2022-01-01 00:00:04');
INSERT INTO chat.messages (room_id, sender, text, time)
VALUES (5, 5, 'message', '2001-01-01 15:59:00');