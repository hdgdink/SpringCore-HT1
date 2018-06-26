package kz.epam.spring.hometask1.runner;

import kz.epam.spring.hometask1.service.EventService;
import kz.epam.spring.hometask1.service.UserService;
import kz.epam.spring.hometask1.service.impl.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private boolean isChoosen;

    public void showMainMenu() throws IOException {

        System.out.println("-------------Welcome to Cinema----------------");
        System.out.println("----------------------------------------------");
        System.out.println("------------------Main Menu-------------------");
        System.out.println("---------------(Choose action)----------------");
        System.out.println("--a.LogIn");
        System.out.println("--b.SignIn");
        System.out.println("--c.Show list of event");
        System.out.println("--d.Exit");

        while (true) {
            checkInput(reader.readLine());
        }
    }

    private void checkInput(String s) {
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
            case "d":
                System.exit(0);
            default:
                System.out.println("Incorrect input");
                break;
        }

    }

    private void showEventListMenu() {
        System.out.println("----------------Events----------------");

        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSignInMenu() {
        System.out.println("----------------SignIn----------------");
    }

    private void showLogInMenu() {
        System.out.println("----------------LogIn----------------");
    }

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.showMainMenu();
        DataInitializator initData = new DataInitializator();
        initData.initData();
        UserService userService = new UserServiceImpl();
        //System.out.println("Hello");
        //System.out.println("All users: " + userService.getAll());
        //System.out.println("User by email: " + userService.getUserByEmail("asd@asd.tr"));


    }
}
