package kz.epam.spring.hometask1.runner;

import kz.epam.spring.hometask1.domain.*;
import kz.epam.spring.hometask1.service.impl.AuditoriumServiceImpl;
import kz.epam.spring.hometask1.service.impl.EventServiceImpl;
import kz.epam.spring.hometask1.service.impl.UserServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

class DataInitializer {
    private UserServiceImpl userService;
    private EventServiceImpl eventService;
    private AuditoriumServiceImpl auditoriumService;
    private final static String PATH_TO_PROPERTIES = "src/main/resources/auditoriums.properties";

    public DataInitializer(UserServiceImpl userService, EventServiceImpl eventService, AuditoriumServiceImpl auditoriumService) {
        this.userService = userService;
        this.eventService = eventService;
        this.auditoriumService = auditoriumService;
    }

    public DataInitializer() {
    }

    void initData() {
        initUser("Vovka", "Pupkin", "asd@asd.asd", "123", 12000.0, Role.CLIENT);
        initUser("Andrey", "Vass", "hdg", "456", 0.0, Role.ADMIN);
        initUser("a", "a", "a", "a", 12000.0, Role.CLIENT);
        initAuditoriums(1);
        initAuditoriums(2);
        initEvent("Abay's way", 400.0, EventRating.HIGH);
        initEvent("T-34", 300.0, EventRating.MID);
    }

    private void initUser(String firstName, String lastName, String email, String pass, Double balance, Role role) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPass(pass);
        user.setBalance(balance);
        user.setRole(role);
        userService.save(user);
    }

    private void initAuditoriums(Integer number) {
        Properties properties = new Properties();
        FileInputStream fileInputStream;

        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String name = properties.getProperty(number.toString() + ".Name");
        int rowSize = Integer.valueOf(properties.getProperty(number.toString() + ".RowSize"));
        Long seats = Long.valueOf(properties.getProperty(number.toString() + ".SeatsCount"));
        String[] list = (properties.getProperty(number.toString() + ".VIPSeats")).split(",");
        Set<Long> vipSeats = new HashSet();

        for (String seat : list) {
            vipSeats.add(Long.valueOf(seat));
        }

        Auditorium auditorium = new Auditorium(name, seats, rowSize, vipSeats);
        auditoriumService.addAuditorium(auditorium);
    }


    private void initEvent(String name, Double basePrice, EventRating rating) {
        Event event = new Event();
        event.setName(name);
        event.setBasePrice(basePrice);
        event.setRating(rating);
        LocalDateTime df1 = LocalDateTime.now().plusDays(ThreadLocalRandom.current().nextLong(0, 100));
        LocalDateTime df2 = LocalDateTime.now().plusDays(ThreadLocalRandom.current().nextLong(0, 100));
        event.addAirDateTime(df1);
        event.addAirDateTime(df2);
        event.assignAuditorium(df1, getAuditorium("Red"));
        event.assignAuditorium(df2, getAuditorium("Green"));
        eventService.save(event);
    }

    private Auditorium getAuditorium(String name) {
        return auditoriumService.getByName(name);
    }
}