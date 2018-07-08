package kz.epam.spring.hometask1.runner;

import kz.epam.spring.hometask1.config.AppConfig;
import kz.epam.spring.hometask1.service.impl.MenuServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("ua.epam.spring.hometask")
public class App {
    private static ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    private static DataInitializer dataInitializer;
    private static MenuServiceImpl menuService;


    public App(DataInitializer dataInitializer, MenuServiceImpl menuService) {
        App.dataInitializer = dataInitializer;
        App.menuService = menuService;
    }

    public static void main(String[] args) {
        dataInitializer.initData();
        menuService.showMainMenu();
    }
}