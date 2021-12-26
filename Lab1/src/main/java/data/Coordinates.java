package data;

import exceptions.ValidationArrayException;
import exceptions.ValidationException;
import org.json.simple.JSONObject;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;


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

    public Coordinates(JSONObject json) throws ValidationException, ValidationArrayException {
        ArrayList<ValidationException> err_list = new ArrayList<>();
        try {
            this.setX(json.get("x"));
        }catch (ValidationException e){
            err_list.add(e);
        }
        try {
            this.setY(json.get("y"));
        }catch (ValidationException e){
            err_list.add(e);
        }

        if (err_list.size() != 0){
            throw new ValidationArrayException(err_list, 403);
        }
    }


    public Coordinates() {
    }

    public long getX() {
        return x;
    }


    public void setX(Object x) throws ValidationException {
        try {
            if (x == null) {
                throw new ValidationException("Параметр coordinates_x должен быть Long", 400);
            }
            long new_x = (Long) x;
            if (new_x > 807) {
                throw new ValidationException("поле coordinates_x не соблюдает условию валидации x < 807", 400);
            }
            this.x = new_x;
        }catch (NumberFormatException | ClassCastException e){
            e.printStackTrace();
            throw new ValidationException("Параметр coordinates_x должен быть Long", 400);
        }

    }


    public void setY(Object y) throws ValidationException {
        try {
            if (y == null){
                throw new ValidationException("Параметр coordinates_y должен быть Double", 400);
            }
            double new_y = (Double) y;
            if (new_y < -776){
                throw new ValidationException("поле coordinates_y не соблюдает условию валидации y > -776", 400);
            }
            this.y = new_y;
        }catch (NumberFormatException | ClassCastException e){
            throw new ValidationException("Параметр coordinates_y должен быть Double", 400);
        }

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
