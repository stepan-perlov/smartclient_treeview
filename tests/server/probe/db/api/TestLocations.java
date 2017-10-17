package probe.db.api;

import org.apache.log4j.Logger;
import org.junit.Test;
import probe.common.Helpers;

public class TestLocations {
    private static Logger log = Logger.getLogger(TestLocations.class);

    @Test
    public void viewLocationsFetchInJson() throws Exception {
        log.info(Helpers.listToJson(LocationsApi.fetch(null)));
        log.info(Helpers.listToJson(LocationsApi.fetch(-5)));
        log.info(Helpers.listToJson(LocationsApi.fetch(-1)));
        log.info(Helpers.listToJson(LocationsApi.fetch(100000)));
    }
}
