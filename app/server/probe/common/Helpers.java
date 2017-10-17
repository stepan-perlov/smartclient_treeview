package probe.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.io.IOException;
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
}
