INSERT INTO data_sources(
  id,
  constructor,
  fields
) VALUES (
  'locations_data',
  'probe.servlets.api.LocationsDataDS',
  '[
    {"title": "Population", "name": "population", "type": "integer"},
    {"title": "Area", "name": "area", "type": "integer"}
  ]'
), (
  'locations',
  'probe.servlets.api.LocationsDS',
  format(
    '[
      {"title": "Id", "name":"id", "type": "integer", "primaryKey": true},
      {"title":"Parent", "name":"parent", "type": "integer", "foreignKey": "locations.id"},
      {"title":"Type", "name":"type", "type": "enum", "valueMap": [%s]},
      {"title":"Name", "name":"name", "type": "string"},
      {"title":"Icon", "name":"icon", "type": "enum", "valueMap": [%s]},
      {"title":"Data", "name": "data", "type": "locations_data"}
    ]',
    (SELECT string_agg('"'||item||'"', ',') FROM unnest(enum_range(null::location_type)) AS item),
    (SELECT string_agg('"'||item||'"', ',') FROM unnest(enum_range(null::icon)) AS item)
  )::jsonb
);
