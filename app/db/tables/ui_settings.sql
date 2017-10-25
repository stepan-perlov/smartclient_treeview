CREATE TABLE ui_settings(
  id text PRIMARY KEY,
  type ui_settings_type,
  settings jsonb
);
