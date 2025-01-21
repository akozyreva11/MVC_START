package com.example.MVC_START.listiner;

import com.example.MVC_START.modelDTO.Recommendation;
import com.example.MVC_START.modelDTO.User;
import com.example.MVC_START.repositories.RecommendationsRepository;
import com.example.MVC_START.repositories.TelegramBotRepository;
import com.example.MVC_START.service.StarUserService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    @Autowired
    private TelegramBot telegramBot;
    private TelegramBotRepository telegramBotRepository;
    private StarUserService starUserService;

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    public TelegramBotUpdatesListener(TelegramBotRepository telegramBotRepository, RecommendationsRepository recommendationsRepository, StarUserService starUserservice) {
        this.telegramBotRepository = telegramBotRepository;
        this.starUserService = starUserService;
    }


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            String message = update.message().text();
            String[] splitMessage = message.split(" ");
            if (splitMessage[0].equals("/recommend")) {
                Collection<User> userList = telegramBotRepository.getUser(splitMessage[1]);
                userList.forEach(userDto -> {
                    Recommendation recommendation = starUserService.getRecommendation(userDto.getId());
                    String messageUser = ("Здравствуйте %s %s\n" +
                            "Новые продукты для вас: %s").formatted(userDto.getFirstName(),
                            userDto.getLastName(),
                            recommendation.getRecommendations());
                    SendMessage sendMessage = new SendMessage(update.message().chat().id(),
                            messageUser);
                    telegramBot.execute(sendMessage);
                });
            }

            SendMessage sendMessage;
            switch (update.message().text()) {
                case "/help":
                    sendMessage = new SendMessage(update.message().chat().id(),
                            getHelp());
                    telegramBot.execute(sendMessage);
                    break;
                case "/start":
                    String nameUser = update.message().from().firstName();
                    sendMessage = new SendMessage(update.message().chat().id(),
                            getHello(nameUser));
                    telegramBot.execute(sendMessage);
                    break;

            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private String getHelp() {
        return "Список продуктов для данного клиента\n" +
                "командау /recommend и свое имя.\n"
                + "Например /recommend IVAN.PETROV";
    }

    private String getHello(String firstName) {
        logger.info("Hello bot {}", firstName);
        return "Привет " + firstName + "!\n"
                + "Помогу тебе найти кредитные продукты,на выгодных условиях";
    }


}
