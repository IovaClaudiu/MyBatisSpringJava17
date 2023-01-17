CREATE TABLE IF NOT EXISTS ARTICLES
(
    id     INTEGER PRIMARY KEY,
    title  VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL
);

INSERT INTO ARTICLES
VALUES (1, 'MyBatis Example', 'Iova');

CREATE TABLE IF NOT EXISTS "user"
(
    id               SERIAL PRIMARY KEY,
    dateCreated      timestamp,
    lastModifiedDate timestamp,
    username         VARCHAR(100) UNIQUE,
    firstName        VARCHAR(100),
    lastName         VARCHAR(100),
    description      VARCHAR(100),
    enabled          bool,
    password         VARCHAR(100),
    legacyApiKey     VARCHAR(100) UNIQUE
);

INSERT INTO "user"
VALUES (DEFAULT, current_timestamp, current_timestamp, 'admin', 'Claudiu', 'Iova', 'admin user', true, 'mypassword',
        '');

CREATE TABLE IF NOT EXISTS ROLE
(
    id         SERIAL PRIMARY KEY,
    permission VARCHAR(10) NOT NULL,
    user_id    INT         NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES "user" (id)
);

INSERT INTO ROLE
VALUES (DEFAULT, 'Admin', 1);

INSERT INTO ROLE
VALUES (DEFAULT, 'User', 1);

INSERT INTO ROLE
VALUES (DEFAULT, 'Anyone', 1);