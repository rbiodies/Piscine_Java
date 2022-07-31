DROP SCHEMA IF EXISTS roomdb CASCADE;

CREATE SCHEMA IF NOT EXISTS roomdb;


CREATE TABLE IF NOT EXISTS roomdb.users (
    id          SERIAL PRIMARY KEY,
    email       TEXT NOT NULL
);