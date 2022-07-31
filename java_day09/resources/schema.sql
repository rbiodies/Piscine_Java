DROP SCHEMA IF EXISTS service CASCADE;

CREATE SCHEMA IF NOT EXISTS service;


CREATE TABLE IF NOT EXISTS service.users (
    id          SERIAL PRIMARY KEY,
    username       TEXT NOT NULL,
    password       TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS service.messages (
    id          SERIAL PRIMARY KEY,
    sender      INTEGER NOT NULL,
    text        TEXT NOT NULL,
    time        TIMESTAMP
);