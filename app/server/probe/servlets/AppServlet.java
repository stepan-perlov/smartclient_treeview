package probe.servlets;

import com.isomorphic.datasource.DataSource;
import org.apache.log4j.Logger;
import probe.common.Helpers;
import probe.db.ProbeDSGenerator;
import probe.db.api.DataSourcesApi;
import probe.db.api.LocationsApi;
import probe.db.structs.DataSourceStruct;
import probe.db.structs.Location;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AppServlet extends HttpServlet {
    private Logger log = Logger.getLogger(AppServlet.class);

    @Override
    public void init() throws ServletException {
        try {
            DataSource.addDynamicDSGenerator(new ProbeDSGenerator());
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setCharacterEncoding("UTF-8");

            List<DataSourceStruct> dataSourceList = DataSourcesApi.fetch();
            request.setAttribute("dataSourceList", Helpers.listToJson(dataSourceList));

            List<Location> locationsList = LocationsApi.fetch(null);
            request.setAttribute("locationsList", Helpers.listToJson(locationsList));

            request.getRequestDispatcher("/app.jsp").forward(request, response);
        } catch (SQLException e) {
            log.error(e);
            response.setStatus(500);
        }
    }
}
