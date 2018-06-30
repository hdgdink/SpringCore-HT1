package kz.epam.spring.hometask1.service;

import kz.epam.spring.hometask1.action.event.EventAction;
import kz.epam.spring.hometask1.action.user.LogInAction;
import kz.epam.spring.hometask1.action.user.SignAction;
import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.domain.User;

import java.util.Scanner;

public class MenuService {
    private Scanner scanner = new Scanner(System.in);
    private LogInAction logInAction = new LogInAction();
    private SignAction signAction = new SignAction();
    private EventAction eventAction = new EventAction();
    private User loggedUser;
    private boolean isChoosen;
    private boolean isLogged;

    public void showMainMenu() {
        if (!isLogged)
            System.out.println("-------------Welcome to Cinema----------------");
        else System.out.println("Welcome " + loggedUser.getFirstName());

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

        while (!isChoosen) {
            checkMainMenuInput(scanner.next());
        }
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
        System.out.println("-please login or signin for buy ticket-");

        for (Event event : eventAction.showAllEvents())
            System.out.println(event);
        System.out.println("--------------------------------------");

        if (isLogged)
            System.out.println("--a.Buy ticket");

        System.out.println("--b.Back to main menu");

        while (!isChoosen) {
            checkEventMenuInput(scanner.next());
        }
    }

    private void buyTicketMenu() {
        Long eventId;
        Event choosenEvent;
        System.out.println("----------------Buy ticket----------------");
        System.out.println("-----------Enter number of event----------");


        eventId = scanner.nextLong();
        choosenEvent = eventAction.buyTicket(eventId);

        if (choosenEvent != null) {
            showVariantsMenu(choosenEvent);
        } else {
            System.out.println("You entered an incorrect event number, try again");
            buyTicketMenu();
        }
    }

    private void showVariantsMenu(Event event) {
        System.out.println("------Dates and location of the event-----");
        System.out.println("--Enter the number of the available option--");
        System.out.println("-or press q to return to the previous menu--");
        int i = 0;


        for (Object dates : eventAction.showDates(event)) {
            i++;
            System.out.println(i + " " + dates);
        }

        System.out.println("Enter the position of the interesting date");
        scanner.nextLine();


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
                isChoosen = true;
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