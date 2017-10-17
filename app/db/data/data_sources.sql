INSERT INTO data_sources(
  id,
  constructor,
  fields
) VALUES (
  'locations',
  'probe.servlets.api.LocationsDS',
  '[
      {"title": "Id", "name":"id", "primaryKey": true},
      {"title":"Type", "name":"type"},
      {"title":"Name", "name":"name"},
      {"title":"Icon", "name":"icon"},
      {"title":"Data", "name":"data"}
  ]'::jsonb
);
