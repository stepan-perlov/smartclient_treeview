package probe.db.structs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import probe.common.Helpers;
import probe.common.Serializable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Iterator;

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

    @JsonProperty("ID")
    public @NotNull String getId() {
        return id;
    }


    @JsonProperty("serverConstructor")
    public @NotNull String getConstructor() { return constructor; }

    public @NotNull JsonNode getFields() {
        return fields;
    }

    public @NotNull String toXml() throws ParserConfigurationException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element rootNode = document.createElement("DataSource");
        rootNode.setAttribute("ID", id);
        rootNode.setAttribute("serverConstructor", constructor);
        document.appendChild(rootNode);

        Element fieldsNode = document.createElement("fields");
        Element fieldItemNode;
        Iterator<JsonNode> iterator = fields.iterator();
        while (iterator.hasNext()) {
            JsonNode fieldItem = iterator.next();
            fieldItemNode = document.createElement("field");
            fieldItemNode.setAttribute("name", fieldItem.get("name").asText());
            fieldItemNode.setAttribute("title", fieldItem.get("title").asText());
            if (fieldItem.get("primaryKey") != null) {
                fieldItemNode.setAttribute("primaryKey", fieldItem.get("primaryKey").asText());
            }
            fieldsNode.appendChild(fieldItemNode);
        }
        rootNode.appendChild(fieldsNode);
        return Helpers.documentToString(document);
    }
}
