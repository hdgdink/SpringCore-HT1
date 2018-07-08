package kz.epam.spring.hometask1.action.user;

import kz.epam.spring.hometask1.domain.User;
import kz.epam.spring.hometask1.service.impl.UserServiceImpl;

public class SignAction {
    private UserServiceImpl userService;

    public SignAction(UserServiceImpl userService) {
        this.userService = userService;
    }

    public SignAction() {
    }

    public User createUser(String email, String firstName, String lastName, String pass1) {
        User user = new User();
        user.setPass(pass1);
        user.setEmail(email);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user = userService.save(user);
        return user;
    }

    public boolean checkPass(String pass1, String pass2) {
        if (!pass1.equals(pass2)) {
            System.out.println("The entered passwords do not match, try again");
            return false;
        } else return true;
    }

    public boolean checkRegisteredEmail(String email) {
        if (userService.getUserByEmail(email) != null) {
            System.out.println("Entered email already registered, select another email");
            return false;
        } else return true;
    }
}