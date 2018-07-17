package kz.epam.spring.hometask1.runner;

import kz.epam.spring.hometask1.config.AppConfig;
import kz.epam.spring.hometask1.service.impl.MenuServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("kz.epam.spring.hometask1")
public class App {
    private static ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    private static MenuServiceImpl menuService;

    public App(MenuServiceImpl menuService) {
        App.menuService = menuService;
    }

    public static void main(String[] args) {
        menuService.showMainMenu();
    }
}