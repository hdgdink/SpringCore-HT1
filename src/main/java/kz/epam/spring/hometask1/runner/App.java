package kz.epam.spring.hometask1.runner;

import kz.epam.spring.hometask1.service.MenuService;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        DataInitializer initData = new DataInitializer();
        initData.initData();
       // UserService userService = new UserServiceImpl();
       // System.out.println("All users: " + userService.getAll());
        //System.out.println("User by email: " + userService.getUserByEmail("asd@asd.asd"));
        MenuService menu = new MenuService();
        menu.showMainMenu();
    }
}
