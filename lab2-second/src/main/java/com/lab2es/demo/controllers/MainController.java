package com.lab2es.demo.controllers;


import com.lab2es.demo.dto.CityDTO;
import com.lab2es.demo.dto.Detail;
import com.lab2es.demo.exceptions.ValidationException;
import com.lab2es.demo.services.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/genocide")
public class MainController {

    @Autowired
    private RestService restService;

    @PostMapping("/kill/{id}/")
    public ResponseEntity kill(@PathVariable Long id){
        try {
            CityDTO cityDTO = restService.getCity(id);
            cityDTO.setPopulation(0);
            restService.updateCity(cityDTO);
            Detail detail = new Detail("killed");
            return new ResponseEntity(detail, HttpStatus.OK);
        }catch (ValidationException e){
            Detail detail = new Detail(e.getErrMessage());
            return new ResponseEntity(detail, e.getErrStatus());
        }
    }


    @PostMapping("/deport/{idFrom}/{idTo}/")
    public ResponseEntity deport(@PathVariable Long idFrom, @PathVariable Long idTo){
        try {
            restService.deport(idFrom, idTo);
            Detail detail = new Detail("deported");
            return new ResponseEntity(detail,HttpStatus.OK);
        } catch (ValidationException e) {
            Detail detail = new Detail(e.getErrMessage());
            return new ResponseEntity(detail, e.getErrStatus());
        }
    }


}
