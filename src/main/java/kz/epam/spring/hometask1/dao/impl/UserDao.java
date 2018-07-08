package kz.epam.spring.hometask1.dao.impl;

import kz.epam.spring.hometask1.dao.Dao;
import kz.epam.spring.hometask1.domain.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserDao implements Dao<User> {
    private static Map<Long, User> users = new HashMap<>();

    @Override
    public User addObject(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void removeObject(User user) {
        users.remove(user.getId(), user);
    }

    @Override
    public User getById(Long id) {
        return users.get(id);
    }

    @Override
    public Collection getAll() {
        return users.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public User getUserByEmail(String email) {
        User user = null;
        List<User> userList = new ArrayList<>(users.values());

        for (User anUserList : userList) {

            if (email.equals(anUserList.getEmail()))
                user = anUserList;
        }

        return user;
    }

    public void setBalance(Long id, Double balance) {
        users.get(id).setBalance(balance);
    }
}