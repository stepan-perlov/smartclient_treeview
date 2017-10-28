package probe.db.structs;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class TreeGridSettings extends UiSettings {
    private final static int DEFAULT_WIDTH = 640;
    private final static int DEFAULT_HEIGHT = 480;
    private int width;
    private int height;

    public TreeGridSettings(
        @NotNull String iId,
        @NotNull ObjectNode iSettings
    ) {
        super(
            iId,
            Type.TREE_GRID
        );
        width = (iSettings.get("width") == null) ? DEFAULT_WIDTH : iSettings.get("width").asInt();
        height = (iSettings.get("height") == null) ? DEFAULT_HEIGHT : iSettings.get("height").asInt();
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
