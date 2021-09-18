package data;

import exceptions.ValidationException;

import java.time.ZonedDateTime;

public class Human {
    private double height; //Значение поля должно быть больше 0
    private java.time.ZonedDateTime birthday;

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
