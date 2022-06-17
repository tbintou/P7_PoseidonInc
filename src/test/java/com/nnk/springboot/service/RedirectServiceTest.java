package com.nnk.springboot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RedirectServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "Jean", authorities = {"USER"})
    public void roleUserPageError() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "Michelle", authorities = {"ADMIN"})
    public void roleAdminContent() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("User List")));
    }

    @Test
    @WithMockUser(username = "Adrian", authorities = {"USER"})
    public void formLoginAndPageRedirect() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
                .user("Adrian78")
                .password("Password$Adrian9");
        mockMvc.perform(login)
                .andExpect(authenticated().withUsername("Adrian78"))
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    @WithMockUser(username = "Bintou", authorities = {"ADMIN"})
    public void formLoginRoleAdminAndPageRedirect() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
                .user("admin")
                .password("Test1&Password");

        mockMvc.perform(login)
                .andExpect(authenticated().withUsername("admin"))
                .andExpect(redirectedUrl("/user/list"));
    }

}
