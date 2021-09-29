package services;

import com.google.gson.*;
import data.Human;
import exceptions.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class Utils {


    public static Gson gson(){
        return new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new JsonSerializer<ZonedDateTime>() {
            @Override
            public JsonElement serialize(ZonedDateTime src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject json = new JsonObject();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
                json.addProperty("birthday", src.format(formatter));
                return json;
            }
        }).create();
    }


    public static long getObjectIdFromPathVariable(HttpServletRequest request) throws ValidationException {
        try {
            StringBuffer requestUrl = request.getRequestURL();
            return (long) Integer.parseInt(requestUrl.substring(requestUrl.lastIndexOf("/") + 1));
        }catch (NumberFormatException e){
            System.out.println(1);
            throw new ValidationException("параметр id должен быть числом", 400);
        }
    }

    public static void writeJSONObjectToResponse(Object object, HttpServletResponse resp) throws ValidationException {
        try {
            String a = gson().toJson(object);
            PrintWriter writer = resp.getWriter();
            writer.write(a);
            resp.setStatus(200);
        }catch (IOException e){
            throw new ValidationException("страница недоступна", 404);
        }
    }

    public static void writeJSONObjectToResponseForHuman(Human human, HttpServletResponse resp) throws ValidationException {
        try {
            String a = gson().toJson(human);
            PrintWriter writer = resp.getWriter();
            writer.write(a);
            resp.setStatus(200);
        }catch (IOException e){
            throw new ValidationException("страница недоступна", 404);
        }
    }



    public static JSONObject getJSONFromBody(HttpServletRequest request) throws ValidationException {
        try {
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            return (JSONObject) new JSONParser().parse(body);
        }catch (ParseException | IOException e){
            throw new ValidationException("Ошибка сигнатуры запроса. Неверный формат JSON", 400);
        }
    }
}
