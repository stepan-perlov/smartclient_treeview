package probe.db.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.log4j.Logger;
import probe.common.Helpers;
import probe.db.Postgresql;
import probe.db.PreparedStatementWrapper;
import probe.db.ResultSetWrapper;
import probe.db.structs.Location;
import probe.db.structs.UiSettings;
import serp.util.Strings;

import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UiSettingsApi {
    private static Logger log = Logger.getLogger(UiSettingsApi.class);

    public static @NotNull List<UiSettings> fetch() throws SQLException {
        Connection conn = null;
        PreparedStatementWrapper stmt = null;
        ResultSetWrapper resultSet = null;
        List<UiSettings> uiSettingsList = new ArrayList<>();
        try {
            conn = Postgresql.connect();

            stmt = new PreparedStatementWrapper(
                conn.prepareStatement(
                    "SELECT * FROM ui_settings_fetch()"
                )
            );

            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                uiSettingsList.add(UiSettings.create(
                    resultSet.getString("o_id"),
                    UiSettings.Type.valueOf(resultSet.getString("o_type")),
                    (ObjectNode) Helpers.stringToJson(resultSet.getString("o_settings"))
                ));
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return uiSettingsList;
    }

    public static void update(String iId, ObjectNode iSettings) throws SQLException {
        Connection conn = null;
        PreparedStatementWrapper stmt = null;
        try {
            conn = Postgresql.connect();

            stmt = new PreparedStatementWrapper(
                conn.prepareStatement(
                    "SELECT * FROM ui_settings_update(?::text, ?::jsonb)"
                )
            );
            stmt.setString(1, iId);
            stmt.setString(2, iSettings.toString());

            stmt.executeQuery();
            conn.commit();

        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}
