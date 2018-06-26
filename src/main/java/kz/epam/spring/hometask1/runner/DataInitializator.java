package kz.epam.spring.hometask1.runner;

import kz.epam.spring.hometask1.domain.User;
import kz.epam.spring.hometask1.service.UserService;
import kz.epam.spring.hometask1.service.impl.UserServiceImpl;

public class DataInitializator {
    UserService userService = new UserServiceImpl();

    public void initData() {
        User user = new User();
        user.setFirstName("Vovka");
        user.setLastName("Pupkin");
        user.setEmail("asd@asd.tr");
        userService.save(user);
    }
}
