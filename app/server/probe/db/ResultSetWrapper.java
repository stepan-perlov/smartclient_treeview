package probe.db;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ResultSetWrapper {
    private ResultSet resultSet;

    public ResultSetWrapper(ResultSet iResultSet) {
        resultSet = iResultSet;
    }

    public void close() throws SQLException {
        resultSet.close();
    }

    public boolean next() throws SQLException {
        return resultSet.next();
    }

    public @Nullable String getString(String key) throws SQLException {
        String value = resultSet.getString(key);
        return (resultSet.wasNull()) ? null : value;
    }

    public @Nullable Integer getInt(String key) throws SQLException {
        Integer value = resultSet.getInt(key);
        return (resultSet.wasNull()) ? null : value;
    }

    public @Nullable Long getLong(String key) throws SQLException {
        Long value = resultSet.getLong(key);
        return (resultSet.wasNull()) ? null : value;
    }

    public @Nullable Double getDouble(String key) throws SQLException {
        Double value = resultSet.getDouble(key);
        return (resultSet.wasNull()) ? null : value;
    }

    public @Nullable LocalDateTime getTimestamp(String key) throws SQLException {
        Timestamp value = resultSet.getTimestamp(key);
        return (resultSet.wasNull()) ? null : value.toLocalDateTime();
    }
    public @Nullable Boolean getBoolean(String key) throws SQLException {
        Boolean value = resultSet.getBoolean(key);
        return (resultSet.wasNull()) ? null : value;
    }

    public @NotNull Array getArray(String key) throws SQLException {
        return resultSet.getArray(key);
    }
}
