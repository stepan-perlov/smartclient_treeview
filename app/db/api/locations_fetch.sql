CREATE OR REPLACE FUNCTION locations_fetch(
    IN i_parent int,
    OUT o_id int,
    OUT o_type text,
    OUT o_name text,
    OUT o_icon text,
    OUT o_data jsonb
) RETURNS SETOF RECORD AS $$
BEGIN
    RETURN QUERY
        SELECT
            id,
            type::text,
            name,
            icon,
            coalesce(data, '{}'::jsonb)
        FROM locations
        WHERE (i_parent IS NULL AND parent IS NULL)
            OR parent = i_parent;
END;
$$ LANGUAGE plpgsql STABLE SECURITY DEFINER;
