package probe.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;

public class Serializable {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Logger log = Logger.getLogger(Serializable.class);

    public @Nullable String toJson() {
        try {
            return objectMapper.writer().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error(e);
            return null;
        }
    };
}
