package probe.db;

import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DataSource;
import com.isomorphic.datasource.DynamicDSGenerator;
import org.apache.log4j.Logger;
import probe.db.api.DataSourcesApi;
import probe.db.structs.DataSourceStruct;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProbeDSGenerator implements DynamicDSGenerator {
    private static Logger log = Logger.getLogger(ProbeDSGenerator.class);
    private static Map<String, DataSource> dataSourceMap = new HashMap<>();

    public ProbeDSGenerator() throws Exception {
        for (DataSourceStruct dataSourceStruct : DataSourcesApi.fetch()) {
            dataSourceMap.put(dataSourceStruct.getId(), DataSource.fromXML(dataSourceStruct.toXml()));
        }
    }

    public void resetDataSource(DataSourceStruct dataSourceStruct) throws SQLException, Exception {
        dataSourceMap.put(dataSourceStruct.getId(), DataSource.fromXML(dataSourceStruct.toXml()));
    }

    @Override
    public DataSource getDataSource(String name, DSRequest dsRequest) {
        return dataSourceMap.get(name);
    }
}
