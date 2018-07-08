package kz.epam.spring.hometask1.config;

import kz.epam.spring.hometask1.runner.App;
import kz.epam.spring.hometask1.runner.DataInitializer;
import kz.epam.spring.hometask1.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by HdgDink} on 08.07.2018.
 */

@Configuration
@ComponentScan("kz.epam.spring.hometask1")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
    @Autowired
    private DataInitializer dataInitializer;
    @Autowired
    private MenuServiceImpl menuService;

    @Bean(name = "app")
    public App getApp() {
        return new App(dataInitializer, menuService);
    }
}
