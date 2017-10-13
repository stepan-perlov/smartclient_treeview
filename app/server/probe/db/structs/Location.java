package probe.db.structs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Nullable;
import probe.common.Helpers;

import javax.validation.constraints.NotNull;

public class Location {
    private int id;
    private Type type;
    private String name;
    private String icon;
    private JsonNode data;

    public Location(
        int iId,
        @NotNull Type iType,
        @NotNull String iName,
        @Nullable String iIcon,
        @NotNull JsonNode iData
    ) {
        id = iId;
        type = iType;
        name = iName;
        icon = iIcon;
        data = iData;
    }

    public int getId() {
        return id;
    }

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

    public String toString() {
        return Helpers.objectToString(this);
    }

    public enum Type {
        CONTINENT,
        MACRO_REGION,
        COUNTRY,
        CITY
    }
}
