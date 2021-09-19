package data;

import exceptions.ValidationException;
import services.IdGenerator;

import java.time.ZonedDateTime;

public class Human {
    private Long id;
    private double height; //Значение поля должно быть больше 0
    private java.time.ZonedDateTime birthday;

    public Human(double height, ZonedDateTime birthday) throws ValidationException {
        this.id = IdGenerator.generateId("Human");
        this.setHeight(height);
        this.setBirthday(birthday);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) throws ValidationException {
        if (height <= 0){
            throw new ValidationException("поле height не соблюдает условию валидации");
        }
        this.height = height;
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(ZonedDateTime birthday) {
        this.birthday = birthday;
    }
}
