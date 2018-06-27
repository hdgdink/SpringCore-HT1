package kz.epam.spring.hometask1.service;

import kz.epam.spring.hometask1.action.menu.LogInAction;

import java.util.Scanner;

public class MenuService {
    private Scanner scanner = new Scanner(System.in);
    private LogInAction logInAction = new LogInAction();
    private boolean isChoosen;
    private boolean isLogged;

    public void showMainMenu() {
        if (!isLogged)
            System.out.println("-------------Welcome to Cinema----------------");

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
                moveBackCheck(email);
            }

            System.out.println("---------Enter your password---------");
            pass = scanner.next();

            if (logInAction.logIn(email, pass)) {
                isLogged = true;
                showMainMenu();
            } else showLogInMenu();

        }

    }

    private void moveBackCheck(String email) {
        if (email.equals("q")) {
            showMainMenu();
        } else showLogInMenu();
    }

    private void showSignInMenu() {
        System.out.println("----------------SignIn----------------");
        System.out.println("----please enter you personal info----");
        System.out.println("----------(* - required data)---------");
        System.out.println("--*Enter your Email");
        scanner.next();

    }

    private void BuyTicketMenu() {
        System.out.println("----------------Buy ticket----------------");
    }

    private void showAllEventsMenu() {
        System.out.println("----------------All events today----------------");
    }

    private void showEventListMenu() {
        System.out.println("----------------Events----------------");
        System.out.println("--a.Show All");

        if (isLogged)
            System.out.println("--b.Buy ticket");
        System.out.println("--c.Back to main menu");

        while (!isChoosen) {
            checkEventMenuInput(scanner.next());
        }

    }

    private void checkEventMenuInput(String next) {
        switch (next) {
            case "a":
                showAllEventsMenu();
                break;
            case "b":
                BuyTicketMenu();
                break;
            case "c":
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
                showMainMenu();
            default:
                System.out.println("Incorrect input");
                break;
        }

    }
}