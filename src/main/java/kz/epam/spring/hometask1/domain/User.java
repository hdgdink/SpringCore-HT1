package kz.epam.spring.hometask1.domain;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class User extends DomainObject {
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String pass;
    private Double balance;


    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public User() {
        setId(ThreadLocalRandom.current().nextLong(0, 100));
        setRole(Role.CLIENT);
        setBalance(0.0);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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
                Objects.equals(pass, user.pass) &&
                Objects.equals(balance, user.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, role, pass, balance);
    }

    @Override
    public String toString() {
        return "User № " + getId() + " firstName = '" + firstName + "', lastName='" + lastName + "', email='" + email +
                ", role=" + role + ", pass='" + pass + "'";
    }
}