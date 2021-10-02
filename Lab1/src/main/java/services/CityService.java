package services;

import dao.CityDAO;
import data.*;
import exceptions.ValidationException;
import org.json.simple.JSONObject;


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
            if (json.get("name") != null){
                city.setName((String) json.get("name"));
            }
            if (json.get("coordinates") != null){
                Coordinates coordinates = new Coordinates((JSONObject) json.get("coordinates"));
                city.setCoordinates(coordinates);
            }
            if (json.get("area") != null){
                city.setArea((int) (long) json.get("name"));
            }
            if (json.get("population") != null){
                city.setPopulation((int) (long) json.get("population"));
            }
            if (json.get("metersAboveSeaLevel") != null){
                city.setMetersAboveSeaLevel((int) (long) json.get("metersAboveSeaLevel"));
            }
            if (json.get("establishmentDate") != null){
                city.setEstablishmentDate((String) json.get("establishmentDate"));
            }
            if (json.get("climate") != null){
                city.setClimate((String) json.get("climate"));
            }
            if (json.get("government") != null){
                city.setGovernment((String) json.get("government"));
            }
            if (json.get("governor") != null){
                Human human = new Human((JSONObject) json.get("governor"));
                city.setGovernor(human);
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


    public static int deleteByClimate(String climate) throws ValidationException{
        if (climate == null){
            throw new ValidationException("Параметр climate не представлен в запросе", 400);
        }
        try {
            Climate climate_obj = Climate.valueOf(climate);
            CityDAO.deleteByClimate(climate_obj);
        }catch (IllegalArgumentException e){
            throw new ValidationException("climate с таким значением не существует", 400);
        }
        return 0;
    }

    public static ArrayList<City> getByGovernment(String government, boolean isHigher) throws ValidationException {
        if (government == null){
            throw new ValidationException("Параметр climate не представлен в запросе", 400);
        }
        try {
            Government government_obj = Government.valueOf(government);
            return (ArrayList<City>) CityDAO.getByGovernment(government_obj, isHigher);
        }catch (IllegalArgumentException e){
            throw new ValidationException("climate с таким значением не существует", 400);
        }

    }

    public static ArrayList<City> findAll(){
        return (ArrayList<City>) CityDAO.all();
    }
}
