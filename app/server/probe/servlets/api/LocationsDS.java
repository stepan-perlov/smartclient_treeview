package probe.servlets.api;

import com.isomorphic.datasource.BasicDataSource;
import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;
import com.isomorphic.servlet.RESTHandler;
import org.apache.log4j.Logger;
import probe.common.Helpers;
import probe.db.api.LocationsApi;
import probe.db.structs.Location;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LocationsDS extends BasicDataSource {
    private Logger log = Logger.getLogger(LocationsDS.class);

    public DSResponse executeFetch(DSRequest request) throws Exception {
        Long parentIdLong = (Long) request.getCriteria().get("parentId");
        Integer parentId = (parentIdLong == null) ? null : parentIdLong.intValue();
        List<Location> locations = LocationsApi.fetch(parentId);
        return new DSResponse(locations);
    }
}
