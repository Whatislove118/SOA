package api;

import data.Climate;
import exceptions.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import services.CityService;
import services.Utils;

import java.io.IOException;

@WebServlet("/delete/coordinates")
public class DeleteCityByClimateController extends HttpServlet {


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String climate = req.getParameter("climate");
        try {
            CityService.deleteByClimate(climate);
        } catch (ValidationException e) {
            resp.sendError(e.getStatus(), e.getMessage());
        }
        resp.setStatus(204);
    }
}
