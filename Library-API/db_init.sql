CREATE TABLE library
(
    id   BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE book
(
    id      BIGINT PRIMARY KEY,
    title   VARCHAR(255) NOT NULL,
    author  VARCHAR(255) NOT NULL,
    status  VARCHAR(10)  NOT NULL,
    library BIGINT       NOT NULL,
    FOREIGN KEY (library) REFERENCES library (id)
);

CREATE TABLE reader
(
    id   BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);