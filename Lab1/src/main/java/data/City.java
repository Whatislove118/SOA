package data;

import exceptions.ValidationException;

import java.time.LocalDate;
import java.util.Date;

public class City {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer area; //Значение поля должно быть больше 0, Поле не может быть null
    private Integer population; //Значение поля должно быть больше 0, Поле не может быть null
    private int metersAboveSeaLevel;
    private java.util.Date establishmentDate;
    private Climate climate; //Поле может быть null
    private Government government; //Поле не может быть null
    private Human governor; //Поле не может быть null

    public String getName() {
        return name;
    }

    public void setName(String name) throws ValidationException {
        if (name == null || name.equals("")){
            throw new ValidationException("поле name не соблюдает условию валидации");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) throws ValidationException {
        if (coordinates == null){
            throw new ValidationException("поле coordinates не соблюдает условию валидации");
        }
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    private void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) throws ValidationException {
        if (area == null || area == 0){
            throw new ValidationException("поле are не соблюдает условию валидации");
        }
        this.area = area;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) throws ValidationException {
        if (population == null || population == 0){
            throw new ValidationException("поле population не соблюдает условию валидации");
        }
        this.population = population;
    }

    public int getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(int metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public Government getGovernment() {
        return government;
    }

    public void setGovernment(Government government) throws ValidationException {
        if (government == null){
            throw new ValidationException("поле government не соблюдает условию валидации");
        }
        this.government = government;
    }

    public Human getGovernor() {
        return governor;
    }

    public void setGovernor(Human governor) throws ValidationException {
        if (governor == null){
            throw new ValidationException("поле governor не соблюдает условию валидации");
        }
        this.governor = governor;
    }
}
