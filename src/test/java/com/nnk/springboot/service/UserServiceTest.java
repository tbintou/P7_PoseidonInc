package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void registrationUser() {

        User newUser = new User(999, "User", "Megane123", "test", "USER");
        userService.save(newUser);
    }
    @Test
    public void findByUsername(){

        User user = userService.findByUsername("newUser");
        assertNotNull(user);
    }

    @Test
    public void updateUser() {

        User user = userService.findByUsername("newUser");
        Integer idUser = user.getId();
        user.setUsername("Username");
        user.setFullname("Fullname");
        user.setPassword("Password56*");
        user.setRole("ADMIN");
        userService.updateUser(idUser, user);
        assertEquals("Username", user.getUsername());
        assertEquals("ADMIN", user.getRole());
    }

    @Test
    public void findById() {
        User user = userService.findByUsername("newUser");
        Integer idUser = user.getId();
        User userById = userService.findById(idUser);
        assertNotNull(userById);
    }

    @Test
    public void deletedUser() {
        User user = userService.findByUsername("newUser");
        userService.delete(user);
        User userByUsername = userService.findByUsername("foundUser");
        assertNull(userByUsername);
    }

}
