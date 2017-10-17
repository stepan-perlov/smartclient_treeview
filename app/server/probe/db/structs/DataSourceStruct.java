package probe.db.structs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.jetbrains.annotations.NotNull;
import probe.common.Serializable;

@JsonRootName(value = "DataSource")
public class DataSourceStruct extends Serializable {
    private static String dataFormat = "json";
    private String id;
    private String constructor;
    private JsonNode fields;

    public DataSourceStruct(
        @NotNull String iId,
        @NotNull String iConstructor,
        @NotNull JsonNode iFields
    ) {
        id = iId;
        constructor = iConstructor;
        fields = iFields;
    }

    public static @NotNull String getDataFormat() {return dataFormat;}

    @JacksonXmlProperty(localName = "ID", isAttribute = true)
    @JsonProperty("ID")
    public @NotNull String getId() {
        return id;
    }


    @JacksonXmlProperty(localName = "serverConstructor", isAttribute = true)
    @JsonProperty("serverConstructor")
    public @NotNull String getConstructor() { return constructor; }

    public @NotNull JsonNode getFields() {
        return fields;
    }
}
