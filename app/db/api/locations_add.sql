CREATE OR REPLACE FUNCTION locations_add(
  IN i_parent int,
  IN i_type   text,
  IN i_name   text,
  IN i_icon   text,
  IN i_data   jsonb,
  OUT o_id     int,
  OUT o_parent int,
  OUT o_type   text,
  OUT o_name   text,
  OUT o_icon   text,
  OUT o_data   jsonb
) RETURNS SETOF RECORD AS $function_body$
BEGIN
  RETURN QUERY
    WITH view AS (
      INSERT INTO locations(
        parent,
        type,
        name,
        icon,
        data
      ) VALUES(
        i_parent,
        i_type::location_type,
        i_name,
        i_icon::location_icon,
        i_data
      ) RETURNING *
    )
    SELECT
      id,
      parent,
      type::text,
      name,
      icon::text,
      coalesce(data, '{}'::jsonb) AS data
    FROM view;
END;
$function_body$ LANGUAGE plpgsql VOLATILE SECURITY DEFINER;
