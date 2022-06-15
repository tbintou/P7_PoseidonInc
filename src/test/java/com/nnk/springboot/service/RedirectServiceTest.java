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
    public void formLoginAndPageRedirect() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
                .user("Jean")
                .password("tef#dPdze3");

        mockMvc.perform(login)
                .andExpect(authenticated().withUsername("Jean"))
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    public void formLoginRoleAdminAndPageRedirect() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
                .user("Michelle")
                .password("Pass;hhhQfg3");

        mockMvc.perform(login)
                .andExpect(authenticated().withUsername("Michelle"))
                .andExpect(redirectedUrl("/user/list"));
    }

}