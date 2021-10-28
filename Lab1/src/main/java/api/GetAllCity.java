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
        resp.setContentType("application/json");
        this.CITY_PARAMS = Utils.filterCityParams(req);
        ArrayList<City> cities = CityService.findAll(this.CITY_PARAMS, req);
        Utils.writeJSONObjectToResponse(Utils.pagination(cities, req), resp);


    }
}
