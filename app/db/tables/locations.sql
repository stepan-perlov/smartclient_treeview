CREATE TABLE locations(
  id serial PRIMARY KEY,
  parent int REFERENCES locations,
  type location_type NOT NULL,
  name text NOT NULL,
  icon text,
  data jsonb
);
