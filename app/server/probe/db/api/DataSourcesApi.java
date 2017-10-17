package probe.db.api;

import org.apache.log4j.Logger;
import probe.common.Helpers;
import probe.db.Postgresql;
import probe.db.PreparedStatementWrapper;
import probe.db.ResultSetWrapper;
import probe.db.structs.DataSourceStruct;

import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataSourcesApi {
    private static Logger log = Logger.getLogger(DataSourcesApi.class);

    public static @NotNull List<DataSourceStruct> fetch() throws SQLException {
        Connection conn = null;
        PreparedStatementWrapper stmt = null;
        ResultSetWrapper resultSet = null;
        List<DataSourceStruct> dataSourceList = new ArrayList<>();
        try {
            conn = Postgresql.connect();

            stmt = new PreparedStatementWrapper(
                conn.prepareStatement(
                    "SELECT * FROM data_sources_fetch()"
                )
            );

            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                dataSourceList.add(new DataSourceStruct(
                    resultSet.getString("o_id"),
                    resultSet.getString("o_constructor"),
                    Helpers.stringToJson(resultSet.getString("o_fields"))
                ));
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return dataSourceList;
    }
}
