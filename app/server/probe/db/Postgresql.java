package probe.db;

import probe.common.Config;
import java.sql.*;

public class Postgresql {
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
}
