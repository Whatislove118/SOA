package services;

import com.google.gson.*;
import data.*;
import exceptions.ErrorObject;
import exceptions.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {


//    public static Gson gson(){
//        return new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new JsonSerializer<ZonedDateTime>() {
//            @Override
//            public JsonElement serialize(ZonedDateTime src, Type typeOfSrc, JsonSerializationContext context) {
//                JsonObject json = new JsonObject();
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
//                json.addProperty("birthday", src.format(formatter));
//                return json;
//            }
//        }).create();
//    }

    public static ArrayList<String> fillCityParamsList(){
        ArrayList<String> result = new ArrayList<>();
        result.add("name");
        result.add("id");
        result.add("coordinates_x");
        result.add("coordinates_y");
        result.add("coordinates_id");

        result.add("area");
        result.add("metersAboveSeaLevel");
        result.add("establishmentDate");
        result.add("population");
        result.add("creationDate");
        result.add("climate");
        result.add("government");

        result.add("governor_id");
        result.add("governor_height");
        result.add("governor_birthday");
        result.add("sort");

        return result;
    }

    public static ArrayList<String> filterCityParams(HttpServletRequest req){
        ArrayList<String> params = fillCityParamsList();
        ArrayList<String> result = new ArrayList<>();
        for (String param: params){
            String predict_parameter = req.getParameter(param);
            if (predict_parameter == null){
                continue;
            }
            System.out.println(param);
            result.add(param);
        }
        return result;
    }



    public static Long getObjectIdFromPathVariable(HttpServletRequest request) throws ValidationException {
        try {
            StringBuffer requestUrl = request.getRequestURL();
            String res = requestUrl.substring(requestUrl.lastIndexOf("/") + 1);
            System.out.println(requestUrl.toString());
            System.out.println(requestUrl.toString().contains("?"));
            System.out.println(res);
            if (res.equals("") || res.equals("city")){
                return null;
            }
            return Long.parseLong(requestUrl.substring(requestUrl.lastIndexOf("/") + 1));
        }catch (NumberFormatException e){
           throw new ValidationException("параметр id должен быть числом", 400);
        }
    }

    public static HashMap<String, Object> createHashMapFilterParams(ArrayList<String> params, HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();
        for (String name: params){
            result.put(name, request.getParameter(name));
        }
        return result;
    }

    public static void writeJSONObjectToResponse(Object object, HttpServletResponse resp) {
        try {
            Gson gson = new Gson();
            String a = gson.toJson(object);
            PrintWriter writer = resp.getWriter();
            writer.write(a);
            resp.setStatus(200);
        }catch (IOException e){

        }
    }

    public static void writeJSONErrorToResponse(HttpServletResponse resp, String message, int status) {
        PrintWriter writer = null;
        try {
            Gson gson = new Gson();
            writer = resp.getWriter();
            ErrorObject err = new ErrorObject(message);
            String json = gson.toJson(err);
            writer.write(json);
            resp.setStatus(status);
        } catch (IOException e) {

        }

    }

    public static void writeJSONErrorObjectsToResponse(HttpServletResponse resp, ArrayList list) {
        PrintWriter writer = null;
        try {
            Gson gson = new Gson();
            writer = resp.getWriter();
            String json = gson.toJson(list);
            writer.write(json);
            resp.setStatus(403);
        } catch (IOException e) {

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

    public static ArrayList<City> filterByField(ArrayList<City> list, HashMap<String,Object> map){
        for (Map.Entry<String, Object> entry : map.entrySet()){
            String key = entry.getKey();
            String value = (String) entry.getValue().toString().replace("&", "");

            System.out.println("filtering " + key + ": " + value);
            try {
                switch (key) {
                    case "id":
                        list = (ArrayList<City>) list.stream().filter(city -> city.getId() == Integer.parseInt(value)).collect(Collectors.toList());
                    case "name":
                        list = (ArrayList<City>) list.stream().filter(city -> city.getName().equals(value)).collect(Collectors.toList());
                        break;
                    case "coordinates_id":
                        list = (ArrayList<City>) list.stream().filter(city -> {
                            Coordinates coordinates = city.getCoordinates();
                            return coordinates.getId() == Long.parseLong(value);
                        }).collect(Collectors.toList());
                        break;
                    case "establishmentDate":
                        list = (ArrayList<City>) list.stream().filter(city -> {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-d", Locale.ENGLISH);
                            try {
                                Date old_date =  formatter.parse(city.getEstablishmentDate().toString());
                                Date new_date = formatter.parse(value);
                                return old_date.equals(new_date);
                            } catch (java.text.ParseException e) {
                                throw new IllegalArgumentException();
                            }
                        }).collect(Collectors.toList());
                        break;
                    case "coordinates_x":
                        list = (ArrayList<City>) list.stream().filter(city -> {
                            Coordinates coordinates = city.getCoordinates();
                            return coordinates.getX() == Long.parseLong(value);
                        }).collect(Collectors.toList());
                        break;
                    case "coordinates_y":
                        list = (ArrayList<City>) list.stream().filter(city -> {
                            Coordinates coordinates = city.getCoordinates();
                            return coordinates.getY() == Double.parseDouble(value);
                        }).collect(Collectors.toList());
                        break;
                    case "area":
                        list = (ArrayList<City>) list.stream().filter(city -> city.getArea() == Integer.parseInt(value)).collect(Collectors.toList());
                        break;
                    case "population":
                        list = (ArrayList<City>) list.stream().filter(city -> city.getPopulation() == Integer.parseInt(value)).collect(Collectors.toList());
                        break;
                    case "metersAboveSeaLevel":
                        list = (ArrayList<City>) list.stream().filter(city -> city.getMetersAboveSeaLevel() == Integer.parseInt(value)).collect(Collectors.toList());
                        break;
                    case "climate":
                        list = (ArrayList<City>) list.stream().filter(city -> city.getClimate() == Climate.valueOf(value)).collect(Collectors.toList());
                        break;
                    case "government":
                        list = (ArrayList<City>) list.stream().filter(city -> city.getGovernment() == Government.valueOf(value)).collect(Collectors.toList());
                        break;
                    case "governor_id":
                        list = (ArrayList<City>) list.stream().filter(city -> {
                            Human governor = city.getGovernor();
                            return governor.getId() == Long.parseLong(value);
                        }).collect(Collectors.toList());
                        break;
                    case "governor_height":
                        list = (ArrayList<City>) list.stream().filter(city -> {
                            Human governor = city.getGovernor();
                            return governor.getHeight() == Double.parseDouble(value);
                        }).collect(Collectors.toList());
                        break;
                    case "governor_birthday":
//                        System.out.println("hello ");
                        list = (ArrayList<City>) list.stream().filter(city -> {
                            Human governor = city.getGovernor();
                            String new_value = value.replace("T", " ");
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime respDate = LocalDateTime.parse(new_value, formatter);
//                            return governor.getBirthday().equals(value);
                            return governor.getBirthday().equals(respDate);
                        }).collect(Collectors.toList());
                        break;
                    case "sort":
                        list = sortedByField(value, list);
                        continue;
                    default:
                        throw new IllegalArgumentException();
                }
            }catch (ClassCastException | IllegalArgumentException e){
                e.printStackTrace();
                list.clear();
                return list;
            }
        }
        return list;
    }

    public static ArrayList<City> sortedByField(String value, ArrayList<City> list) throws IllegalArgumentException{
            System.out.println("sorting by " + value);
            return directSorted(value, list);

    }

//    private static ArrayList<City> reverseSorted(String value, ArrayList<City> list) throws IllegalArgumentException{
//        switch (value){
//            case "id":
//                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getId).reversed()).collect(Collectors.toList());
//                break;
//            case "coordinates_x":
//                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(city -> city.getCoordinates().getX())).collect(Collectors.toList());
//                break;
//            case "coordinates_y":
//                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(city -> city.getCoordinates().getX())).collect(Collectors.toList());
//                break;
//            case "name":
//                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getName).reversed()).collect(Collectors.toList());
//                break;
//            case "area":
//                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getArea).reversed()).collect(Collectors.toList());
//                break;
//            case "population":
//                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getPopulation).reversed()).collect(Collectors.toList());
//                break;
//            case "metersAboveSeaLevel":
//                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getMetersAboveSeaLevel).reversed()).collect(Collectors.toList());
//                break;
//            case "climate":
//                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getClimate).reversed()).collect(Collectors.toList());
//                break;
//            case "government":
//                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getGovernment).reversed()).collect(Collectors.toList());
//                break;
//            case "creationDate":
//                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getCreationDate).reversed()).collect(Collectors.toList());
//                break;
//            default:
//                throw new IllegalArgumentException();
//        }
//        return list;
//    }

    private static ArrayList<City> directSorted(String value, ArrayList<City> list) throws IllegalArgumentException{
        System.out.println(value);
        switch (value){
            case "id":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getId)).collect(Collectors.toList());
                break;
            case "name":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getName)).collect(Collectors.toList());
                break;
            case "establishmentDate":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getEstablishmentDate)).collect(Collectors.toList());
                break;
            case "coordinates_x":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(city -> city.getCoordinates().getX())).collect(Collectors.toList());
                break;
            case "coordinates_y":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(city -> city.getCoordinates().getY())).collect(Collectors.toList());
                break;
            case "area":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getArea)).collect(Collectors.toList());
                break;
            case "population":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getPopulation)).collect(Collectors.toList());
                break;
            case "metersAboveSeaLevel":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getMetersAboveSeaLevel)).collect(Collectors.toList());
                break;
            case "climate":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getClimate)).collect(Collectors.toList());
                break;
            case "government":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getGovernment)).collect(Collectors.toList());
                break;
            case "creationDate":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getCreationDate)).collect(Collectors.toList());
                break;
            case "governor_height":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(city -> city.getGovernor().getHeight())).collect(Collectors.toList());
                break;
            case "governor_birthday":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(city -> city.getGovernor().getBirthday())).collect(Collectors.toList());
                break;
            default:
                throw new IllegalArgumentException();
        }
        return list;
    }

    public static Resp pagination(ArrayList<City> list, HttpServletRequest request) {
        if (request.getParameter("page") == null | request.getParameter("pageSize") == null) {
            return new Resp(list.size(), list);
        }

        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        if (page <= 0 & pageSize <= 0) {
            return new Resp(list.size(), list);
        }

        int fromIndex = (page - 1) * pageSize;
        if (list == null || list.size() <= fromIndex) {
            return new Resp(0, Collections.emptyList());
        }


        return new Resp(list.size(), list.subList(fromIndex, Math.min(fromIndex + pageSize, list.size())));
    }
//    public static List<City> pagination(ArrayList<City> list, HttpServletRequest request) {
//        if (request.getParameter("page") == null | request.getParameter("pageSize") == null) {
//            return list;
//        }
//
//        int page = Integer.parseInt(request.getParameter("page"));
//        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//
//        if (page <= 0 & pageSize <= 0) {
//            return list;
//        }
//
//        int fromIndex = (page - 1) * pageSize;
//        if (list == null || list.size() <= fromIndex) {
//            return Collections.emptyList();
//        }
//
//        return list.subList(fromIndex, Math.min(fromIndex + pageSize, list.size()));
//    }



}
