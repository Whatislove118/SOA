package com.lab2es.demo.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genocide")
public class MainController {


    @PostMapping("/kill/{id}/")
    public ResponseEntity kill(@PathVariable Long id){

    }


    @PostMapping("/deport/{idFrom}/{idTo}/")
    public ResponseEntity deport(@PathVariable Long idFrom, @PathVariable Long idTo){

    }


}
