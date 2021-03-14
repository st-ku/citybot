package com.kurstak.bot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;

@Entity
public class City {
    @Id
    private String name;
    @Lob
    private String caption;

    public String getName() {
        return name;
    }

    public String getCaption() {
        return caption;
    }

    public void setName(String cityName) {
        this.name = cityName;
    }

    public void setCaption(String cityCaption) {
        this.caption = cityCaption;
    }

    public City(String name, String caption) {
        this.name = name;
        this.caption = caption;
    }

    public City() {
    }
}
