package services;

import com.google.gson.Gson;
import com.sun.deploy.net.HttpRequest;
import exceptions.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Utils {


    public static Long getObjectIdFromPathVariable(HttpServletRequest request) throws ValidationException {
        try {
            StringBuffer requestUrl = request.getRequestURL();
            return Long.parseLong(requestUrl.substring(requestUrl.lastIndexOf("/") + 1));
        }catch (NumberFormatException e){
            throw new ValidationException("параметр id должен быть числом");
        }
    }

    public static void writeJSONObjectToResponse(Object object, HttpServletResponse resp) throws ValidationException {
        try {
            String a = new Gson().toJson(object);
            PrintWriter writer = resp.getWriter();
            writer.write(a);
            resp.setStatus(200);
        }catch (IOException e){
            throw new ValidationException("страница недоступна");
        }
    }
}
