package probe.db.api;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.postgresql.core.Utils;
import probe.common.Helpers;
import probe.db.Postgresql;
import probe.db.PreparedStatementWrapper;
import probe.db.ResultSetWrapper;
import probe.db.structs.Location;
import serp.util.Strings;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                    resultSet.getInt("o_parent"),
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

    public static Location update(int iId, Map iValues) throws SQLException {
        List<String> patchFields = new ArrayList<>();
        if (iValues.get("name") != null) {
            patchFields.add("name = " + Postgresql.quoteLiteral((String)iValues.get("name")));
        }
        if (iValues.get("type") != null) {
            patchFields.add("type = " + Postgresql.quoteLiteral((String)iValues.get("type")));
        }
        String patch = (patchFields.size() > 0) ? Strings.join(patchFields.toArray(), ",\n") : "id = id";

        Connection conn = null;
        PreparedStatementWrapper stmt = null;
        ResultSetWrapper resultSet = null;
        Location location;
        try {
            conn = Postgresql.connect();

            stmt = new PreparedStatementWrapper(
                conn.prepareStatement(
                    "SELECT * FROM locations_update(?::int, ?::text)"
                )
            );
            stmt.setInt(1, iId);
            stmt.setString(2, patch);

            resultSet = stmt.executeQuery();
            conn.commit();

            resultSet.next();
            location = new Location(
                resultSet.getInt("o_id"),
                resultSet.getInt("o_parent"),
                Location.Type.valueOf(resultSet.getString("o_type")),
                resultSet.getString("o_name"),
                resultSet.getString("o_icon"),
                Helpers.stringToJson(resultSet.getString("o_data"))
            );
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return location;
    }
}
