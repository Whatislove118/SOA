package com.lab2es.demo.services;

import com.lab2es.demo.dto.CityDTO;
import com.lab2es.demo.dto.Detail;
import com.lab2es.demo.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;



@Service
public class RestService {

    final String url = "http://localhost:8080/cities/";
    int TIMEOUT = 4000;
    RestTemplate restTemplate = new RestTemplate();

    public RestService() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(TIMEOUT);
        requestFactory.setReadTimeout(TIMEOUT);
        restTemplate.setRequestFactory(requestFactory);
    }

    public CityDTO getCity(Long id) throws ValidationException {
        try {
            return restTemplate.getForObject(url + id + "/", CityDTO.class);
        }catch (HttpClientErrorException e){
            throw new ValidationException("City with that id doesn't exists", HttpStatus.BAD_REQUEST);
        }
    }


    public void deport(Long idFrom, Long idTo) throws ValidationException {
        CityDTO cityFrom = null;
        CityDTO cityTo = null;
        try {
            cityFrom = getCity(idFrom);
        }catch (ValidationException e){
            e.setErrMessage("City from deport doesn't exists");
            throw e;
        }
        try {
            cityTo = getCity(idTo);
        }catch (ValidationException e){
            e.setErrMessage("City to deport doesn't exists");
            throw e;
        }

        System.out.println(cityFrom.getArea());
        System.out.println(cityTo.getArea());
        cityTo.setPopulation(cityTo.getPopulation() + cityFrom.getPopulation());
        cityFrom.setPopulation(0);
        updateCity(cityTo);
        updateCity(cityFrom);
    }



    public void updateCity(CityDTO cityDTO){
        restTemplate.patchForObject(url+cityDTO.getId()+"/", cityDTO, CityDTO.class);
    }

}

