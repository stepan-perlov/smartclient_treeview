CREATE OR REPLACE FUNCTION data_sources_fetch(
  OUT o_id          text,
  OUT o_constructor text,
  OUT o_fields      jsonb
) RETURNS SETOF RECORD AS $$
BEGIN
  RETURN QUERY
  SELECT
    id,
    constructor,
    fields
  FROM data_sources;
END;
$$ LANGUAGE plpgsql STABLE SECURITY DEFINER;
