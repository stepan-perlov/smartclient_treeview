package probe.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import probe.common.objects.DbConfig;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Config {
    private static Config instance;
    static {
        try {
            File fstream = new File(Config.class.getResource("config.json").toURI());
            String configString = new String(FileUtils.readFileToByteArray(fstream), "UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode configJson = mapper.readTree(configString);
            instance = new Config(configJson);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static Config get() {
        return instance;
    }

    private DbConfig dbConfig;

    private Config(@NotNull JsonNode iConfigJson) {
        dbConfig = new DbConfig(iConfigJson.get("db"));
    }

    public @NotNull String getDbUrl() {
        return dbConfig.getConnurl();
    }

    public @NotNull String getDbHost() {
        return dbConfig.getHost();
    }

    public int getDbPort() {
        return dbConfig.getPort();
    }

    public @NotNull String getDbUser() {
        return dbConfig.getUser();
    }

    public @NotNull String getDbPassword() {
        return dbConfig.getPassword();
    }

    public @NotNull String getDbname() {
        return dbConfig.getDbname();
    }
}
