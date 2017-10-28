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
    '/icons/continents/asia.png'::location_icon,
    '{
        "population": 100000,
        "area": 30000
    }'
), (
    -4,
    null,
    'CONTINENT'::location_type,
    'Африка',
    '/icons/continents/africa.png'::location_icon,
    '{
        "population": 100000,
        "area": 10000
    }'
), (
    -3,
    -5,
    'MACRO_REGION'::location_type,
    'Европа',
    '/icons/continents/asia.png'::location_icon,
    '{
        "population": 100000,
        "area": 10000
    }'
), (
    -2,
    -5,
    'MACRO_REGION'::location_type,
    'Азия',
    '/icons/continents/asia.png'::location_icon,
    '{
        "population": 100000,
        "area": 20000
    }'
), (
    -1,
    -4,
    'MACRO_REGION'::location_type,
    'Северная Африка',
    '/icons/continents/africa.png'::location_icon,
    '{
        "population": 100000,
        "area": 5000
    }'
), (
    0,
    -4,
    'MACRO_REGION'::location_type,
    'Южная Африка',
    '/icons/continents/africa.png'::location_icon,
    '{
        "population": 100000,
        "area": 5000
    }'
), (
    nextval('locations_id_seq'),
    -3,
    'COUNTRY'::location_type,
    'Франция',
    '/icons/flags/FR.png'::location_icon,
    '{
        "population": 100000,
        "area": 1000
    }'
), (
    nextval('locations_id_seq'),
    -3,
    'COUNTRY'::location_type,
    'Германия',
    '/icons/flags/GM.png'::location_icon,
    '{
        "population": 100000,
        "area": 1000
    }'
), (
    nextval('locations_id_seq'),
    -3,
    'COUNTRY'::location_type,
    'Россия',
    '/icons/flags/RS.png'::location_icon,
    '{
        "population": 100000,
        "area": 1000
    }'
), (
    nextval('locations_id_seq'),
    -2,
    'COUNTRY'::location_type,
    'Россия',
    '/icons/flags/RS.png'::location_icon,
    '{
        "population": 100000,
        "area": 1000
    }'
), (
    nextval('locations_id_seq'),
    -2,
    'COUNTRY'::location_type,
    'КНР',
    '/icons/flags/CH.png'::location_icon,
    '{
        "population": 100000,
        "area": 1000
    }'
), (
    nextval('locations_id_seq'),
    -2,
    'COUNTRY'::location_type,
    'Республика Корея',
    '/icons/flags/KR.png'::location_icon,
    '{
        "population": 100000,
        "area": 1000
    }'
);