package kz.epam.spring.hometask1.service;

import kz.epam.spring.hometask1.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface UserService {
    /**
     * Finding user by email
     *
     * @param email Email of the user
     * @return found user or <code>null</code>
     */
    public @Nullable
    User getUserByEmail(@Nonnull String email);
}