package probe.servlets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.isomorphic.datasource.BasicDataSource;
import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;
import probe.db.api.UiSettingsApi;
import probe.db.structs.TreeGridSettings;

import java.util.Map;

public class TreeGridSettingsDS extends BasicDataSource {
    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public DSResponse executeUpdate(DSRequest request) throws Exception {
        Map values = request.getValues();
        String id = (String) values.get("id");

        ObjectNode settings = mapper.createObjectNode();
        if (values.get("width") != null) {
            settings.put("width", ((Long)values.get("width")).intValue());
        }
        if (values.get("height") != null) {
            settings.put("height", ((Long)values.get("height")).intValue());
        }

        UiSettingsApi.update(id, settings);
        DSResponse response = new DSResponse(new TreeGridSettings(id, settings));
        response.setAffectedRows(1);
        return response;
    }
}
