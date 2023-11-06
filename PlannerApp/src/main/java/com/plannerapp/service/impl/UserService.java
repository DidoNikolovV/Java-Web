package com.plannerapp.service.impl;

import com.plannerapp.model.entity.User;
import com.plannerapp.model.entity.dto.user.UserRegisterBindingModel;
import com.plannerapp.model.entity.dto.user.UserLoginBindingModel;
import com.plannerapp.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final LoggedUser loggedUser;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        if(!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            return false;
        }

        boolean existsByUsernameOrEmail = userRepository.existsByUsernameOrEmail(userRegisterBindingModel.getUsername(), userRegisterBindingModel.getEmail());

        if(existsByUsernameOrEmail) {
            return false;
        }

        User user = new User();

        user.setUsername(userRegisterBindingModel.getUsername());
        user.setEmail(userRegisterBindingModel.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        this.userRepository.save(user);

        return true;
    }

    public boolean login(UserLoginBindingModel userLoginBindingModel) {
        User dbUser = userRepository.findByUsername(userLoginBindingModel.getUsername());

        if(dbUser != null && passwordEncoder.matches(userLoginBindingModel.getPassword(), dbUser.getPassword())) {
            this.loggedUser.login(userLoginBindingModel.getUsername());

            return true;
        }

        return false;
    }

    public void logout() {
        this.loggedUser.logout();
    }
}
