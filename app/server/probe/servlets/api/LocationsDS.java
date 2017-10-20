package probe.servlets.api;

import com.isomorphic.datasource.BasicDataSource;
import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;
import org.apache.log4j.Logger;
import probe.db.api.LocationsApi;
import probe.db.structs.Location;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class LocationsDS extends BasicDataSource {
    private Logger log = Logger.getLogger(LocationsDS.class);

    @Override
    public DSResponse executeFetch(DSRequest request) throws SQLException {
        Long parentLong = (Long) request.getCriteria().get("parent");
        Integer parent = (parentLong == null) ? null : parentLong.intValue();
        List<Location> locations = LocationsApi.fetch(parent);
        return new DSResponse(locations);
    }

    @Override
    public DSResponse executeUpdate(DSRequest request) throws Exception {
        Long id = (Long) request.getCriteria().get("id");
        Map values = request.getValues();
        values.remove("id");
        Location location = LocationsApi.update(id.intValue(), values);
        DSResponse response = new DSResponse(location);
        response.setAffectedRows(1);
        return response;
    }
}
