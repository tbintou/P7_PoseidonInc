package com.nnk.springboot.serviceImpl;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.domain.dto.UserRegistrationDTO;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.repositories.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null){
            logger.info("Find a user by name" + username);
            return user;
        } else {
            logger.error("This username cannot be found" + username);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            logger.info("Find a user by id");
            return userOptional.get();
        } else {
            logger.error("this id doesn't exist");
        }
        return null;
    }

    @Override
    public Boolean updateUser(int id, User user) {
        boolean updated = false;
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User newUser = new User();
            newUser.setFullname(user.getFullname());
            newUser.setUsername(user.getUsername());
            newUser.setPassword(user.getPassword());
            newUser.setRole(user.getRole());
            userRepository.save(newUser);
            updated = true;
            logger.info("User with id " + id + " is updated as " + user);
        } else {
            logger.info("Failed to update User with id " + id + " as " + user);
        }
        return updated;
    }

    @Override
    public void save(User user) {
        User newUser = new User();
        newUser.setFullname(user.getFullname());
        newUser.setUsername(user.getUsername());
        newUser.setRole(user.getRole());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);
        logger.info("New User " + newUser + " is created!");
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
        logger.info("this user " + user + " is deleted !");
    }
}
