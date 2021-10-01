package api;

import data.Coordinates;
import data.Human;
import exceptions.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import services.CoordinatesService;
import services.HumanService;
import services.Utils;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/human/*")
public class HumanController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JSONObject json = Utils.getJSONFromBody(req);
            Human human = new Human(json);
            HumanService.save(human);
            resp.setStatus(201);
        }catch (ValidationException e) {
            resp.sendError(e.getStatus(), e.getMessage());
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            JSONObject json = Utils.getJSONFromBody(req);
            HumanService.update(json);
        }catch (ValidationException e){
            resp.sendError(e.getStatus(), e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JSONObject json = Utils.getJSONFromBody(req);
            HumanService.delete(json);
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
                ArrayList<Human> humans = HumanService.findAll();
                Utils.writeJSONObjectToResponse(humans, resp);
            }else {
                Human human = HumanService.findById(id);
                Utils.writeJSONObjectToResponse(human, resp);
            }
        }catch (ValidationException e){
            resp.sendError(e.getStatus(), e.getMessage());
        }

    }
}
