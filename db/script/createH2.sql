CREATE TABLE IF NOT EXISTS authorities
(
    id        int AUTO_INCREMENT,
    authority text
);
CREATE TABLE IF NOT EXISTS users
(
    id           int AUTO_INCREMENT,
    name         text,
    password     text,
    authority_id int references authorities (id),
    enabled      boolean
);
CREATE TABLE IF NOT EXISTS posts
(
    id          int AUTO_INCREMENT,
    name        text,
    description text,
    created     timestamp,
    user_id     int references users (id)
);
INSERT INTO AUTHORITIES (authority)
VALUES ('ADMIN');
INSERT INTO Users (name, authority_id)
VALUES ('ADMIN', 1);
insert into AUTHORITIES (authority)
values ('ROLE_USER ')