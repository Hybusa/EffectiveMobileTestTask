-- liquibase formatted sql

-- changeset akuznetsov:1
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    login      VARCHAR(64) UNIQUE NOT NULL,
    password   VARCHAR(64) NOT NULL,
    role       varchar(16) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);
CREATE TABLE tasks
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(32) NOT NULL,
    description VARCHAR(64) NOT NULL,
    status      VARCHAR(16) NOT NULL,
    priority    VARCHAR(8)  NOT NULL,
    author_id   BIGINT      NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    assigned_id BIGINT REFERENCES users (id)
);
CREATE TABLE comments
(
    id        BIGSERIAL PRIMARY KEY,
    text      VARCHAR(64),
    author_id BIGINT NOT NULL REFERENCES users (id)
        ON DELETE CASCADE,
    task_id   BIGINT NOT NULL REFERENCES tasks (id)
        ON DELETE CASCADE
);
