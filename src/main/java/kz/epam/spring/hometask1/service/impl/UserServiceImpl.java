package kz.epam.spring.hometask1.service.impl;

import kz.epam.spring.hometask1.dao.impl.UserDao;
import kz.epam.spring.hometask1.domain.User;
import kz.epam.spring.hometask1.service.UserService;

import javax.annotation.Nonnull;
import java.util.Collection;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDao();

    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public User save(@Nonnull User object) {
        return userDao.addObject(object);
    }

    @Override
    public void remove(@Nonnull User object) {
        userDao.removeObject(object);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return userDao.getById(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userDao.getAll();
    }
}
