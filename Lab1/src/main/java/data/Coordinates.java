package data;

import exceptions.ValidationException;

public class Coordinates {

    private long x; //Максимальное значение поля: 807
    private Double y; //Значение поля должно быть больше -776, Поле не может быть null


    public long getX() {
        return x;
    }

    public void setX(long x) throws ValidationException {
        if (x > 807){
            throw new ValidationException("поле x не соблюдает условию валидации");
        }
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) throws ValidationException {
        if (y < -776){
            throw new ValidationException("поле y не соблюдает условию валидации");
        }
        this.y = y;
    }
}
