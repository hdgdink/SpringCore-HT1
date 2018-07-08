package kz.epam.spring.hometask1.action.user.admin;

import kz.epam.spring.hometask1.domain.Auditorium;
import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.domain.EventRating;
import kz.epam.spring.hometask1.domain.User;
import kz.epam.spring.hometask1.service.impl.AuditoriumServiceImpl;
import kz.epam.spring.hometask1.service.impl.EventServiceImpl;
import kz.epam.spring.hometask1.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by HdgDink} on 07.07.2018.
 */
@Component
@ComponentScan
public class AdminAction {
    private Scanner scanner = new Scanner(System.in);
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EventServiceImpl eventService;
    @Autowired
    private AuditoriumServiceImpl auditoriumService;

    private Boolean isEmpty = false;
    private Boolean isChosen = false;

    public AdminAction(UserServiceImpl userService, EventServiceImpl eventService, AuditoriumServiceImpl auditoriumService) {
        this.userService = userService;
        this.auditoriumService = auditoriumService;
        this.eventService = eventService;
    }

    public void showAdminMenu() {
        isChosen = false;
        System.out.println("Select an action");
        System.out.println("--a.Add new user");
        System.out.println("--b.Show all users");
        System.out.println("--c.Show all events");
        System.out.println("--d.Add new event");
        System.out.println("--e.See purchased tickets");
        System.out.println("--f.Log out");

        while (!isChosen) {
            checkAdminMenuInput(scanner.next());
        }

    }

    private void addUser() {
        String firstName;
        String lastName;
        String email = null;
        String pass;
        Double balance;
        User user = new User();

        System.out.println("--------Enter the name of the new user--------");
        System.out.println("----------------(can be empty)----------------");
        firstName = scanner.next();
        System.out.println("--------Enter the last name of the new user--------");
        System.out.println("----------------(can be empty)----------------");
        lastName = scanner.next();
        System.out.println("--------Enter the email of the new user--------");

        while (!isEmpty) {
            email = scanner.next();
            isEmpty = checkEmail(email);
        }

        System.out.println("--------Enter the pass for the new account--------");
        pass = scanner.next();

        System.out.println("--------Enter the balance for the new account--------");
        balance = scanner.nextDouble();
        createNewUser(user, firstName, lastName, email, pass, balance);
    }

    private void createNewUser(User user, String firstName, String lastName, String email, String pass, Double balance) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPass(pass);
        user.setBalance(balance);

        if (userService.save(user) != null) {
            System.out.println("New user with name: " + user.getFirstName() + " and email: " + user.getEmail()
                    + " is added");
        } else {
            System.out.println("Error of creating the user");
        }
    }

    private boolean checkEmail(String email) {
        if (userService.getUserByEmail(email) != null) {
            System.out.println("Email is busy, input another");
            return false;
        } else return true;
    }

    private void showAllUsers() {
        for (User user : userService.getAll())
            System.out.println(user);

        System.out.println("-----------Choose action-------------");
        System.out.println("---a.Edit user");
        System.out.println("---b.Back to admin menu");

        while (!isChosen) {
            checkUserListMenuInput(scanner.next());
        }
    }

    private void checkUserListMenuInput(String next) {
        if (next.equals("a")) {
            showEditUserMenu();
        } else if (next.equals("b")) {
            showAdminMenu();
        }
    }

    private void showEditUserMenu() {
        Long userId;
        User user;
        String firstName;
        String lastName;
        String email;
        String pass;
        Double balance;

        System.out.println("-------Enter the number of user for editing------");
        userId = scanner.nextLong();
        user = userService.getById(userId);
        email = user.getEmail();
        System.out.println("You selected a user: " + user);

        System.out.println("Current user's first name is: " + user.getFirstName());
        System.out.println("Enter a new name or enter the current name again");
        firstName = scanner.next();

        System.out.println("Current user's last first name is: " + user.getLastName());
        System.out.println("Enter a new last name or enter the current last name again");
        lastName = scanner.next();

        System.out.println("Current user's pass is: " + user.getPass());
        System.out.println("Enter a new password or enter the current password again");
        pass = scanner.next();

        System.out.println("Current user's balance is: " + user.getBalance());
        System.out.println("Enter a new balance or enter the current balance again");
        balance = scanner.nextDouble();

        createNewUser(user, firstName, lastName, email, pass, balance);
        showAllUsers();
    }

    private void showAllEvents() {
        for (Event event : eventService.getAll())
            System.out.println(event);

        System.out.println("-----------Choose action-------------");
        System.out.println("---a.Edit event");
        System.out.println("---b.Back to admin menu");

        while (!isChosen) {
            checkEventListMenuInput(scanner.next());
        }
    }

    private void checkEventListMenuInput(String next) {
        if (next.equals("a")) {
            showEditEventMenu();
        } else if (next.equals("b")) {
            showAdminMenu();
        }
    }

    private void showEditEventMenu() {
        Long eventId;
        Event event;
        String eventName;
        Double basePrice;
        EventRating eventRating;

        System.out.println("-------Enter the number of event for editing------");
        eventId = scanner.nextLong();
        event = eventService.getById(eventId);

        System.out.println("You selected a event: " + event);

        System.out.println("Current event's name is: " + event.getName());
        System.out.println("Enter a new name or enter the current name again");
        eventName = scanner.next();

        System.out.println("Current event's base price is: " + event.getBasePrice());
        System.out.println("Enter a new price or enter the current price again");
        basePrice = scanner.nextDouble();

        System.out.println("Current event's rating is: " + event.getRating());
        System.out.println("Enter a new rating or enter the current rating again");
        System.out.println("(rating can be: high, low or medium)");

        eventRating = checkRating(scanner.next());

        if (eventRating != null) {
            saveEvent(event, eventName, basePrice, eventRating);
            System.out.println("Event:" + event.getName() + " is saved");
            showAllEvents();
        } else {
            System.out.println("Your enter wrong rating, try again");
            showEditEventMenu();
        }
    }

    private void saveEvent(Event event, String eventName, Double basePrice, EventRating eventRating) {
        event.setName(eventName);
        event.setBasePrice(basePrice);
        event.setRating(eventRating);
        eventService.save(event);
    }

    private EventRating checkRating(String next) {
        EventRating newRating = null;

        if (next.equalsIgnoreCase("high")) {
            newRating = EventRating.HIGH;
            return newRating;
        } else if (next.equalsIgnoreCase("medium")) {
            newRating = EventRating.MID;
            return newRating;
        } else if (next.equalsIgnoreCase("low")) {
            newRating = EventRating.LOW;
            return newRating;
        } else return newRating;
    }

    private void addEvent() {
        String name;
        Double basePrice;
        EventRating eventRating;
        Event event = new Event();
        Boolean isFinished = false;

        System.out.println("-----------Add new event---------");

        System.out.println("-----------Enter the name--------");
        name = scanner.next();

        System.out.println("-------Enter the base price------");
        basePrice = scanner.nextDouble();

        System.out.println("-------Enter the rating------");
        eventRating = checkRating(scanner.next());

        while (!isFinished) {
            System.out.println("--Enter the number of available auditorium--");
            event.assignAuditorium(getLocalDateTime(event), addAuditorium());
            System.out.println("----Do you want add one more auditorium?----");
            System.out.println("--a. yes");
            System.out.println("--b. no");

            if (scanner.next().equals("a")) {
                event.assignAuditorium(getLocalDateTime(event), addAuditorium());
            } else isFinished = true;
        }

        createNewEvent(event, name, basePrice, eventRating);
    }

    private void createNewEvent(Event event, String name, Double basePrice, EventRating eventRating) {
        event.setRating(eventRating);
        event.setName(name);
        event.setBasePrice(basePrice);
        eventService.save(event);
        System.out.println("Event: " + event.getName() + " saved");
        showAllEvents();
    }


    private LocalDateTime getLocalDateTime(Event event) {
        LocalDateTime df = LocalDateTime.now().plusDays(ThreadLocalRandom.current().nextLong(0, 100));
        event.addAirDateTime(df);
        return df;
    }

    private Auditorium addAuditorium() {
        for (Auditorium auditoriumList : auditoriumService.getAll()) {
            System.out.println("№ " + auditoriumList.getId() + " " + auditoriumList);
        }

        return auditoriumService.getById(scanner.nextLong());
    }

    private void checkAdminMenuInput(String next) {
        switch (next) {
            case "a":
                addUser();
                showAdminMenu();
                break;
            case "b":
                showAllUsers();
                break;
            case "c":
                showAllEvents();
                break;
            case "d":
                addEvent();
                break;
            case "e":
                isChosen = true;
                break;
            default:
                System.out.println("Incorrect input");
                break;
        }
    }
}