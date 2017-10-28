CREATE OR REPLACE FUNCTION ui_settings_update(
  IN i_id       text,
  IN i_settings jsonb
) RETURNS VOID AS $function_body$
BEGIN
  UPDATE ui_settings
    SET settings = i_settings
    WHERE id = i_id;
END;
$function_body$ LANGUAGE plpgsql VOLATILE SECURITY DEFINER;
