package by.example.bot;

import by.example.bot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {
    @Autowired
    private CityService cityService;

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String text = update.getMessage().getText();
            User user = update.getMessage().getFrom();

            if ("/start".equals(text)) {
                sendMsg(message, "Привет, " + user.getFirstName() + "! Я CityInfoBot. Пожалуйста, напишите название интересующего вас города");
            } else {
                sendMsg(message, cityService.getCityInfo(text));
            }
        }

    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMes = new SendMessage();
        sendMes.enableMarkdown(true);
        sendMes.setChatId(message.getChatId().toString());
        sendMes.setReplyToMessageId(message.getMessageId());
        sendMes.setText(text);
        try {
            execute(sendMes);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
