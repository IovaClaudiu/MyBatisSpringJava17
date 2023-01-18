CREATE TABLE IF NOT EXISTS ARTICLES
(
    id     INTEGER PRIMARY KEY,
    title  VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL
);

INSERT INTO ARTICLES
VALUES (1, 'MyBatis Example', 'Iova');

CREATE TABLE IF NOT EXISTS USERS
(
    id                 SERIAL PRIMARY KEY,
    date_created       timestamp,
    last_modified_date timestamp,
    username           VARCHAR(100) UNIQUE,
    first_name         VARCHAR(100),
    last_name          VARCHAR(100),
    description        VARCHAR(100),
    enabled            bool,
    password           VARCHAR(100),
    legacy_api_key     VARCHAR(100) UNIQUE
);

INSERT INTO USERS
VALUES (DEFAULT, current_timestamp, current_timestamp, 'admin', 'Claudiu', 'Iova', 'admin user', true,
        '$2a$12$cpLz6YRHfrnO/QVWP0jtCuPiw2klPy4BKNP1n3GCzei0oxHd8xhMW',
        '');

CREATE TABLE IF NOT EXISTS ROLES
(
    id         SERIAL PRIMARY KEY,
    permission VARCHAR(10) NOT NULL,
    user_id    INT         NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES USERS (id)
);

INSERT INTO ROLES
VALUES (DEFAULT, 'ADMIN', 1);

INSERT INTO ROLES
VALUES (DEFAULT, 'USER', 1);

INSERT INTO ROLES
VALUES (DEFAULT, 'ANYONE', 1);