package probe.db.structs;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import probe.common.Serializable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Map;

public abstract class UiSettings extends Serializable {
    public static UiSettings create(
        @NotNull String iId,
        @NotNull Type iType,
        @NotNull ObjectNode iSettings
    ) {
        switch (iType) {
            case TREE_GRID:
                return new TreeGridSettings(
                        iId,
                        iType,
                        iSettings
                );
            default:
                throw new NotImplementedException();
        }
    }

    private String id;
    private Type type;
    private Map settings;

    protected UiSettings(
        @NotNull String iId,
        @NotNull Type iType,
        @NotNull ObjectNode iSettings
    ) {
        id = iId;
        type = iType;
        settings = settingsObjectToMap(iSettings);
    }

    protected abstract Map<String, Object> settingsObjectToMap(@NotNull ObjectNode iSettings);

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public Map getSettings() {
        return settings;
    }

    public enum Type {
        TREE_GRID
    }
}
