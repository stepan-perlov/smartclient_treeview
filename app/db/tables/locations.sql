CREATE TABLE locations(
  id serial PRIMARY KEY,
  parent int REFERENCES locations ON DELETE CASCADE,
  type location_type NOT NULL,
  name text NOT NULL,
  icon location_icon,
  data jsonb
);
