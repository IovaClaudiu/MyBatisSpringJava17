CREATE TABLE IF NOT EXISTS AUTHORS
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    age        INT         NOT NULL,
    sex        VARCHAR(10) NOT NULL
);

INSERT INTO AUTHORS
VALUES (DEFAULT, 'Claudiu', 'Iova', 28, 'Male');

CREATE TABLE IF NOT EXISTS ARTICLES
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(100) NOT NULL,
    description VARCHAR(200) NOT NULL,
    author_id   INT          NOT NULL,
    CONSTRAINT fk_authors FOREIGN KEY (author_id) REFERENCES AUTHORS (id)
);

INSERT INTO ARTICLES
VALUES (DEFAULT, 'MyBatis Example', 'Small description', 1);

INSERT INTO ARTICLES
VALUES (DEFAULT, 'Another Example', 'Another Small description', 1);

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
    legacy_api_key     VARCHAR(100)
);

INSERT INTO USERS
VALUES (DEFAULT, current_timestamp, current_timestamp, 'admin', 'admin', 'admin', 'admin user', true,
        '$2a$12$cpLz6YRHfrnO/QVWP0jtCuPiw2klPy4BKNP1n3GCzei0oxHd8xhMW',
        '');

INSERT INTO USERS
VALUES (DEFAULT, current_timestamp, current_timestamp, 'user', 'user', 'user', 'user user', true,
        '$2a$12$cpLz6YRHfrnO/QVWP0jtCuPiw2klPy4BKNP1n3GCzei0oxHd8xhMW',
        '');

INSERT INTO USERS
VALUES (DEFAULT, current_timestamp, current_timestamp, 'viewer', 'viewer', 'viewer', 'viewer user', true,
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
VALUES (DEFAULT, 'USER', 2);

INSERT INTO ROLES
VALUES (DEFAULT, 'VIEWER', 3);