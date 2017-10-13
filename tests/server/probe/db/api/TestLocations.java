package probe.db.api;

import org.apache.log4j.Logger;
import org.junit.Test;

public class TestLocations {
    private Logger log = Logger.getLogger(TestLocations.class);
    @Test
    public void testFetch() throws Exception {
        log.info(LocationsApi.fetch(null).toString());
        log.info(LocationsApi.fetch(-5).toString());
        log.info(LocationsApi.fetch(-1).toString());
        log.info(LocationsApi.fetch(100000).toString());
    }
}
