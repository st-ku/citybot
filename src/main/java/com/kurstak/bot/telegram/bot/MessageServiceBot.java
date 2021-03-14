package com.kurstak.bot.telegram.bot;

import com.kurstak.bot.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class MessageServiceBot {
    @Autowired
    private final ReplyService replyService;

    public MessageServiceBot(ReplyService replyService) {
        this.replyService = replyService;
    }

    public SendMessage handleInputMessage(Message message) {
        switch (message.getText()) {
            case ("/start"):
                return createMessage(message, "Your joined the cityBot!");
            default:
                return createMessage(message, replyService.handle(message.getText()));
        }
    }

    private SendMessage createMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setText(text);
        return sendMessage;
    }

}
