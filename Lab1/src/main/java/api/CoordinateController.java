package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import data.Coordinates;
import exceptions.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import services.DbApi;
import services.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet("/coordinate/*")
public class CoordinateController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            JSONObject json = (JSONObject) new JSONParser().parse(body);
            Coordinates coordinates = new Coordinates((Long) json.get("x"), (Double) json.get("y"));
            System.out.println(coordinates);
            DbApi.createObject(coordinates, "Coordinates");
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (ParseException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ошибка сигнатуры запроса");
        } catch (ValidationException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Utils.getObjectIdFromPathVariable(req);
            DbApi.deleteById(id);
        }catch (ValidationException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
        super.doDelete(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Utils.getObjectIdFromPathVariable(req);
            Coordinates coordinates = (Coordinates) DbApi.findById(id, "Coordinates");
            Utils.writeJSONObjectToResponse(coordinates, resp);
        }catch (ValidationException e){
            resp.sendError(400, e.getMessage());
        }

    }
}

