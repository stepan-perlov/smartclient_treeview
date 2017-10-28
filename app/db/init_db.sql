\echo '# Create extensions'
CREATE EXTENSION IF NOT EXISTS plpython3u;

\echo '# Create helpers'
\i 'helpers/json_extend.plpy'
\i 'helpers/jsonb_extend.plpy'

\echo '# Create enums'
\i 'enums/location_type.sql'
\i 'enums/location_icon.sql'
\i 'enums/ui_settings_type.sql'

\echo '# Create tables'
\i 'tables/data_sources.sql'
\i 'tables/locations.sql'
\i 'tables/ui_settings.sql'

\echo '# Insert data'
\i 'data/data_sources.sql'
\i 'data/locations.sql'
\i 'data/ui_settings.sql'

\echo '# Create api'
\i 'api/data_sources_fetch.sql'
\i 'api/locations_fetch.sql'
\i 'api/locations_add.sql'
\i 'api/locations_update.sql'
\i 'api/locations_remove.sql'
\i 'api/ui_settings_fetch.sql'
\i 'api/ui_settings_update.sql'
