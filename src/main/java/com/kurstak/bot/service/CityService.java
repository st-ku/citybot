package com.kurstak.bot.service;

import com.kurstak.bot.model.City;
import com.kurstak.bot.repository.CityRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public Optional<City> findById(String city) {
        return cityRepository.findById(city);
    }

    public List<City> findAll() {
        return (List<City>) cityRepository.findAll();
    }

    public City save(City city) {
        if (cityRepository.existsById(city.getName())) throw new IllegalArgumentException(city.getName()+" already exists");
        else return cityRepository.save(city);
    }
    public City update(City city) {
        if (!cityRepository.existsById(city.getName())) throw new IllegalArgumentException(city.getName()+" not found");
        else return cityRepository.save(city);
    }

    public City getCity(String id) {
        return cityRepository.findById(id).get();
    }

    public void deleteCity(String id) {
        cityRepository.deleteById(id);
    }
}
