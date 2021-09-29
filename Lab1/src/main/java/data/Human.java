package data;

import exceptions.ValidationException;
import org.json.simple.JSONObject;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

@Entity
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double height; //Значение поля должно быть больше 0

    private java.time.ZonedDateTime birthday;

    public Human(Double height, ZonedDateTime birthday) throws ValidationException {
        this.setHeight(height);
        //this.setBirthday((Date) birthday);
    }

    public Human(JSONObject json) throws ValidationException{
        try {
            this.setHeight((Double) json.get("height"));
            this.setBirthday((String) json.get("birthday"));
        }catch (ClassCastException e){
            throw new ValidationException("Ошибка сигнатуры запроса. Типы переменных не соответсвтуеют заданным", 400);
        }
    }


    public Human() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(Double height) throws ValidationException {
        try {
            if (height == null){
                throw new ValidationException("поле height должно быть представлено в запросе", 400);
            }
            if (height <= 0) {
                throw new ValidationException("поле height не соблюдает условию валидации", 400);
            }
            this.height = height;
        }catch (NumberFormatException e){
            throw new ValidationException("поле height должно быть числом", 400);
        }
    }

    public String getBirthday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        return birthday.format(formatter);
    }

    public void setBirthday(String birthday) throws ValidationException {
        if (birthday == null){
            throw new ValidationException("поле birthday должно быть представлено в запросе", 400);
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
            this.birthday = ZonedDateTime.parse(birthday, formatter);
        }catch (DateTimeParseException e){
            throw new ValidationException("Неверный формат даты", 400);
        }
    }
}
