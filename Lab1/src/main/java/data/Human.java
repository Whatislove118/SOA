package data;

import exceptions.ValidationArrayException;
import exceptions.ValidationException;
import org.json.simple.JSONObject;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double height; //Значение поля должно быть больше 0

    private java.time.ZonedDateTime birthday;


    public Human(JSONObject json) throws ValidationException, ValidationArrayException {
        ArrayList<ValidationException> err_list = new ArrayList<>();
        try {
            this.setHeight(json.get("height"));
        }catch (ValidationException e){
            err_list.add(e);
        }
        try {
            this.setBirthday(json.get("birthday"));
        }catch (ValidationException e){
            err_list.add(e);
        }
        if(err_list.size() != 0){
            throw new ValidationArrayException(err_list, 400);
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

    public void setHeight(Object height) throws ValidationException {
        try {
            if (height == null){
                throw new ValidationException("поле height должно быть представлено в запросе", 400);
            }
            Double new_height = (Double) height;
            if (new_height <= 0) {
                throw new ValidationException("поле height не соблюдает условию height > 0", 400);
            }
            this.height = new_height;
        }catch (NumberFormatException e){
            throw new ValidationException("поле height должно быть числом", 400);
        }catch (ClassCastException e){
            throw new ValidationException("Параметр height должен быть Double", 400);
        }
    }

    public LocalDateTime getBirthday() {
        return birthday.toLocalDateTime();
    }

    public void setBirthday(Object birthday) throws ValidationException {
        try {
        if (birthday == null){
            throw new ValidationException("поле birthday должно быть представлено в запросе", 400);
        }
            LocalDateTime dt = LocalDateTime.parse((CharSequence) birthday);
            this.birthday = dt.atZone(ZoneId.systemDefault());
        }catch (DateTimeParseException e){
            throw new ValidationException("Неверный формат даты birthday", 400);
        }catch (ClassCastException e){
            throw new ValidationException("Неверный тип параметра birthday", 400);
        }
    }
}
