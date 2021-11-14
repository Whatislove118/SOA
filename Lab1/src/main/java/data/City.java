package data;

import exceptions.ValidationException;
import org.json.simple.JSONObject;
import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    private String name; //Поле не может быть null, Строка не может быть пустой

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="coordinates_id")
    private Coordinates coordinates; //Поле не может быть null

    private final java.time.LocalDate creationDate = LocalDate.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private Integer area; //Значение поля должно быть больше 0, Поле не может быть null

    private Integer population; //Значение поля должно быть больше 0, Поле не может быть null

    private int metersAboveSeaLevel;

    private java.util.Date establishmentDate;

    @Enumerated(EnumType.STRING)
    private Climate climate; //Поле может быть null

    @Enumerated(EnumType.STRING)
    private Government government; //Поле не может быть null

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="human_id")
    private Human governor; //Поле не может быть null

    public City(String name, Coordinates coordinates, Integer area, Integer population, int metersAboveSeaLevel, Date establishmentDate, Climate climate, Government government, Human governor) throws ValidationException {
        this.setName(name);
        this.setCoordinates(coordinates);
        this.setArea(area);
        this.setPopulation(population);
        this.setMetersAboveSeaLevel(metersAboveSeaLevel);
        this.setGovernor(governor);
    }

    public City() {

    }

    public City(JSONObject json) throws ValidationException{
        try {
            this.setName((String) json.get("name"));
            Coordinates coordinates = new Coordinates((JSONObject) json.get("coordinates"));
            this.setCoordinates(coordinates);
            this.setArea((int) (long) json.get("area"));
            this.setPopulation((int) (long) json.get("population"));
            this.setMetersAboveSeaLevel((int) (long) json.get("metersAboveSeaLevel"));
            this.setEstablishmentDate((String) json.get("establishmentDate"));
            this.setClimate((String) json.get("climate"));
            this.setGovernment((String) json.get("government"));
            Human human = new Human((JSONObject) json.get("governor"));
            this.setGovernor(human);
        }catch (ClassCastException e){
            System.out.println(1);
            e.printStackTrace();
            throw new ValidationException("Ошибка сигнатуры запроса сущности City. Типы переменных не соответсвтуеют заданным", 400);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) throws ValidationException {
        if (name == null){
            throw new ValidationException("поле name не представлено в запросе", 400);
        } if(name.equals("")){
            throw new ValidationException("поле name не соблюдает условию валидации", 400);
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) throws ValidationException {
        if (coordinates == null){
            throw new ValidationException("поле coordinates не соблюдает условию валидации", 400);
        }
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) throws ValidationException {
        if (area == null || area < 0){
            throw new ValidationException("поле are не соблюдает условию валидации", 400);
        }
        this.area = area;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) throws ValidationException {
        if (population == null || population < 0){
            throw new ValidationException("поле population не соблюдает условию валидации", 400);
        }
        this.population = population;
    }

    public int getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMetersAboveSeaLevel(Integer metersAboveSeaLevel) throws ValidationException {
        if (metersAboveSeaLevel == null){
            throw new ValidationException("Поле metersAboveSeaLevel должно быть представлено в запросе", 400);
        }
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(String establishmentDate) throws ValidationException {
        if (establishmentDate == null){
            throw new ValidationException("поле establishmentDate должно быть представлено в запросе", 400);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            this.establishmentDate = formatter.parse(establishmentDate);
        }catch (ParseException e){
            throw new ValidationException("Неверный формат даты establishmentDate", 400);
        }
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(String climateName) throws ValidationException {
        if (climateName == null){
            throw new ValidationException("поле government не соблюдает условию валидации", 400);
        }
        for (Climate climate : Climate.values()){
            if (climate.name().equals(climateName)){
                this.climate = climate;
                return;
            }
        }
        throw new ValidationException("Поле climate не соответсвтует заданным значениям", 400);
    }

    public Government getGovernment() {
        return government;
    }

    public void setGovernment(String governmentName) throws ValidationException {
        if (governmentName == null){
            throw new ValidationException("поле government не соблюдает условию валидации", 400);
        }
        for (Government government : Government.values()){
            if (government.name().equals(governmentName)){
                this.government = government;
                return;
            }
        }
        throw new ValidationException("Поле government не соответсвтует заданным значениям", 400);
    }

    public Human getGovernor() {
        return governor;
    }

    public void setGovernor(Human governor) throws ValidationException {
        if (governor == null){
            throw new ValidationException("поле governor не соблюдает условию валидации", 400);
        }
        this.governor = governor;
    }
}
