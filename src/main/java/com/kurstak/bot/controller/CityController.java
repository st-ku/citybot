package com.kurstak.bot.controller;

import com.kurstak.bot.model.City;
import com.kurstak.bot.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/cities")
    public ResponseEntity addCity(@RequestBody City city) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cityService.save(city));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/cities")
    public ResponseEntity updateCity(@RequestBody City city) {
        try {
            cityService.update(city);
            return ResponseEntity.status(HttpStatus.OK).body(cityService.update(city));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/cities")
    public List<City> listCities() {
        return cityService.findAll();
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity getCity(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cityService.getCity(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity deleteCity(@PathVariable String id) {
        try {
            cityService.deleteCity(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}


