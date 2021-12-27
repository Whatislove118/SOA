package com.soa.lab2.controllers;

import com.soa.lab2.beans.Climate;
import com.soa.lab2.beans.Coordinates;
import com.soa.lab2.beans.Government;
import com.soa.lab2.beans.Human;
import java.util.Date;

public class FilterParams {

    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    private String name; //Поле не может быть null, Строка не может быть пустой

    private Long coordinates_id; //Поле не может быть null

    private Long coordinates_x; //Поле не может быть null, Значение этого поля должно генерироваться автоматически


    private Double coordinates_y; //Значение поля должно быть больше 0, Поле не может быть null


    private Integer area; //Значение поля должно быть больше 0, Поле не может быть null

    private String  establishmentDate; //Значение поля должно быть больше 0, Поле не может быть null

    private Integer population;

    private Integer metersAboveSeaLevel;

    private Climate climate; //Поле может быть null

    private Government government; //Поле не может быть null

    private Long governor_id; //Поле не может быть null

    private String birthday; //Поле не может быть null

    private Double governor_height; //Поле не может быть null


    public FilterParams() {
    }


    public FilterParams(Long id, String name, Long coordinates_id, Long coordinates_x, Double coordinates_y, Integer area, String establishmentDate, Integer population, Integer metersAboveSeaLevel, Climate climate, Government government, Long governor_id, String birthday, Double governor_height) {
        this.id = id;
        this.name = name;
        this.coordinates_id = coordinates_id;
        this.coordinates_x = coordinates_x;
        this.coordinates_y = coordinates_y;
        this.area = area;
        this.establishmentDate = establishmentDate;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.climate = climate;
        this.government = government;
        this.governor_id = governor_id;
        this.birthday = birthday;
        this.governor_height = governor_height;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCoordinates_id() {
        return coordinates_id;
    }

    public void setCoordinates_id(Long coordinates_id) {
        this.coordinates_id = coordinates_id;
    }

    public Long getCoordinates_x() {
        return coordinates_x;
    }

    public void setCoordinates_x(Long coordinates_x) {
        this.coordinates_x = coordinates_x;
    }

    public Double getCoordinates_y() {
        return coordinates_y;
    }

    public void setCoordinates_y(Double coordinates_y) {
        this.coordinates_y = coordinates_y;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(String establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(Integer metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
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

    public void setGovernment(Government government) {
        this.government = government;
    }

    public Long getGovernor_id() {
        return governor_id;
    }

    public void setGovernor_id(Long governor_id) {
        this.governor_id = governor_id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Double getGovernor_height() {
        return governor_height;
    }

    public void setGovernor_height(Double governor_height) {
        this.governor_height = governor_height;
    }

    @Override
    public String toString() {
        return "FilterParams{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates_id=" + coordinates_id +
                ", coordinates_x=" + coordinates_x +
                ", coordinates_y=" + coordinates_y +
                ", area=" + area +
                ", establishmentDate='" + establishmentDate + '\'' +
                ", population=" + population +
                ", metersAboveSeaLevel=" + metersAboveSeaLevel +
                ", climate=" + climate +
                ", government=" + government +
                ", governor_id=" + governor_id +
                ", birthday='" + birthday + '\'' +
                ", governor_height=" + governor_height +
                '}';
    }
}
