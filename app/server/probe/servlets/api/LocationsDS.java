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
        Long parentIdLong = (Long) request.getCriteria().get("parentId");
        Integer parentId = (parentIdLong == null) ? null : parentIdLong.intValue();
        List<Location> locations = LocationsApi.fetch(parentId);
        return new DSResponse(locations);
    }

    @Override
    public DSResponse executeUpdate(DSRequest request) throws SQLException {
        Long id = (Long) request.getCriteria().get("id");
        Map values = request.getValues();
        values.remove("id");
        Location location = LocationsApi.update(id.intValue(), values);
        return new DSResponse(location);
    }
}
