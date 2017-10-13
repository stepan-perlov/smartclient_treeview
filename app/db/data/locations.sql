INSERT INTO locations(
    id,
    parent,
    type,
    name,
    icon,
    data
) VALUES (
    -5,
    null,
    'CONTINENT'::location_type,
    'Евразия',
    null,
    null
), (
    -4,
    null,
    'CONTINENT'::location_type,
    'Африка',
    null,
    null
), (
    -3,
    -5,
    'MACRO_REGION'::location_type,
    'Европа',
    null,
    null
), (
    -2,
    -5,
    'MACRO_REGION'::location_type,
    'Азия',
    null,
    null
), (
    -1,
    -4,
    'MACRO_REGION'::location_type,
    'Северная Африка',
    null,
    null
), (
    0,
    -4,
    'MACRO_REGION'::location_type,
    'Южная Африка',
    null,
    null
), (
    nextval('locations_id_seq'),
    -3,
    'COUNTRY'::location_type,
    'Франция',
    null,
    null
), (
    nextval('locations_id_seq'),
    -3,
    'COUNTRY'::location_type,
    'Германия',
    null,
    null
), (
    nextval('locations_id_seq'),
    -3,
    'COUNTRY'::location_type,
    'Россия',
    null,
    null
), (
    nextval('locations_id_seq'),
    -2,
    'COUNTRY'::location_type,
    'Россия',
    null,
    null
), (
    nextval('locations_id_seq'),
    -2,
    'COUNTRY'::location_type,
    'КНР',
    null,
    null
), (
    nextval('locations_id_seq'),
    -2,
    'COUNTRY'::location_type,
    'Республика Корея',
    null,
    null
);