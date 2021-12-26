package com.soa.lab2.services;


import com.soa.lab2.beans.City;
import com.soa.lab2.dto.CityDTO;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class Utils {


    public PageImpl generateResponse(ArrayList<CityDTO> cities, Pageable pageable){
        int start = (int) pageable.getOffset();
        int end = (int) (Math.min((start + pageable.getPageSize()), cities.size()));
        System.out.println(cities.size());
        return new PageImpl(cities.subList(start, end), pageable, cities.size());

    }

    public ArrayList<City> sort(String value, ArrayList<City> list) throws IllegalArgumentException{
        System.out.println(value);
        switch (value){
            case "id":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getId)).collect(Collectors.toList());
                break;
            case "name":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getName)).collect(Collectors.toList());
                break;
            case "establishmentDate":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getEstablishmentDate)).collect(Collectors.toList());
                break;
            case "coordinates_x":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(city -> city.getCoordinates().getX())).collect(Collectors.toList());
                break;
            case "coordinates_y":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(city -> city.getCoordinates().getY())).collect(Collectors.toList());
                break;
            case "area":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getArea)).collect(Collectors.toList());
                break;
            case "population":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getPopulation)).collect(Collectors.toList());
                break;
            case "metersAboveSeaLevel":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getMetersAboveSeaLevel)).collect(Collectors.toList());
                break;
            case "climate":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getClimate)).collect(Collectors.toList());
                break;
            case "government":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getGovernment)).collect(Collectors.toList());
                break;
            case "creationDate":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(City::getCreationDate)).collect(Collectors.toList());
                break;
            case "governor_height":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(city -> city.getGovernor().getHeight())).collect(Collectors.toList());
                break;
            case "governor_birthday":
                list = (ArrayList<City>) list.stream().sorted(Comparator.comparing(city -> city.getGovernor().getBirthday())).collect(Collectors.toList());
                break;
            default:
                throw new IllegalArgumentException();
        }
        return list;
    }
}
