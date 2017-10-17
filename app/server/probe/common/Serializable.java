package probe.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;

public class Serializable {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static XmlMapper xmlMapper = new XmlMapper();
    private static Logger log = Logger.getLogger(Serializable.class);

    public @Nullable String toJson() {
        try {
            return objectMapper.writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error(e);
            return null;
        }
    };

    public @Nullable String toXml() {
        try {
            return xmlMapper.writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error(e);
            return null;
        }

    }
}
