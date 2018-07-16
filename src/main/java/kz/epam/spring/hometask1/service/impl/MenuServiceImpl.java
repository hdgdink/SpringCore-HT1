package kz.epam.spring.hometask1.service.impl;

import kz.epam.spring.hometask1.action.event.EventAction;
import kz.epam.spring.hometask1.action.user.LogInAction;
import kz.epam.spring.hometask1.action.user.SignAction;
import kz.epam.spring.hometask1.action.user.admin.AdminAction;
import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Scanner;

@Service
@ComponentScan
public class MenuServiceImpl {
    private Scanner scanner = new Scanner(System.in);
    @Autowired
    private SignAction signAction;
    @Autowired
    private LogInAction logInAction;
    @Autowired
    private EventAction eventAction;
    @Autowired
    private AdminAction adminAction;

    public static User loggedUser;
    private boolean isChosen;
    private boolean isLogged;
    private boolean isAdmin;

    public MenuServiceImpl() {
    }

    public MenuServiceImpl(SignAction signAction, LogInAction logInAction,
                           EventAction eventAction, AdminAction adminAction) {
        this.signAction = signAction;
        this.logInAction = logInAction;
        this.eventAction = eventAction;
        this.adminAction = adminAction;
    }

    public void showMainMenu() {
        if (!isLogged)
            System.out.println("-------------Welcome to Cinema----------------");
        else System.out.println("Welcome " + loggedUser.getFirstName());

        if (isAdmin) {
            showAdminMenu();
        }

        System.out.println("----------------------------------------------");
        System.out.println("------------------Main Menu-------------------");
        System.out.println("---------------(Choose action)----------------");

        if (!isLogged) {
            System.out.println("--a.LogIn");
            System.out.println("--b.SignIn");
        }

        System.out.println("--c.Show list of event");

        if (isLogged)
            System.out.println("--d.Log out");

        System.out.println("--e.Exit");

        while (!isChosen) {
            checkMainMenuInput(scanner.next());
        }
    }

    private void showAdminMenu() {
        adminAction.showAdminMenu();
        isLogged = false;
        isAdmin = false;
        loggedUser = null;
        showMainMenu();
    }

    private void showLogInMenu() {
        String email;
        String pass;

        while (!isLogged) {
            System.out.println("----------------LogIn----------------");
            System.out.println("-------Enter your login(email)-------");
            System.out.println("--(ot type 'q' to back to main menu)--");
            email = scanner.next();

            if (!logInAction.checkEmail(email)) {
                moveBackLogInMenuCheck(email);
            }

            System.out.println("---------Enter your password---------");
            pass = scanner.next();

            loggedUser = logInAction.logIn(email, pass);

            if (loggedUser.getEmail() != null) {
                isLogged = true;
                isAdmin = logInAction.checkIsAdmin(loggedUser);
                showMainMenu();
            } else {
                showLogInMenu();
                loggedUser = null;
            }
        }
    }

    private void moveBackLogInMenuCheck(String email) {
        if (email.equals("q"))
            showMainMenu();
        else showLogInMenu();
    }


    private void showSignInMenu() {
        String email;
        String firstName;
        String lastName;
        String pass1;
        String pass2;

        System.out.println("----------------SignIn----------------");
        System.out.println("----please enter you personal info----");
        System.out.println("--(ot type 'q' to back to main menu)--");
        System.out.println("----------(* - required data)---------");

        System.out.println("--*Enter your Email");
        email = scanner.next();

        if (!signAction.checkRegisteredEmail(email)) showSignInMenu();

        if (email.equals("q")) showMainMenu();

        System.out.println("--Enter your first name");
        firstName = scanner.next();

        System.out.println("--Enter your last name");
        lastName = scanner.next();

        System.out.println("--Enter password");
        pass1 = scanner.next();

        System.out.println("--Confirm password");
        pass2 = scanner.next();

        if (!signAction.checkPass(pass1, pass2)) {
            showSignInMenu();
        }

        loggedUser = signAction.createUser(email, firstName, lastName, pass1);
        isLogged = true;
        showMainMenu();
    }


    private void showEventListMenu() {
        System.out.println("----------------Events----------------");

        if (!isLogged)
            System.out.println("-please login or SignIn for buy ticket-");

        for (Event event : eventAction.showAllEvents())
            System.out.println(event);

        System.out.println("--------------------------------------");

        if (isLogged)
            System.out.println("--a.Buy ticket");

        System.out.println("--b.Back to main menu");

        while (!isChosen) {
            checkEventMenuInput(scanner.next());
        }
    }

    private void buyTicketMenu() {
        Long eventId;
        Event choosenEvent;
        System.out.println("----------------Buy ticket----------------");
        System.out.println("-----------Enter number of event----------");

        eventId = scanner.nextLong();
        choosenEvent = eventAction.getEventById(eventId);

        if (choosenEvent != null) {
            showVariantsMenu(choosenEvent);
        } else {
            System.out.println("You entered an incorrect event number, try again");
            buyTicketMenu();
        }
    }

    private void showVariantsMenu(Event event) {
        LocalDateTime choosenDate;
        System.out.println("------Dates and location of the event-----");
        System.out.println("--Enter the number of the available option--");
        Object dates[] = eventAction.showDates(event).toArray();

        for (int i = 0; i < dates.length; i++) {
            System.out.println(i + " " + dates[i]);
        }

        System.out.println("Enter the position of the interesting date");
        int number = scanner.nextInt();

        if (number < dates.length) {
            choosenDate = (LocalDateTime) dates[number];
            System.out.println("Available " + eventAction.showAuditorium(event));
            System.out.println("Enter the number of seat");
            eventAction.showSeats((LocalDateTime) dates[number], event);
            System.out.println();
            Long seatNumber = scanner.nextLong();

            if (eventAction.bookPlace(loggedUser, event, choosenDate, seatNumber)) {
                System.out.println("Do you want to buy ticket?");
                System.out.println("--a. Buy ticket");
                System.out.println("--b. Back");
                checkVariantsMenuInput(scanner.next(), event, choosenDate, seatNumber);
            } else {
                System.out.println("Choose another seat");
                showVariantsMenu(event);
            }

        } else {
            System.out.println("Incorrect input");
            showVariantsMenu(event);
        }
    }

    private void checkVariantsMenuInput(String next, Event event, LocalDateTime dateTime, Long seatNumber) {
        switch (next) {
            case "a":
                if (eventAction.buyTicket(event, dateTime, seatNumber, loggedUser)) showMainMenu();
                else System.out.println("You need to replenish your balance");
                break;
            case "b":
                showEventListMenu();
                break;
            default:
                System.out.println("Incorrect input");
                break;
        }
    }

    private void checkEventMenuInput(String next) {
        switch (next) {
            case "a":
                buyTicketMenu();
                break;
            case "b":
                showMainMenu();
                break;
            default:
                System.out.println("Incorrect input");
                break;
        }
    }

    private void checkMainMenuInput(String s) {
        switch (s) {
            case "a":
                showLogInMenu();
                break;
            case "b":
                showSignInMenu();
                break;
            case "c":
                showEventListMenu();
                break;
            case "e":
                isChosen = true;
                System.exit(0);
                break;
            case "d":
                isLogged = false;
                loggedUser = null;
                showMainMenu();
            default:
                System.out.println("Incorrect input");
                break;
        }
    }
}