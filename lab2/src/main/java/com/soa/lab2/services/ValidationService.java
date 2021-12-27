package com.soa.lab2.services;


import com.soa.lab2.dto.CityDTO;
import com.soa.lab2.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {



    public void validateCity(CityDTO cityDTO, boolean isUpdate) throws ValidationException {
        if(!isUpdate) {
            if (cityDTO.getName().equals("")|| cityDTO.getName() == null) {
                throw new ValidationException("Name mustn't be None", HttpStatus.BAD_REQUEST);
            }
            if (cityDTO.getArea() < 0 || cityDTO.getArea() == null) {
                throw new ValidationException("Area must be > 0", HttpStatus.BAD_REQUEST);
            }
            if (cityDTO.getCoordinates().getX() > 807 || cityDTO.getCoordinates().getX() == null) {
                throw new ValidationException("X must be > 807", HttpStatus.BAD_REQUEST);
            }
            if (cityDTO.getCoordinates().getY() < -776 || cityDTO.getCoordinates().getY() == null) {
                throw new ValidationException("Y must be < -776", HttpStatus.BAD_REQUEST);
            }
            if (cityDTO.getPopulation() < 0 || cityDTO.getPopulation() == null) {
                throw new ValidationException("Population must be > 0", HttpStatus.BAD_REQUEST);
            }
            if (cityDTO.getClimate().name().equals("") || cityDTO.getClimate() == null) {
                throw new ValidationException("Climate must be presented", HttpStatus.BAD_REQUEST);
            }
            if (cityDTO.getGovernment().name().equals("") || cityDTO.getGovernment() == null) {
                throw new ValidationException("Government must be presented", HttpStatus.BAD_REQUEST);
            }
            if (cityDTO.getGovernor().getHeight() < 0.0 || cityDTO.getGovernor() == null) {
                throw new ValidationException("Height must > 0", HttpStatus.BAD_REQUEST);
            }
        }else{
            if(cityDTO.getName() != null){
                if(cityDTO.getName().equals("")){
                    throw new ValidationException("Name mustn't be None", HttpStatus.BAD_REQUEST);
                }
            }
            if (cityDTO.getArea() != null) {
                if (cityDTO.getArea() < 0) {
                    throw new ValidationException("Area must be > 0", HttpStatus.BAD_REQUEST);
                }
            }
            if (cityDTO.getCoordinates() != null) {
                if (cityDTO.getCoordinates().getX() != null) {
                    if (cityDTO.getCoordinates().getX() > 807) {
                        throw new ValidationException("X must be > 807", HttpStatus.BAD_REQUEST);
                    }
                }
                if (cityDTO.getCoordinates().getY() != null) {
                    if (cityDTO.getCoordinates().getY() < -776) {
                        throw new ValidationException("Y must be < -776", HttpStatus.BAD_REQUEST);
                    }
                }
            }
            if (cityDTO.getPopulation() != null) {
                if(cityDTO.getPopulation() < 0) {
                    throw new ValidationException("Population must be > 0", HttpStatus.BAD_REQUEST);
                }
            }
            if (cityDTO.getClimate() != null) {
                if (cityDTO.getClimate().name().equals("")){
                    throw new ValidationException("Climate must be presented", HttpStatus.BAD_REQUEST);
                }
            }
            if (cityDTO.getGovernment() != null){
                if (cityDTO.getGovernment().name().equals("")){
                    throw new ValidationException("Government must be presented", HttpStatus.BAD_REQUEST);
                }
            }
            if (cityDTO.getGovernor() != null) {
                if (cityDTO.getGovernor().getHeight() != null) {
                    if (cityDTO.getGovernor().getHeight() < 0.0) {
                        throw new ValidationException("Height must > 0", HttpStatus.BAD_REQUEST);
                    }
                }
            }
        }


    }
}
