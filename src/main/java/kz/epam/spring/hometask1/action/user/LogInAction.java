package kz.epam.spring.hometask1.action.user;

import kz.epam.spring.hometask1.domain.Role;
import kz.epam.spring.hometask1.domain.User;
import kz.epam.spring.hometask1.runner.App;
import kz.epam.spring.hometask1.service.impl.UserServiceImpl;

public class LogInAction {
    private UserServiceImpl userService ;

    public LogInAction() {
    }

    public LogInAction(UserServiceImpl userService) {
        this.userService = userService;
    }

    public Boolean checkEmail(String email) {
        User user = userService.getUserByEmail(email);

        if (user == null) {
            System.out.println("Incorrect username(email), try again");
            return false;
        } else
            return true;
    }

    public User logIn(String email, String pass) {
        User loggedUser = userService.getUserByEmail(email);

        if (loggedUser != null && loggedUser.getPass().equals(pass)) {
            return loggedUser;
        } else {
            System.out.println("Incorrect password, try again");
            assert loggedUser != null;
            loggedUser.setEmail(null);
            return loggedUser;
        }
    }

    public boolean checkIsAdmin(User loggedUser) {
        return loggedUser.getRole().equals(Role.ADMIN);
    }
}