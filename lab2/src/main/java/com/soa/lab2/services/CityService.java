package com.soa.lab2.services;

import com.soa.lab2.beans.*;
import com.soa.lab2.dto.CityDTO;
import com.soa.lab2.exceptions.CityNotFoundException;
import com.soa.lab2.exceptions.ValidationException;
import com.soa.lab2.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private Utils utils;



    public City save(City city) {
        return cityRepository.save(city);
    }


    public City getCity(long id) throws ValidationException {
        try {
            City city = cityRepository.findById(id).get();
            return city;
        }catch (NoSuchElementException e){
            throw new ValidationException("City with that id not found", HttpStatus.NOT_FOUND);
        }

    }

    public void update(CityDTO cityDTO, Long id) throws ValidationException {
        try {
            City city = cityRepository.findById(id).get();
            System.out.println(cityDTO.getName());
            if (cityDTO.getName() != null){

                city.setName(cityDTO.getName() );
            }
            if (cityDTO.getCoordinates() != null) {
                if (cityDTO.getCoordinates().getX() != null) {
                    city.getCoordinates().setX(cityDTO.getCoordinates().getX());
                }
                if (cityDTO.getCoordinates().getY() != null) {
                    city.getCoordinates().setY(cityDTO.getCoordinates().getY());
                }
            }
            if (cityDTO.getArea() != null){
                city.setArea(cityDTO.getArea());
            }
            if (cityDTO.getPopulation() != null){
                city.setPopulation(cityDTO.getPopulation());
            }
            if (cityDTO.getMetersAboveSeaLevel() != 0){
                city.setMetersAboveSeaLevel(cityDTO.getMetersAboveSeaLevel());
            }
            if (cityDTO.getEstablishmentDate() != null){
                city.setEstablishmentDate(cityDTO.getEstablishmentDate());
            }
            if (cityDTO.getClimate() != null){
                city.setClimate(cityDTO.getClimate());
            }
            if (cityDTO.getGovernment() != null){
                city.setGovernment(cityDTO.getGovernment());
            }
            if (cityDTO.getGovernor() != null) {
                if (cityDTO.getGovernor().getHeight() != null) {
                    city.getGovernor().setHeight(cityDTO.getGovernor().getHeight());
                }
            }
           cityRepository.save(city);
        }catch (NoSuchElementException e){
            throw new ValidationException("City with that id doesn't exists ", HttpStatus.BAD_REQUEST);
        }
    }

    public City editCity(City city) {
        return cityRepository.save(city);
    }


    public void deleteCity(City city) {
        cityRepository.delete(city);
    }

    public void deleteByClimate(Climate climate){
        cityRepository.deleteAllByClimate(climate);
    }

    public void deleteCity(int id) {
        cityRepository.deleteById((long) id);
    }

    public ArrayList<City> getAllByGovernment(String government, String mode) throws ValidationException {
        try {
            Government government_enum = Government.valueOf(government);
            ArrayList<City> result = null;
            if (mode.equals(">")) {
                result = (ArrayList<City>) cityRepository.getAllByHigherGovernment(government_enum);
            } else {
                result = (ArrayList<City>) cityRepository.getAllByLowerGovernment(government_enum);
            }
            return result;
        }catch (IllegalArgumentException e){
            throw new ValidationException("Government doesn't exists", HttpStatus.BAD_REQUEST);
        }
    }


    public ArrayList<City> getAllCitys(List<String> sort, HashMap<String, Object> filter) throws ValidationException {
        ArrayList<City> result = null;
        if (sort == null) {
            result = (ArrayList<City>) cityRepository.findAll();
            System.out.println("no sort");
        } else {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();
            for (String value : sort) {
                orders.add(new Sort.Order(Sort.Direction.ASC, value));
            }
            try {
                result = (ArrayList<City>) cityRepository.findAll(Sort.by(orders));
            } catch (PropertyReferenceException e) {
                throw new ValidationException("Wrong parameter for sort", HttpStatus.BAD_REQUEST);
            }
        }
        if (filter == null) {
            System.out.println("no f");
            return result;
        }
        result = filterByField(result, filter);
        return result;
    }


    public  ArrayList<City> filterByField(ArrayList<City> list, HashMap<String,Object> map){
        for (Map.Entry<String, Object> entry : map.entrySet()){
            String key = entry.getKey();
            String value = (String) entry.getValue().toString().replace("&", "");

            System.out.println("filtering " + key + ": " + value);
            try {
                switch (key) {
                    case "id":
                        list = (ArrayList<City>) list.stream().filter(city -> {
                            System.out.println(city.getId()); return city.getId() == Integer.parseInt(value);}).collect(Collectors.toList());
                        break;
                    case "name":
                        list = (ArrayList<City>) list.stream().filter(city -> city.getName().equals(value)).collect(Collectors.toList());
                        break;
                    case "coordinates_id":
                        list = (ArrayList<City>) list.stream().filter(city -> {
                            Coordinates coordinates = city.getCoordinates();
                            return coordinates.getId() == Long.parseLong(value);
                        }).collect(Collectors.toList());
                        break;
                    case "establishmentDate":
                        list = (ArrayList<City>) list.stream().filter(city -> {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-d", Locale.ENGLISH);
                            try {
                                Date old_date =  formatter.parse(city.getEstablishmentDate().toString());
                                Date new_date = formatter.parse(value);
                                return old_date.equals(new_date);
                            } catch (java.text.ParseException e) {
                                throw new IllegalArgumentException();
                            }
                        }).collect(Collectors.toList());
                        break;
                    case "coordinates_x":
                        list = (ArrayList<City>) list.stream().filter(city -> {
                            Coordinates coordinates = city.getCoordinates();
                            return coordinates.getX() == Long.parseLong(value);
                        }).collect(Collectors.toList());
                        break;
                    case "coordinates_y":
                        list = (ArrayList<City>) list.stream().filter(city -> {
                            Coordinates coordinates = city.getCoordinates();
                            return coordinates.getY() == Double.parseDouble(value);
                        }).collect(Collectors.toList());
                        break;
                    case "area":
                        list = (ArrayList<City>) list.stream().filter(city -> city.getArea() == Integer.parseInt(value)).collect(Collectors.toList());
                        break;
                    case "population":
                        list = (ArrayList<City>) list.stream().filter(city -> city.getPopulation() == Integer.parseInt(value)).collect(Collectors.toList());
                        break;
                    case "metersAboveSeaLevel":
                        list = (ArrayList<City>) list.stream().filter(city -> city.getMetersAboveSeaLevel() == Integer.parseInt(value)).collect(Collectors.toList());
                        break;
                    case "climate":
                        list = (ArrayList<City>) list.stream().filter(city -> city.getClimate() == Climate.valueOf(value)).collect(Collectors.toList());
                        break;
                    case "government":
                        list = (ArrayList<City>) list.stream().filter(city -> city.getGovernment() == Government.valueOf(value)).collect(Collectors.toList());
                        break;
                    case "governor_id":
                        list = (ArrayList<City>) list.stream().filter(city -> {
                            Human governor = city.getGovernor();
                            return governor.getId() == Long.parseLong(value);
                        }).collect(Collectors.toList());
                        break;
                    case "governor_height":
                        list = (ArrayList<City>) list.stream().filter(city -> {
                            Human governor = city.getGovernor();
                            return governor.getHeight() == Double.parseDouble(value);
                        }).collect(Collectors.toList());
                        break;
                    case "governor_birthday":
//                        System.out.println("hello ");
                        list = (ArrayList<City>) list.stream().filter(city -> {
                            Human governor = city.getGovernor();
                            String new_value = value.replace("T", " ");
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime respDate = LocalDateTime.parse(new_value, formatter);
                            return governor.getBirthday().equals(respDate);
                        }).collect(Collectors.toList());
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }catch (ClassCastException | IllegalArgumentException e){
                list.clear();
                return list;
            }
        }
        return list;
    }



}
