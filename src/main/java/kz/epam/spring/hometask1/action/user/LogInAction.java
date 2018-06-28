package kz.epam.spring.hometask1.action.user;

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

    public User logIn(String email, String pass) {
        User loggedUser;
        loggedUser = userService.getUserByEmail(email);

        if (loggedUser.getPass().equals(pass)) {
            return loggedUser;
        } else {
            System.out.println("Incorrect password, try again");
            loggedUser.setEmail(null);
            return loggedUser;
        }
    }
}