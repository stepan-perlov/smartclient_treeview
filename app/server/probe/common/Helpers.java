package probe.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Locale;

public class Helpers {
    public static Logger log = Logger.getLogger(Helpers.class);

    public static JsonNode stringToJson(String iData) {
        JsonNode oResult = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            oResult = mapper.readTree(iData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return oResult;
    }

    public static String objectToString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writer().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e);
            return "Dump error";
        }
    }
}
