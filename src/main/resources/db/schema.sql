drop table databasechangelog, databasechangeloglock;

create table if not exists authorities
(
    id        serial primary key,
    authority text
);
create table if not exists users
(
    id           serial primary key,
    name         text,
    password     text,
    authority_id int references authorities (id),
    enabled      boolean
);
create table if not exists posts
(
    id          serial primary key,
    name        text,
    description text,
    created     timestamp,
    user_id     int references users (id)
);