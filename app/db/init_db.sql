\echo '# Create enums'
\i 'enums/location_type.sql'

\echo '# Create tables'
\i 'tables/data_sources.sql'
\i 'tables/locations.sql'

\echo '# Insert data'
\i 'data/data_sources.sql'
\i 'data/locations.sql'

\echo '# Create api'
\i 'api/data_sources_fetch.sql'
\i 'api/locations_fetch.sql'
