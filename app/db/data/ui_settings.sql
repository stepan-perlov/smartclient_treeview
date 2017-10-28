INSERT INTO ui_settings(
  id,
  type,
  settings
) VALUES (
  'locations_tree',
  'TREE_GRID'::ui_settings_type,
  '{
    "width": 640,
    "height": 480
  }'::jsonb
);
