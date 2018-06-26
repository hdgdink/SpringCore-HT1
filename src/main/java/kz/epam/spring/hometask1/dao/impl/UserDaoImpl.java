package kz.epam.spring.hometask1.dao.impl;

import kz.epam.spring.hometask1.dao.Dao;
import kz.epam.spring.hometask1.domain.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserDaoImpl implements Dao<User> {
    private static Map<Long, User> users = new HashMap<>();

    @Override
    public User addObject(User user) {
        //System.out.println("UserDaO: Add Object: " + user.toString());
        users.put(user.getId(), user);

        return user;
    }

    @Override
    public void removeObject(User user) {
        users.remove(user.getId(), user);
    }

    @Override
    public User getById(Long id) {
        User user = null;

        if (id != null) {
            user = users.get(id);
        }

        return user;
    }


    @Override
    public Collection getAll() {
      //  System.out.println("UserDao: Getall, size of collection: " + users.size());
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

}