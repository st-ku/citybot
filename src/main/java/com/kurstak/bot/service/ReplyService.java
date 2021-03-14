package com.kurstak.bot.service;

import com.kurstak.bot.model.City;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReplyService {
    private final CityService cityService;

    public ReplyService(CityService cityService) {
        this.cityService = cityService;
    }

    public String handle(String cityName) {
        Optional<City> city = cityService.findById(cityName);
        return city.isPresent() ? city.get().getCaption() : "City not found";
    }


}
