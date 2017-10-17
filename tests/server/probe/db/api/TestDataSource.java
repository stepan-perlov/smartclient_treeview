package probe.db.api;

import org.apache.log4j.Logger;
import org.junit.Test;

public class TestDataSource {
    private static Logger log = Logger.getLogger(TestLocations.class);

    @Test
    public void viewFetchResultInXml() throws Exception {
        log.info(DataSourcesApi.fetch().get(0).toXml());
    }
}
