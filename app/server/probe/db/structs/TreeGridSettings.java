package probe.db.structs;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class TreeGridSettings extends UiSettings {
    public TreeGridSettings(
        @NotNull String iId,
        @NotNull Type iType,
        @NotNull ObjectNode iSettings
    ) {
        super(
            iId,
            iType,
            iSettings
        );
    }

    @Override
    protected Map<String, Object> settingsObjectToMap(@NotNull ObjectNode iSettings) {
        Map<String, Object> settingsMap = new HashMap<>();
        if (iSettings.get("width") != null) {
            settingsMap.put("width", iSettings.get("width").asText());
        }
        return settingsMap;
    }
}
