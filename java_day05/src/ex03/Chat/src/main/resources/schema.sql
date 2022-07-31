DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA IF NOT EXISTS chat;


CREATE TABLE IF NOT EXISTS chat.users (
    id          SERIAL PRIMARY KEY,
    login       TEXT NOT NULL,
    password    TEXT
);

CREATE TABLE IF NOT EXISTS chat.rooms (
    id          SERIAL PRIMARY KEY,
    chat_owner  INTEGER NOT NULL REFERENCES chat.users(id) ,
    chat_name   TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.messages (
    id          SERIAL PRIMARY KEY,
    room_id     INTEGER NOT NULL REFERENCES chat.rooms(id),
    sender      INTEGER NOT NULL REFERENCES chat.users(id),
    text        TEXT NOT NULL,
    time        TIMESTAMP
);