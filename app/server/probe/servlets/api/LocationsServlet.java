package probe.servlets.api;

import org.apache.log4j.Logger;
import probe.db.api.LocationsApi;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LocationsServlet extends HttpServlet {
    private Logger log = Logger.getLogger(LocationsServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parentIdString = request.getParameter("parentId");
        Integer parentId = (parentIdString != null && parentIdString.matches("^-?\\d+"))
            ? Integer.parseInt(parentIdString)
            : null;
        response.setContentType("application/json");
        try {
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(LocationsApi.fetch(parentId).toString());
        } catch (SQLException e) {
            log.error(e);
            response.setStatus(500);
            response.getWriter().write("Server error");
        }
    }
}
