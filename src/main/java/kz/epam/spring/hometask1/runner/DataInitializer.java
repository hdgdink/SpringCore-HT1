package kz.epam.spring.hometask1.runner;

import kz.epam.spring.hometask1.dao.impl.AuditoriumDao;
import kz.epam.spring.hometask1.domain.Auditorium;
import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.domain.EventRating;
import kz.epam.spring.hometask1.domain.User;
import kz.epam.spring.hometask1.service.EventService;
import kz.epam.spring.hometask1.service.UserService;
import kz.epam.spring.hometask1.service.impl.EventServiceImpl;
import kz.epam.spring.hometask1.service.impl.UserServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class DataInitializer {
    private UserService userService = new UserServiceImpl();
    private EventService eventService = new EventServiceImpl();

    void initData() {
        initUser("Vovka", "Pupkin", "asd@asd.asd", "123");
        initUser("Andrey", "Vass", "hdg", "456");
        initAuditoriums(1);
        initAuditoriums(2);
        initEvent("Leninskie kuchi", 300.0, EventRating.HIGH);
        initEvent("T-34", 300.0, EventRating.HIGH);
    }

    private void initUser(String firstName, String lastName, String email, String pass) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPass(pass);
        user.setBalance(12000.0);
        userService.save(user);
    }

    private void initAuditoriums(Integer number) {
        AuditoriumDao auditoriumDao = new AuditoriumDao();
        Properties properties = new Properties();

        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("auditoriums.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String name = properties.getProperty(number.toString() + ".Name");
        int rowSize = Integer.valueOf(properties.getProperty(number.toString() + ".RowCount"));
        Long seats = Long.valueOf(properties.getProperty(number.toString() + ".SeatsCount"));
        String[] list = (properties.getProperty(number.toString() + ".VIPSeats")).split(",");
        Set<Long> vipSeats = new HashSet();

        for (String seat : list) {
            vipSeats.add(Long.valueOf(seat));
        }

        Auditorium auditorium = new Auditorium(name, seats, rowSize,vipSeats);
        auditoriumDao.addObject(auditorium);
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
        AuditoriumDao auditoriumDao = new AuditoriumDao();
        return auditoriumDao.getByName(name);
    }
}
