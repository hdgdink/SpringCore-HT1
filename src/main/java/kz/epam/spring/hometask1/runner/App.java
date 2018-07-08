package kz.epam.spring.hometask1.runner;

import com.sun.org.apache.bcel.internal.util.ClassPath;
import kz.epam.spring.hometask1.service.impl.MenuServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DataInitializer initData = (DataInitializer) ctx.getBean("dataInitializer");
        initData.initData();
        MenuServiceImpl menu = (MenuServiceImpl) ctx.getBean("menuService");
        menu.showMainMenu();
    }
}