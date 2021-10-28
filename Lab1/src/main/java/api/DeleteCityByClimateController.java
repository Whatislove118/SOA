package api;

import data.Climate;
import exceptions.ErrorObject;
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
        resp.setContentType("application/json");
        try {
            CityService.deleteByClimate(climate);
        } catch (ValidationException e) {
            Utils.writeJSONErrorToResponse(resp, e.getMessage(), e.getStatus());
        }
        resp.setStatus(204);
    }
}
