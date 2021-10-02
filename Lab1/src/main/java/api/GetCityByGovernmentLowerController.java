package api;

import data.City;
import exceptions.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CityService;
import services.Utils;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/city/by/government/lower")
public class GetCityByGovernmentLowerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String government = req.getParameter("government");
        try {
            ArrayList<City> cities = CityService.getByGovernment(government, false);
            Utils.writeJSONObjectToResponse(cities, resp);
        } catch (ValidationException e) {
            resp.sendError(e.getStatus(), e.getMessage());
        }

    }

}
