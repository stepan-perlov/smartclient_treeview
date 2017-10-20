package probe.db.structs;

import org.jetbrains.annotations.Nullable;
import probe.common.Serializable;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class Location extends Serializable {
    private int id;
    private Integer parent;
    private Type type;
    private String name;
    private String icon;
    private Map<String, Object> data;

    public Location(
        int iId,
        @Nullable Integer iParent,
        @NotNull Type iType,
        @NotNull String iName,
        @Nullable String iIcon,
        @NotNull Map<String, Object> iData
    ) {
        id = iId;
        parent = iParent;
        type = iType;
        name = iName;
        icon = iIcon;
        data = iData;
    }

    public int getId() {
        return id;
    }

    public @Nullable Integer getParent() { return parent; }

    public @NotNull Type getType() {
        return type;
    }

    public @NotNull String getName() {
        return name;
    }

    public @Nullable String getIcon() {
        return icon;
    }

    public @NotNull Map<String, Object> getData() {
        return data;
    }

    public boolean getIsFolder() { return type != Type.COUNTRY; }

    public enum Type {
        CONTINENT,
        MACRO_REGION,
        COUNTRY
    }
}
