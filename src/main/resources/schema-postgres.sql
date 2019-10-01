DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS credential;

CREATE TABLE ticket (
    id SERIAL PRIMARY KEY,
    title VARCHAR(30),
    text VARCHAR(300),
    finished BOOLEAN,
    created_at TIMESTAMP
);