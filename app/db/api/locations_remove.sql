CREATE OR REPLACE FUNCTION locations_remove(
  IN i_id     int
) RETURNS VOID AS $function_body$
BEGIN
  DELETE FROM locations
    WHERE id = i_id;
END;
$function_body$ LANGUAGE plpgsql VOLATILE SECURITY DEFINER;
