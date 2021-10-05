CREATE TABLE IF NOT EXISTS authorities
(
    id        serial primary key,
    authority text
);
CREATE TABLE IF NOT EXISTS users
(
    id           serial primary key,
    name         text,
    password     text,
    authority_id int references authorities (id),
    enabled      boolean
);
CREATE TABLE IF NOT EXISTS posts
(
    id          serial primary key,
    name        text,
    description text,
    created     timestamp,
    user_id     int references users (id)
);