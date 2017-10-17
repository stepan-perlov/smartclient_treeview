package probe.db;

import org.apache.log4j.Logger;
import org.postgresql.core.Utils;
import probe.common.Config;
import java.sql.*;

public class Postgresql {
    private static Logger log = Logger.getLogger(Postgresql.class);

    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Config config = Config.get();
        Connection conn = DriverManager.getConnection(
            config.getDbUrl(),
            config.getDbUser(),
            config.getDbPassword()
        );
        conn.setAutoCommit(false);
        return conn;
    }

    public static String quoteLiteral(String iValue) throws SQLException {
        return "'" + Utils.escapeLiteral( null, iValue, true ).toString() + "'";
    }
}
