package services;

import dao.CityDAO;
import data.*;
import exceptions.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        System.out.println(json.toJSONString());
        try {
            City city = findById((Long) json.get("id"));
            if (json.get("name") != null){
//                if (json.get("name").equals("")){
//                    city.setName(null);
//                }
                city.setName((String) json.get("name"));
            }
            if (json.get("coordinate_x") != null){
//                if (json.get("coordinates_x").equals("")){
//                    city.getCoordinates().setX(null);
//                }
                System.out.println(json.get("coordinate_x"));
                city.getCoordinates().setX((Long)json.get("coordinate_x"));
            }
            if (json.get("coordinate_y") != null){
//                if (json.get("coordinates_y").equals("")){
//                    city.getCoordinates().setY(null);
//                }
               city.getCoordinates().setY((Double)json.get("coordinate_y"));
            }
            if (json.get("area") != null){
//                if (json.get("area").equals("")){
//                    city.setArea(null);
//                }
                city.setArea((int) (long) json.get("area"));
            }
            if (json.get("population") != null){
//                if (json.get("population").equals("")){
//                    city.setPopulation(null);
//                }
                city.setPopulation((int) (long) json.get("population"));
            }
            if (json.get("metersAboveSeaLevel") != null){
//                if (json.get("metersAboveSeaLevel").equals("")){
//                    city.setMetersAboveSeaLevel(null);
//                }
                city.setMetersAboveSeaLevel((int) (long) json.get("metersAboveSeaLevel"));
            }
            if (json.get("establishmentDate") != null){
//                if (json.get("establishmentDate").equals("")){
//                    city.setEstablishmentDate(null);
//                }
                city.setEstablishmentDate((String) json.get("establishmentDate"));
            }
            if (json.get("climate") != null){
                city.setClimate((String) json.get("climate"));
            }
            if (json.get("government") != null){
                city.setGovernment((String) json.get("government"));
            }
            if (json.get("governor_height") != null){
                city.getGovernor().setHeight((Double)json.get("governor_height"));
            }
            CityDAO.update(city);
        }catch (ClassCastException e){
            throw new ValidationException("Ошибка сигнатуры тела запроса", 400);
        }
    }

    public static void delete(Long id) throws ValidationException {
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

    public static ArrayList<City> findAll(ArrayList<String> params, HttpServletRequest req){
        if (params.size() == 0){
            return (ArrayList<City>) CityDAO.all();
        }
        HashMap<String, Object> filter_params = Utils.createHashMapFilterParams(params, req);
//        for (Map.Entry<String, Object> entry : filter_params.entrySet()){
//            System.out.println(entry.getKey());
//        }
       ArrayList<City> cities = (ArrayList<City>) CityDAO.all();
       return Utils.filterByField(cities, filter_params);
    }

}
