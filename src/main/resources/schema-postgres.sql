DROP TABLE IF EXISTS ticket;

CREATE TABLE ticket (
    id SERIAL PRIMARY KEY,
    title VARCHAR(30),
    text VARCHAR(300),
    finished BOOLEAN,
    created_at TIMESTAMP
);