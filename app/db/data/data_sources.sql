INSERT INTO data_sources(
  id,
  constructor,
  fields
) VALUES (
  'locations',
  'probe.servlets.api.LocationsDS',
  format(
    '[
      {"title": "Id", "name":"id", "primaryKey": true},
      {"title":"Type", "name":"type", "valueMap": [%s]},
      {"title":"Name", "name":"name"},
      {"title":"Icon", "name":"icon"},
      {"title":"Data", "name":"data"}
    ]',
    (SELECT string_agg('"'||item||'"', ',') FROM unnest(enum_range(null::location_type)) AS item)
  )::jsonb
);
