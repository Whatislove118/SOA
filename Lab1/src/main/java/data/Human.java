package data;

import exceptions.ValidationException;
import services.IdGenerator;

import java.time.ZonedDateTime;

public class Human {
    private Long id;
    private double height; //Значение поля должно быть больше 0
    private java.time.ZonedDateTime birthday;

    public Human(String height, ZonedDateTime birthday) throws ValidationException {
        //this.id = IdGenerator.generateId("Human");
        this.setHeight(height);
        this.setBirthday(birthday);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(String h) throws ValidationException {
        try {
            double height = Double.parseDouble(h);
            if (height <= 0) {
                throw new ValidationException("поле height не соблюдает условию валидации");
            }
            this.height = height;
        }catch (NumberFormatException e){
            throw new ValidationException("поле height должно быть числом");
        }
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(ZonedDateTime birthday) {
        this.birthday = birthday;
    }
}
