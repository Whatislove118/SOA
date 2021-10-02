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


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JSONObject json = Utils.getJSONFromBody(req);
            City coordinates = new City(json);
            CityService.save(coordinates);
            resp.setStatus(201);
        }catch (ValidationException e) {
            resp.sendError(e.getStatus(), e.getMessage());
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            JSONObject json = Utils.getJSONFromBody(req);
            CityService.update(json);
        }catch (ValidationException e){
            resp.sendError(e.getStatus(), e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JSONObject json = Utils.getJSONFromBody(req);
            CityService.delete(json);
            resp.setStatus(204);
        }catch (ValidationException e){
            resp.sendError(e.getStatus(), e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Utils.getObjectIdFromPathVariable(req);
            City city = CityService.findById(id);
            Utils.writeJSONObjectToResponse(city, resp);
            resp.setContentType("application/json");
        }catch (ValidationException e){
            resp.sendError(e.getStatus(), e.getMessage());
        }

    }
}
