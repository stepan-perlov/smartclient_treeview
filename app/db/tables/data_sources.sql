CREATE TABLE data_sources(
    id text PRIMARY KEY,
    constructor text UNIQUE NOT NULL,
    fields jsonb NOT NULL
);
