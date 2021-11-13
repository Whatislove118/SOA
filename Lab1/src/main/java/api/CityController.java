package api;

import data.City;
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
import java.util.ArrayList;


@WebServlet("/city/*")
public class CityController extends HttpServlet {

    private ArrayList<String> CITY_PARAMS;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            JSONObject json = Utils.getJSONFromBody(req);
            City coordinates = new City(json);
            CityService.save(coordinates);
            resp.setStatus(201);
        }catch (ValidationException e) {
            Utils.writeJSONErrorToResponse(resp, e.getMessage(), e.getStatus());
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try{
            JSONObject json = Utils.getJSONFromBody(req);
            CityService.update(json);
        }catch (ValidationException e){
            Utils.writeJSONErrorToResponse(resp, e.getMessage(), e.getStatus());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            Long id = (Long)Utils.getObjectIdFromPathVariable(req);
            CityService.delete(id);
            resp.setStatus(204);
        }catch (ValidationException e){
            Utils.writeJSONErrorToResponse(resp, e.getMessage(), e.getStatus());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            Long id = Utils.getObjectIdFromPathVariable(req);
            if (id == null){
                this.CITY_PARAMS = Utils.filterCityParams(req);
                ArrayList<City> cities = CityService.findAll(this.CITY_PARAMS, req);
                Utils.writeJSONObjectToResponse(Utils.pagination(cities, req), resp);
            }else {
                City city = CityService.findById(id);
                Utils.writeJSONObjectToResponse(city, resp);
            }
        }catch (ValidationException e){
            Utils.writeJSONErrorToResponse(resp, e.getMessage(), e.getStatus());
        }

    }
}
