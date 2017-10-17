CREATE OR REPLACE FUNCTION locations_update(
  IN i_id    int,
  IN i_patch text,
  OUT o_id     int,
  OUT o_parent int,
  OUT o_type   text,
  OUT o_name   text,
  OUT o_icon   text,
  OUT o_data   jsonb
) RETURNS SETOF RECORD AS $function_body$
BEGIN
  RETURN QUERY EXECUTE format($query$
    WITH view AS (
      UPDATE locations
      SET %s
      WHERE id = $1
      RETURNING *
    ) SELECT
      id,
      parent,
      type::text AS type,
      name,
      icon,
      coalesce(data, '{}'::jsonb) AS data
    FROM view
  $query$, i_patch) USING i_id;
END;
$function_body$ LANGUAGE plpgsql VOLATILE SECURITY DEFINER;
