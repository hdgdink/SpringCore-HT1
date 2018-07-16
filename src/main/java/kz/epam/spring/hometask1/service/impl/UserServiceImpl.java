package kz.epam.spring.hometask1.service.impl;

import kz.epam.spring.hometask1.dao.impl.UserDao;
import kz.epam.spring.hometask1.domain.User;
import kz.epam.spring.hometask1.service.AbstractDomainService;
import kz.epam.spring.hometask1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Collection;

@Service
@ComponentScan
public class UserServiceImpl implements UserService, AbstractDomainService<User> {
    @Autowired
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserServiceImpl() {
    }

    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public User save(@Nonnull User object) {
        return userDao.addObject(object);
    }

    @Override
    public void update(@Nonnull User object) {
        userDao.updateObject(object);
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