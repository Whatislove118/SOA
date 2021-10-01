package services;

import dao.CityDAO;
import data.City;
import exceptions.ValidationException;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class CityService {
    
    
    public static City findById(Long id) throws ValidationException {
        if (id == null){
            throw new ValidationException("Поле id должно быть представлено в теле запроса", 400);
        }
        City city = CityDAO.findById(id);
        if (city == null){
            throw new ValidationException("Город по заданному id не существует", 404);
        }
        return city;
    }

    public static void save(City city) {
        CityDAO.save(city);
    }

    public static void update(JSONObject json) throws ValidationException {
        try {
            City city = findById((Long) json.get("id"));
            for (Field f : city.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                System.out.println(f.getName());
                if (json.get(f.getName()) == null) {
                    continue;
                } else {
                    try {
                        f.set(city, f.getType().cast(json.get(f.getName())));
                    } catch (IllegalAccessException e) {
                        throw new ValidationException("Ошибка сигнатуры тела запроса", 400);

                    }
                }
            }
            CityDAO.update(city);
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
        City city = findById(id);
        CityDAO.delete(city);
    }


    public static ArrayList<City> findAll(){
        return (ArrayList<City>) CityDAO.all();
    }
}
