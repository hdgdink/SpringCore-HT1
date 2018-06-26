package kz.epam.spring.hometask1.domain;

import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class User extends DomainObject {
    private String firstName;
    private String lastName;
    private String email;
    private Role role;

    public User() {
        setId(ThreadLocalRandom.current().nextLong(0, 100));
        setRole(Role.CLIENT);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private NavigableSet<Ticket> tickets = new TreeSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NavigableSet<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(NavigableSet<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                role == user.role &&
                Objects.equals(tickets, user.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, role, tickets);
    }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + getId()+ '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", tickets=" + tickets +
                '}';
    }
}