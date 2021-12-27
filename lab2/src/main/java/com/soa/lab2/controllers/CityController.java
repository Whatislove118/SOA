package com.soa.lab2.controllers;

import com.soa.lab2.beans.City;
import com.soa.lab2.beans.Climate;
import com.soa.lab2.dto.CityDTO;
import com.soa.lab2.dto.Detail;
import com.soa.lab2.exceptions.ValidationException;
import com.soa.lab2.services.CityService;
import com.soa.lab2.services.DTOConverter;
import com.soa.lab2.services.Utils;
import com.soa.lab2.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/cities")
public class CityController {


    @Autowired
    private CityService service;

    @Autowired
    private DTOConverter converter;

    @Autowired
    private Utils utils;

    @Autowired
    private ValidationService validationService;


    @GetMapping("")
    // TODO FIX FILTER FCK
    public ResponseEntity<PageImpl> listCity(
            @RequestParam(required = false) HashMap<String, String> filter)  {

        try {
            int page = 0;
            int size = 3;
            if (filter.containsKey("page") && filter.containsKey("size")){
                page = Integer.parseInt(filter.get("page"));
                filter.remove("page");
                size = Integer.parseInt(filter.get("size"));
                filter.remove("size");
            }
            System.out.println(page);
            System.out.println(size);
//            page = (page < 1) ? 0 : page - 1;
//            size = (size < 1) ? 10 : size;
            Pageable pageable = PageRequest.of(page, size);
            ArrayList<City> cities = service.getAllCitys(filter);
            ArrayList<CityDTO> cityDTOS = converter.convertListCityToListDTO(cities);
            return new ResponseEntity(utils.generateResponse(cityDTOS, pageable), HttpStatus.OK);
        }catch (ValidationException e){
            Detail detail = new Detail(e.getErrMessage());
            return new ResponseEntity(detail, e.getErrStatus());
        }
    }

    @GetMapping("/{id}/")
    public ResponseEntity<City> get(@PathVariable Long id){
        try {
            City city = service.getCity(id);
            return new ResponseEntity(city, HttpStatus.OK);
        } catch (ValidationException e) {
            Detail detail = new Detail(e.getErrMessage());
            return new ResponseEntity(detail, e.getErrStatus());
        }

    }

    @PostMapping("/")
    public ResponseEntity<Detail> create( @RequestBody CityDTO cityDTO){
        try {
            validationService.validateCity(cityDTO, false);
            City city = converter.convertCityFromDTO(cityDTO);
            service.save(city);
            Detail detail = new Detail("created");
            return new ResponseEntity(detail, HttpStatus.CREATED);
        }catch (ValidationException e){
            Detail detail = new Detail(e.getErrMessage());
            return new ResponseEntity(detail, e.getErrStatus());
        }
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<Detail> delete(@PathVariable Long id){
        try{
            City city = service.getCity(id);
            service.deleteCity(city);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (ValidationException e){
            Detail detail = new Detail(e.getErrMessage());
            return new ResponseEntity(detail, e.getErrStatus());
        }
    }

    @PatchMapping("/{id}/")
    public ResponseEntity<CityDTO> update(@RequestBody CityDTO cityDTO, @PathVariable Long id){
        try{
            validationService.validateCity(cityDTO, true);
            service.update(cityDTO, id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (ValidationException e){
            Detail detail = new Detail(e.getErrMessage());
            return new ResponseEntity(detail, e.getErrStatus());
        }
    }

    @DeleteMapping("/delete/by")
    public ResponseEntity deleteByClimate(@RequestParam String climate){
        try {
            Climate climate_enum = Climate.valueOf(climate);
            service.deleteByClimate(climate_enum);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (IllegalArgumentException e){
            Detail detail = new Detail("Climate with that value doesn't exists");
            return new ResponseEntity(detail, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/by/government/higher")
    public ResponseEntity getByHigherGovernment(@RequestParam(name="government", required = true) String government){
        try {
            ArrayList<City> cities = service.getAllByGovernment(government, ">");
            ArrayList<CityDTO> cityDTOS = converter.convertListCityToListDTO(cities);
            return new ResponseEntity(cityDTOS, HttpStatus.OK);
        }catch (ValidationException e){
            Detail detail = new Detail(e.getErrMessage());
            return new ResponseEntity(detail, e.getErrStatus());
        }
    }

    @GetMapping("/by/government/lower")
    public ResponseEntity getByLowerGovernment(@RequestParam(name="government", required = true) String government){
        try {
            ArrayList<City> cities = service.getAllByGovernment(government, "<");
            ArrayList<CityDTO> cityDTOS = converter.convertListCityToListDTO(cities);
            return new ResponseEntity(cityDTOS, HttpStatus.OK);
        }catch (ValidationException e){
            Detail detail = new Detail(e.getErrMessage());
            return new ResponseEntity(detail, e.getErrStatus());
        }
    }



}
