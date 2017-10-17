package probe.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import javax.validation.constraints.NotNull;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

public class Helpers {
    public static Logger log = Logger.getLogger(Helpers.class);

    public static @NotNull JsonNode stringToJson(String iData) {
        JsonNode oResult = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            oResult = mapper.readTree(iData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return oResult;
    }

    public static @NotNull String listToJson(List<? extends Serializable> iList) {
        return String.format(
            "[%s]",
                String.join(
                ",",
                iList.stream().map(
                    (Serializable item)-> item.toJson()
                ).collect(Collectors.toList())
            )
        );
    }

    public static @NotNull String documentToString(Document document) {
        try {
            DOMSource domSource = new DOMSource(document);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();

            StringWriter stringWriter = new StringWriter();
            StreamResult resultStream = new StreamResult(stringWriter);

            transformer.transform(domSource, resultStream);
            return stringWriter.toString();
        } catch (TransformerException e) {
            log.error(e);
            return "";
        }

    }
}
