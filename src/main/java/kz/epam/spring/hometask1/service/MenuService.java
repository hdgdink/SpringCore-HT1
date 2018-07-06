package kz.epam.spring.hometask1.service;

import kz.epam.spring.hometask1.domain.Event;

import java.time.LocalDateTime;

public interface MenuService {
    void showMainMenu();

    void showLogInMenu();

    void moveBackLogInMenuCheck(String email);

    void showSignInMenu();

    void showEventListMenu();

    void buyTicketMenu();

    void showVariantsMenu(Event event);

    void checkVariantsMenuInput(String next, Event event, LocalDateTime dateTime, Long seatNumber);

    void checkEventMenuInput(String next);

    void checkMainMenuInput(String s);
}
