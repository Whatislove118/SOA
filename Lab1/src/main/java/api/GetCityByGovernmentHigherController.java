package api;

import data.City;
import exceptions.ErrorObject;
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

@WebServlet("/city/by/government/higher")
public class GetCityByGovernmentHigherController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String government = req.getParameter("government");
        try {
            ArrayList<City> cities = CityService.getByGovernment(government, true);
            Utils.writeJSONObjectToResponse(cities, resp);
        } catch (ValidationException e) {
            Utils.writeJSONErrorToResponse(resp, e.getMessage(), e.getStatus());
        }
    }
}
