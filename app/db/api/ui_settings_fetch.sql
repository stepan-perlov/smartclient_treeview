CREATE OR REPLACE FUNCTION ui_settings_fetch(
  OUT o_id       text,
  OUT o_type     text,
  OUT o_settings jsonb
) RETURNS SETOF RECORD AS $function_body$
BEGIN
  RETURN QUERY
    SELECT
      id,
      type::text AS type,
      coalesce(settings, '{}'::jsonb) AS settings
    FROM ui_settings;
END;
$function_body$ LANGUAGE plpgsql STABLE SECURITY DEFINER;
