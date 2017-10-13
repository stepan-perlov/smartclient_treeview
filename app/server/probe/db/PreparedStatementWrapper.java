package probe.db;

import java.sql.*;
import java.time.LocalDateTime;

// TODO: define all methods https://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
public class PreparedStatementWrapper {
    private PreparedStatement statement;

    public PreparedStatementWrapper(PreparedStatement iStatement) {
        statement = iStatement;
    }

    public ResultSetWrapper executeQuery() throws SQLException {
        return new ResultSetWrapper(statement.executeQuery());
    }

    public void close() throws SQLException {
        statement.close();
    }

    public void setString(int iIndex, String iValue) throws SQLException {
        if (iValue == null) {
            statement.setNull(iIndex, Types.VARCHAR);
        } else {
            statement.setString(iIndex, iValue);
        }
    }

    public void setInt(int iIndex, Integer iValue)  throws SQLException {
        if (iValue == null) {
            statement.setNull(iIndex, Types.INTEGER);
        } else {
            statement.setInt(iIndex, iValue);
        }
    }

    public void setLong(int iIndex, Long iValue)  throws SQLException {
        if (iValue == null) {
            statement.setNull(iIndex, Types.BIGINT);
        } else {
            statement.setLong(iIndex, iValue);
        }
    }

    public void setTimestamp(int iIndex, LocalDateTime iValue) throws SQLException {
        if (iValue == null) {
            statement.setNull(iIndex, Types.TIMESTAMP);
        } else {
            statement.setTimestamp(iIndex, Timestamp.valueOf(iValue));
        }
    }

    public void setJson(int iIndex, String iValue) throws SQLException {
        if (iValue == null || iValue.length() < 2) {
            statement.setString(iIndex, "{}");
        } else {
            statement.setString(iIndex, iValue);
        }
    }

    public void setArray(int iIndex, Array iValue) throws SQLException {
        statement.setArray(iIndex, iValue);
    }

    public PreparedStatement getStatement() {
        return statement;
    }
}
