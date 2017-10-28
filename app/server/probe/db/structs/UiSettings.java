package probe.db.structs;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import probe.common.Serializable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Map;

public class UiSettings extends Serializable {
    public static UiSettings create(
        @NotNull String iId,
        @NotNull Type iType,
        @NotNull ObjectNode iSettings
    ) {
        switch (iType) {
            case TREE_GRID:
                return new TreeGridSettings(
                        iId,
                        iSettings
                );
            default:
                throw new NotImplementedException();
        }
    }

    private String id;
    private Type type;

    protected UiSettings(
        @NotNull String iId,
        @NotNull Type iType
    ) {
        id = iId;
        type = iType;
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        TREE_GRID
    }
}
