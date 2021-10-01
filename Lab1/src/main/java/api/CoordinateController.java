package api;

import data.City;
import data.Coordinates;
import exceptions.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import services.CityService;
import services.CoordinatesService;
import services.Utils;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/coordinate/*")
public class CoordinateController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JSONObject json = Utils.getJSONFromBody(req);
            Coordinates coordinates = new Coordinates(json);
            CoordinatesService.save(coordinates);
            resp.setStatus(201);
        }catch (ValidationException e) {
            resp.sendError(e.getStatus(), e.getMessage());
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            JSONObject json = Utils.getJSONFromBody(req);
            CoordinatesService.update(json);
        }catch (ValidationException e){
            resp.sendError(e.getStatus(), e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JSONObject json = Utils.getJSONFromBody(req);
            CoordinatesService.delete(json);
            resp.setStatus(204);
        }catch (ValidationException e){
            resp.sendError(e.getStatus(), e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Utils.getObjectIdFromPathVariable(req);
            if (id == null){
                ArrayList<Coordinates> coordinates = CoordinatesService.findAll();
                Utils.writeJSONObjectToResponse(coordinates, resp);
            }else {
                Coordinates coordinates = CoordinatesService.findById(id);
                Utils.writeJSONObjectToResponse(coordinates, resp);
            }
        }catch (ValidationException e){
            resp.sendError(e.getStatus(), e.getMessage());
        }

    }
}

