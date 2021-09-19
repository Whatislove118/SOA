package data;

import exceptions.ValidationException;
import services.IdGenerator;

public class Coordinates {
    private Long id;
    private long x; //Максимальное значение поля: 807
    private Double y; //Значение поля должно быть больше -776, Поле не может быть null


    public Coordinates(long x, Double y) throws ValidationException {
        this.id = IdGenerator.generateId("Coordinates");
        this.setX(x);
        this.setY(y);

    }

    public Long getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
