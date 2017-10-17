package probe.db.structs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;
import probe.common.Serializable;

import javax.validation.constraints.NotNull;

public class Location extends Serializable {
    private int id;
    private Integer parent;
    private Type type;
    private String name;
    private String icon;
    private JsonNode data;

    public Location(
        int iId,
        @Nullable Integer iParent,
        @NotNull Type iType,
        @NotNull String iName,
        @Nullable String iIcon,
        @NotNull JsonNode iData
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

    @JsonProperty("parentId")
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

    public @NotNull JsonNode getData() {
        return data;
    }

    public enum Type {
        CONTINENT,
        MACRO_REGION,
        COUNTRY,
        CITY
    }
}
