package com.soa.lab2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.soa.lab2.beans.Climate;
import com.soa.lab2.beans.Coordinates;
import com.soa.lab2.beans.Government;
import com.soa.lab2.beans.Human;
import com.soa.lab2.exceptions.ValidationException;
import org.springframework.http.HttpStatus;


import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CityDTO implements Serializable {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private Long id;

    private String name;

    private CoordinatesDTO coordinates;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private String creationDate;


    private Integer area;


    private Integer population;

    private int metersAboveSeaLevel;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private String establishmentDate;


    private Climate climate;


    private Government government;

    private HumanDTO governor;

    public Date getCreationDate() throws ValidationException {
        try {
            return this.formatter.parse(creationDate);
        }catch (ParseException e){
            e.printStackTrace();
            throw new ValidationException("Wrong format of date. Should be - yyyy-MM-dd", HttpStatus.BAD_REQUEST);
        }

    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = this.formatter.format(creationDate);
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

    public void setName(String name) throws ValidationException {
        this.name = name;
    }

    public CoordinatesDTO getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesDTO coordinates) throws ValidationException {
        this.coordinates = coordinates;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public int getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(int metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public Date getEstablishmentDate() throws ValidationException {
        try {
            if (establishmentDate == null){
                return null;
            }
            return this.formatter.parse(establishmentDate);
        }catch (ParseException e){
            e.printStackTrace();
            throw new ValidationException("Wrong format of date. Should be - yyyy-MM-dd", HttpStatus.BAD_REQUEST);
        }
    }

    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = this.formatter.format(establishmentDate);
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

    public HumanDTO getGovernor() {
        return governor;
    }

    public void setGovernor(HumanDTO governor) {
        this.governor = governor;
    }
}
