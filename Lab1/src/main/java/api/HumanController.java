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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.DbApi;
import services.Utils;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@WebServlet("/human/*")
public class HumanController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            JSONObject json = (JSONObject) new JSONParser().parse(body);
            Human human = new Human((double) json.get("height"), (ZonedDateTime) json.get("birthday"));
            System.out.println(human);
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
            Human human = (Human) DbApi.findById(id, "Human");
            Utils.writeJSONObjectToResponse(human, resp);
        }catch (ValidationException e){
            resp.sendError(400, e.getMessage());
        }

    }
}
