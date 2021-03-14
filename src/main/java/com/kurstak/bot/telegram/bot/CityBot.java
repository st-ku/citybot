package com.kurstak.bot.telegram.bot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CityBot extends TelegramLongPollingBot {

    private final MessageServiceBot messageServiceBot;
    private String token;
    private String username;

    public CityBot(DefaultBotOptions botOptions, MessageServiceBot messageServiceBot) {
        super(botOptions);
        this.messageServiceBot = messageServiceBot;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                execute(messageServiceBot.handleInputMessage(message));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
