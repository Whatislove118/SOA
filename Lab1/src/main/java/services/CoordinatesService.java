package services;

import dao.CityDAO;
import dao.CoordinatesDAO;
import data.City;
import data.Coordinates;
import exceptions.ValidationException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class CoordinatesService {

    public static Coordinates findById(Long id) throws ValidationException {
        if (id == null){
            throw new ValidationException("Поле id должно быть представлено в теле запроса", 400);
        }
        Coordinates coordinates = CoordinatesDAO.findById(id);
        if (coordinates == null){
            throw new ValidationException("Координата по заданному id не существует", 404);
        }
        return coordinates;
    }

    public static void save(Coordinates coordinates) {
        CoordinatesDAO.save(coordinates);
    }

    public static void update(JSONObject json) throws ValidationException {
       try {
           Coordinates coordinates = findById((Long) json.get("id"));
           for (Field f : coordinates.getClass().getDeclaredFields()) {
               f.setAccessible(true);
               System.out.println(f.getName());
               if (json.get(f.getName()) == null) {
                   continue;
               } else {
                   try {
                       f.set(coordinates, f.getType().cast(json.get(f.getName())));
                   } catch (IllegalAccessException e) {
                       throw new ValidationException("Ошибка сигнатуры тела запроса", 400);

                   }
               }
           }
           CoordinatesDAO.update(coordinates);
       }catch (ClassCastException e){
           throw new ValidationException("Ошибка сигнатуры тела запроса", 400);
       }
    }

    public static void delete(JSONObject json) throws ValidationException {
        Long id = null;
        try {
            id = (Long) json.get("id");
        }catch (ClassCastException e){
            throw new ValidationException("Ошибка сигнатуры метода", 400);
        }
        if (id == null){
            throw new ValidationException("поле id должно быть представлено в теле запроса", 400);
        }
        Coordinates coordinates = findById(id);
        CoordinatesDAO.delete(coordinates);
    }

    public static ArrayList<Coordinates> findAll(){
        return (ArrayList<Coordinates>) CoordinatesDAO.all();
    }

}
