DROP TABLE IF EXISTS ticket;

CREATE TABLE ticket (
    id VARCHAR(36) PRIMARY KEY,
    title VARCHAR(100),
    text VARCHAR(200),
    finished BOOLEAN,
    created_at TIMESTAMP
);