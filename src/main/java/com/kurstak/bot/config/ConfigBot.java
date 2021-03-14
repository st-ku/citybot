package com.kurstak.bot.config;

import com.kurstak.bot.model.City;
import com.kurstak.bot.service.CityService;
import com.kurstak.bot.telegram.bot.CityBot;
import com.kurstak.bot.telegram.bot.MessageServiceBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;

@Configuration
public class ConfigBot {
    @Autowired
    private MessageServiceBot messageServiceBot;
    @Autowired
    private CityService cityService;
    @Value("${bot.botToken}")
    private String token;
    @Value("${bot.userName}")
    private String username;

   @PostConstruct
    private void registerBot(){
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            CityBot cityBot = new CityBot(new DefaultBotOptions(),messageServiceBot);
            cityBot.setToken(token);
            cityBot.setUsername(username);
            telegramBotsApi.registerBot(cityBot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostConstruct
    private void populateDb(){
        cityService.save(new City("Москва","Не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить)))"));
    }

}
