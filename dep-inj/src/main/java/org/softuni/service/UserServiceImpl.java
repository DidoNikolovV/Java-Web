package org.softuni.service;


import org.softuni.model.User;
import org.softuni.repository.InMemoryUserRepository;
import org.softuni.repository.UserRepository;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService,
        BeanNameAware {

    private final UserRepository userRepository;

    public UserServiceImpl(
            @Qualifier("memoryRepo")
            UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findYoungestUser() {
        return userRepository.findAll().stream()
                .min(Comparator.comparing(User::age));
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("My bean is named " + name);
    }
}
