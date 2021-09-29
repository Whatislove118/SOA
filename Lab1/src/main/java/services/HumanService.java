package services;

import dao.CoordinatesDAO;
import dao.HumanDAO;
import data.Coordinates;
import data.Human;
import exceptions.ValidationException;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class HumanService {

    public static Human findById(Long id) throws ValidationException {
        if (id == null){
            throw new ValidationException("Поле id должно быть представлено в теле запроса", 400);
        }
        Human human = HumanDAO.findById(id);
        if (human == null){
            throw new ValidationException("Координата по заданному id не существует", 404);
        }
        return human;
    }

    public static void save(Human human) {
        HumanDAO.save(human);
    }

    public static void update(JSONObject json) throws ValidationException {
        try {
            Human human = findById((Long) json.get("id"));
            for (Field f : human.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (json.get(f.getName()) == null) {
                    continue;
                } else {
                    try {
                        if (f.getType() == ZonedDateTime.class){
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
                            ZonedDateTime value = ZonedDateTime.parse((CharSequence) json.get(f.getName()), formatter);
                            f.set(human, value);
                            continue;
                        }
                        f.set(human, f.getType().cast(json.get(f.getName())));
                    } catch (IllegalAccessException e) {
                        throw new ValidationException("Ошибка сигнатуры тела запроса.", 400);

                    }
                }
            }
            HumanDAO.update(human);
        }catch (ClassCastException e){
            throw new ValidationException("Ошибка сигнатуры тела запроса 1", 400);
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
        Human human = findById(id);
        HumanDAO.delete(human);
    }
}
