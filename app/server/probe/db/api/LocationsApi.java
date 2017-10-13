package probe.db.api;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import probe.common.Helpers;
import probe.db.Postgresql;
import probe.db.PreparedStatementWrapper;
import probe.db.ResultSetWrapper;
import probe.db.structs.Location;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationsApi {
    private static Logger log = Logger.getLogger(LocationsApi.class);

    public static List<Location> fetch(@Nullable Integer iParent) throws SQLException {
        Connection conn = null;
        PreparedStatementWrapper stmt = null;
        ResultSetWrapper resultSet = null;
        List<Location> locationList = new ArrayList<>();
        try {
             conn = Postgresql.connect();

             stmt = new PreparedStatementWrapper(
                 conn.prepareStatement(
                     "SELECT * FROM locations_fetch(?::int)"
                 )
             );
             stmt.setInt(1, iParent);

             resultSet = stmt.executeQuery();
             while (resultSet.next()) {
                locationList.add(new Location(
                    resultSet.getInt("o_id"),
                    Location.Type.valueOf(resultSet.getString("o_type")),
                    resultSet.getString("o_name"),
                    resultSet.getString("o_icon"),
                    Helpers.stringToJson(resultSet.getString("o_data"))
                ));
             }
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return locationList;
    }
}
