package com.soa.lab2.services;

import com.soa.lab2.beans.City;
import com.soa.lab2.beans.Coordinates;
import com.soa.lab2.beans.Human;
import com.soa.lab2.dto.CityDTO;
import com.soa.lab2.dto.CoordinatesDTO;
import com.soa.lab2.dto.HumanDTO;
import com.soa.lab2.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DTOConverter {

    @Autowired
    private City city;

    @Autowired
    private Human human;

    @Autowired
    private Coordinates coordinates;

    public ArrayList<CityDTO> convertListCityToListDTO(ArrayList<City> list) throws ValidationException {
        ArrayList<CityDTO> result = new ArrayList<>();
        for (City city: list){
            result.add(convertCityToDTO(city));
        }
        return result;

    }

    public CityDTO convertCityToDTO(City city) throws ValidationException {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setClimate(city.getClimate());
        cityDTO.setArea(city.getArea());
        cityDTO.setCreationDate(city.getCreationDate());
        cityDTO.setGovernment(city.getGovernment());
        cityDTO.setCoordinates(convertCoordinatesToDTO(city.getCoordinates()));
        cityDTO.setGovernor(convertHumanToDTO(city.getGovernor()));
        cityDTO.setPopulation(city.getPopulation());
        cityDTO.setEstablishmentDate(city.getEstablishmentDate());
        cityDTO.setPopulation(city.getPopulation());
        return cityDTO;
    }

    public City convertCityFromDTO(CityDTO cityDTO) throws ValidationException {
        city.setName(cityDTO.getName());
        city.setClimate(cityDTO.getClimate());
        city.setArea(cityDTO.getArea());
        city.setGovernment(cityDTO.getGovernment());
        city.setCoordinates(convertCoordinatesFromDTO(cityDTO.getCoordinates()));
        city.setGovernor(convertHumanFromDTO(cityDTO.getGovernor()));
        city.setPopulation(cityDTO.getPopulation());
        city.setEstablishmentDate(cityDTO.getEstablishmentDate());
        city.setPopulation(cityDTO.getPopulation());
        if (cityDTO.getId() != null) {
            city.setId(cityDTO.getId());
        }
        return city;
    }

    public HumanDTO convertHumanToDTO(Human human) {
        HumanDTO humanDTO = new HumanDTO();
        humanDTO.setId(human.getId());
        humanDTO.setHeight(human.getHeight());
        humanDTO.setBirthday(human.getBirthday());
        return humanDTO;
    }

    public Human convertHumanFromDTO(HumanDTO humanDTO) throws ValidationException {
        human.setBirthday(humanDTO.getBirthday());
        human.setHeight(humanDTO.getHeight());
        if (humanDTO.getId() != null) {
            human.setId(humanDTO.getId());
        }
        return human;
    }

    public CoordinatesDTO convertCoordinatesToDTO(Coordinates coordinates) {
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
        coordinatesDTO.setId(coordinates.getId());
        coordinatesDTO.setX(coordinates.getX());
        coordinatesDTO.setY(coordinates.getY());
        return coordinatesDTO;
    }

    public Coordinates convertCoordinatesFromDTO(CoordinatesDTO coordinatesDTO) {
        coordinates.setX(coordinatesDTO.getX());
        coordinates.setY(coordinatesDTO.getY());
        if (coordinatesDTO.getId() != null) {
            coordinates.setId(coordinatesDTO.getId());
        }
        return coordinates;
    }
}