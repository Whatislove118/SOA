package api;

import data.City;
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
import java.util.ArrayList;


@WebServlet("/city/*")
public class CityController extends HttpServlet {


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
            JSONObject json = Utils.getJSONFromBody(req);
            CityService.delete(json);
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
            City city = CityService.findById(id);
            Utils.writeJSONObjectToResponse(city, resp);
        }catch (ValidationException e){
            Utils.writeJSONErrorToResponse(resp, e.getMessage(), e.getStatus());
        }

    }
}
