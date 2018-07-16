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
//    private static DataInitializer dataInitializer;

    public App(/* DataInitializer dataInitializer,*/ MenuServiceImpl menuService) {
        App.menuService = menuService;
      //  App.dataInitializer=dataInitializer;
    }


    public static void main(String[] args) {
//        dataInitializer.initData();
        menuService.showMainMenu();
    }
}