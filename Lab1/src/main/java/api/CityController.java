package api;

import data.*;
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
import java.util.Date;
import java.util.stream.Collectors;


@WebServlet("/city/*")
public class CityController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            JSONObject json = (JSONObject) new JSONParser().parse(body);
            City city = new City((String) json.get("name"), (Coordinates) DbApi.findById((Long) json.get("coordinates_id"), "Coordinates"), (Integer) json.get("area"), (Integer) json.get("population"), (int) json.get("meters_above_sea_level"),  (Date) json.get("establishment_date"), (Climate) DbApi.findById((Long) json.get("climate_id"), "Climate"), (Government) DbApi.findById((Long) json.get("government_id"), "Government"), (Human) DbApi.findById((Long) json.get("human_id"), "Human"));
            System.out.println(city);
            //save
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
            City city = (City) DbApi.findById(id, "City");
            Utils.writeJSONObjectToResponse(city, resp);
        }catch (ValidationException e){
            resp.sendError(400, e.getMessage());
        }

    }

}
