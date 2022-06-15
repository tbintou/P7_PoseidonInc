package com.nnk.springboot.controller;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "Ive", authorities = {"ADMIN"})
public class UserControllerTest {

    private int id = 0;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @BeforeAll
    public void init() {
        User user = new User("username", "password*23", "fullname", "USER");
        userService.save(user);
        for (User user1 : userService.findAll()) {
            if (user1.getUsername().equals("username")) {
                id = user1.getId();
                break;
            }
        }
    }

    @AfterAll
    public void deleteUser() {
        User user = userService.findByUsername("Hector");
        userService.delete(user);
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void addUser() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void validateUser() throws Exception {
        mockMvc.perform(post("/user/validate")
                        .param("username", "Hector89")
                        .param("fullname", "Hector")
                        .param("password", "Test@passWord*76")
                        .param("role", "USER"))
                .andExpect(redirectedUrl("/user/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void updateById() throws Exception {
        mockMvc.perform(get("/user/update/{id}", id)
                        .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUser() throws Exception {
        mockMvc.perform(post("/user/update/{id}", id)
                        .param("username", "John653")
                        .param("fullname", "John")
                        .param("password", "Password$John23")
                        .param("role", "USER"))
                .andExpect(redirectedUrl("/user/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(get("/user/delete/{id}", id))
                .andDo(print())
                .andExpect(redirectedUrl("/user/list"))
                .andExpect(status().isFound())
                .andReturn();
    }
    
}
