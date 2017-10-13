\echo '# Create enums'
\i 'enums/location_type.sql'

\echo '# Create tables'
\i 'tables/locations.sql'

\echo '# Insert data'
\i 'data/locations.sql'

\echo '# Create api'
\i 'api/locations_fetch.sql'
