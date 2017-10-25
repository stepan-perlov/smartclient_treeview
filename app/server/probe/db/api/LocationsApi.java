package probe.db.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.isomorphic.datasource.DSField;
import com.isomorphic.datasource.DataSource;
import groovy.sql.Sql;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
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
    private static DataSource locationsData;
    static {
        try {
            locationsData = DataSource.getDynamicDataSource("locations_data", null);
        } catch (Exception e) {
            log.error(e);
        }
    }
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static ObjectNode dataMapToObject(Map dataMap) {
        ObjectNode data = objectMapper.createObjectNode();
        for (Object objectKey : dataMap.keySet()) {
            String key = (String) objectKey;
            DSField dataField = locationsData.getField(key);

            switch (dataField.getType()) {
                case "string":
                    data.put(key, (String) dataMap.get(key));
                    break;
                case "integer":
                    data.put(key, (Long) dataMap.get(key));
                    break;
                default:
                    log.warn(String.format("Unexpect type `%s` for data field `%s`", dataField.getType(), key));
                    break;
            }
        }
        return data;
    }

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
                    Helpers.stringToMap(resultSet.getString("o_data"))
                ));
             }
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return locationList;
    }

    public static Location add(Map iValues) throws SQLException {
        ObjectNode data = null;
        if (iValues.get("data") != null) {
            data = dataMapToObject((Map) iValues.get("data"));
        }
        
        Connection conn = null;
        PreparedStatementWrapper stmt = null;
        ResultSetWrapper resultSet = null;
        Location location;
        try {
            conn = Postgresql.connect();

            stmt = new PreparedStatementWrapper(
                conn.prepareStatement(
                    "SELECT * FROM locations_add(?::int, ?::text, ?::text, ?::text, ?::jsonb)"
                )
            );
            stmt.setInt(1, ((Long) iValues.get("parent")).intValue());
            stmt.setString(2, (String) iValues.get("type"));
            stmt.setString(3, (String) iValues.get("name"));
            stmt.setString(4, (String) iValues.get("icon"));
            stmt.setString(5, (data == null) ? null : data.toString());

            resultSet = stmt.executeQuery();
            conn.commit();

            resultSet.next();
            location = new Location(
                resultSet.getInt("o_id"),
                resultSet.getInt("o_parent"),
                Location.Type.valueOf(resultSet.getString("o_type")),
                resultSet.getString("o_name"),
                resultSet.getString("o_icon"),
                Helpers.stringToMap(resultSet.getString("o_data"))
            );
            log.info(location.toJson());
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return location;
    }

    public static Location update(int iId, Map iValues) throws SQLException {
        List<String> patchFields = new ArrayList<>();
        if (iValues.get("type") != null) {
            patchFields.add("type = " + Postgresql.quoteLiteral((String)iValues.remove("type")));
        }

        if (iValues.get("name") != null) {
            patchFields.add("name = " + Postgresql.quoteLiteral((String)iValues.remove("name")));
        }

        if (iValues.get("icon") != null) {
            patchFields.add("icon = " + Postgresql.quoteLiteral((String)iValues.remove("icon")));
        }

        if (iValues.get("data") != null) {
            ObjectNode dataPatch = dataMapToObject((Map) iValues.get("data"));

            String dataPatchValue = dataPatch.toString();
            if (dataPatchValue.length() > 2) {
                patchFields.add(String.format("data = jsonb_extend(coalesce(data, '{}'::jsonb), %s::jsonb)", Postgresql.quoteLiteral(dataPatchValue)));
            }
        }

        String patch = (patchFields.size() > 0) ? Strings.join(patchFields.toArray(), ",\n") : "id = id";
        log.info(patch);

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
                Helpers.stringToMap(resultSet.getString("o_data"))
            );
            log.info(location.toJson());
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return location;
    }

    public static void remove(int iId) throws SQLException {
        Connection conn = null;
        PreparedStatementWrapper stmt = null;
        ResultSetWrapper resultSet = null;
        try {
            conn = Postgresql.connect();

            stmt = new PreparedStatementWrapper(
                conn.prepareStatement(
                    "SELECT * FROM locations_remove(?::int)"
                )
            );
            stmt.setInt(1, iId);

            stmt.executeQuery();
            conn.commit();
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}
