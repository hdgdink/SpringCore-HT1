package kz.epam.spring.hometask1.action.menu;

import kz.epam.spring.hometask1.domain.User;
import kz.epam.spring.hometask1.service.UserService;
import kz.epam.spring.hometask1.service.impl.UserServiceImpl;

public class LogInAction {
    private UserService userService = new UserServiceImpl();

    public Boolean checkEmail(String email) {
        User user;
        user = userService.getUserByEmail(email);

        if (user == null) {
            System.out.println("Incorrect username(email), try again");
            return false;
        } else
            return true;
    }

    public Boolean logIn(String email, String pass) {
        User loggedUser;
        loggedUser = userService.getUserByEmail(email);

        if (loggedUser.getPass().equals(pass)) {
            System.out.println("Welcome " + loggedUser.getFirstName());
            return true;
        } else {
            System.out.println("Incorrect password, try again");
            return false;
        }
    }
}