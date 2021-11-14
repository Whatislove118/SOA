package data;

import exceptions.ValidationException;
import org.json.simple.JSONObject;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long x; //Максимальное значение поля: 807

    private Double y; //Значение поля должно быть больше -776, Поле не может быть null


    public Coordinates(Long x, Double y) throws ValidationException {
        if (y == null){
            this.setX(x);
        }else if (x == null){
            this.setY(y);
        }else {
            this.setX(x);
            this.setY(y);
        }

    }

    public Coordinates(JSONObject json) throws ValidationException{
        try {
            this.setX(Long.parseLong((String) json.get("x")));
            this.setY(Double.parseDouble((String)json.get("y")));
        }catch (ClassCastException e){
            throw new ValidationException("Ошибка сигнатуры запроса сущности Coordinates. Типы переменных не соответсвтуеют заданным", 400);
        }
    }


    public Coordinates() {
    }

    public long getX() {
        return x;
    }

    public void setX(Long x) throws ValidationException {
        if (x == null){
            throw new ValidationException("поле x должно быть представлено в запросе", 400);
        }
        if (x > 807){
            throw new ValidationException("поле x не соблюдает условию валидации", 400);
        }
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) throws ValidationException {
        if (y == null){
            throw new ValidationException("поле y должно быть представлено в запросе", 400);
        }
        if (y < -776){
            throw new ValidationException("поле y не соблюдает условию валидации", 400);
        }
        this.y = y;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
