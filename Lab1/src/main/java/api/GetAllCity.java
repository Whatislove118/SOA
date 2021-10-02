package api;

import data.City;
import data.Human;
import exceptions.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CityService;
import services.HumanService;
import services.Utils;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/all/city")
public class GetAllCity extends HttpServlet {

    private ArrayList<String> CITY_PARAMS;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.CITY_PARAMS = Utils.filterCityParams(req);
        ArrayList<City> cities = CityService.findAll(this.CITY_PARAMS, req);
        try {
            Utils.writeJSONObjectToResponse(cities, resp);
            resp.setContentType("application/json");
        } catch (ValidationException e) {
            resp.sendError(e.getStatus(), e.getMessage());
        }
    }
}
