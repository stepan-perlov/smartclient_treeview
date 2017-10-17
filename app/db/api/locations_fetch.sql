CREATE OR REPLACE FUNCTION locations_fetch(
    IN i_parent int,
    OUT o_id     int,
    OUT o_parent int,
    OUT o_type   text,
    OUT o_name   text,
    OUT o_icon   text,
    OUT o_data   jsonb
) RETURNS SETOF RECORD AS $function_body$
BEGIN
    RETURN QUERY
        SELECT
            id,
            parent,
            type::text AS type,
            name,
            icon,
            coalesce(data, '{}'::jsonb) AS data
        FROM locations
        WHERE (i_parent IS NULL AND parent IS NULL)
            OR parent = i_parent;
END;
$function_body$ LANGUAGE plpgsql STABLE SECURITY DEFINER;
