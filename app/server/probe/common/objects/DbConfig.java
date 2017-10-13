package probe.common.objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import org.jetbrains.annotations.NotNull;

public class DbConfig {
    private String connurl;
    private String host;
    private int port;
    private String user;
    private String password;
    private String dbname;

    public DbConfig(@NotNull JsonNode iParams) {
        NullNode nullNode = NullNode.getInstance();

        JsonNode iHost = iParams.get("host");
        JsonNode iPort = iParams.get("port");
        host = (iHost == null || iHost == nullNode) ? "localhost" : iHost.asText();
        port = (iPort == null || iPort == nullNode) ? 5432 : iPort.asInt();;

        JsonNode iUser = iParams.get("user");
        JsonNode iPassword = iParams.get("password");
        user = (iUser == null || iUser == nullNode) ? "postgres" : iUser.asText();
        password = (iPassword == null || iParams == nullNode) ? "" : iPassword.asText();

        dbname = iParams.get("dbname").asText();

        connurl = String.format("jdbc:postgresql://%s:%d/%s", host, port, dbname);
    }

    public @NotNull String getConnurl() {
        return connurl;
    }

    public @NotNull String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public @NotNull String getUser() {
        return user;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public @NotNull String getDbname() {
        return dbname;
    }
}

