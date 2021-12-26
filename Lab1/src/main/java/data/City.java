package data;

import exceptions.ValidationArrayException;
import exceptions.ValidationException;
import org.json.simple.JSONObject;
import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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



    public City(JSONObject json) throws ValidationException, ValidationArrayException {
        ArrayList<ValidationException> err_list = new ArrayList<>();
        try {
            try {
                this.setName((String) json.get("name"));
            }catch (ValidationException e){
                System.out.println("name error");
                err_list.add(e);
            }
            try {
                Coordinates coordinates = new Coordinates((JSONObject) json.get("coordinates"));
                this.setCoordinates(coordinates);
            }catch (ValidationArrayException e){
                err_list.addAll(e.list);
            }
            try {
                this.setArea(json.get("area"));
            }catch (ValidationException e){
                err_list.add(e);
            }
            try {
                this.setPopulation(json.get("population"));
            }catch (ValidationException e){
                err_list.add(e);
            }
            try {
                this.setMetersAboveSeaLevel(json.get("metersAboveSeaLevel"));
            }catch (ValidationException e){
                err_list.add(e);
            }
            try {
                this.setEstablishmentDate((String) json.get("establishmentDate"));
            }catch (ValidationException e){
                err_list.add(e);
            }
            try {
                this.setClimate((String) json.get("climate"));
            }catch (ValidationException e){
                err_list.add(e);
            }
            try {
                this.setGovernment((String) json.get("government"));
            }catch (ValidationException e){
                err_list.add(e);
            }
            try {
                Human human = new Human((JSONObject) json.get("governor"));
                this.setGovernor(human);
            }catch (ValidationArrayException e){
                err_list.addAll(e.list);
            }
            if (err_list.size() != 0) {
                throw new ValidationArrayException(err_list, 403);
            }
        }catch (ClassCastException | NumberFormatException | NullPointerException e){
            e.printStackTrace();
            throw new ValidationException("Ошибка сигнатуры запроса сущности City. Типы переменных не соответсвтуеют заданным", 400);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) throws ValidationException {
        if (name == null || name.equals("")){
            throw new ValidationException("Параметр name должно быть представлено в запросе", 400);
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


    public void setArea(Object area) throws ValidationException{
        try {
            if (area == null){
                throw new ValidationException("Параметр area должен быть Integer", 400);
            }
            int new_area = (int) (long) area;
            if (new_area < 0) {
                throw new ValidationException("поле area не соблюдает условию валидации area >= 0", 400);
            }
            this.area = new_area;
        }catch (ClassCastException e){
            throw new ValidationException("Параметр area должен быть Integer", 400);
        }
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Object population) throws ValidationException {
        try {
            if (population == null){
                throw new ValidationException("Параметр population должен быть Integer", 400);
            }
            int new_population = (int) (long) population;
            if (new_population < 0) {
                throw new ValidationException("поле population не соблюдает условию валидации population >= 0", 400);
            }
            this.population = new_population;
        }catch (ClassCastException e){
            throw new ValidationException("Параметр population должен быть Integer", 400);
        }
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

    public void setMetersAboveSeaLevel(Object metersAboveSeaLevel) throws ValidationException {
        try {
            if (metersAboveSeaLevel == null){
                throw new ValidationException("Параметр metersAboveSeaLevel должен быть Integer", 400);
            }
            int new_smasl = (int) (long) metersAboveSeaLevel;
            this.metersAboveSeaLevel = new_smasl;
        }catch (ClassCastException e){
            throw new ValidationException("Параметр metersAboveSeaLevel должен быть Integer", 400);
        }
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
