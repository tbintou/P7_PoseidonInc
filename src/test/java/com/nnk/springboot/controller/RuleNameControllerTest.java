package com.nnk.springboot.controller;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;
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
@WithMockUser(username = "Morgane", authorities = {"USER"})
public class RuleNameControllerTest {

    private int id = 0;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Autowired
    private RuleNameService ruleNameService;

    @BeforeAll
    public void init() {
        RuleName ruleName = new RuleName("Name", "Description", "Json", "Template", "SqlStr", "SqlPart");
        ruleNameService.addRuleName(ruleName);
        for (RuleName ruleList : ruleNameService.findAll()) {
            if (ruleList.getName().equals("Name")) {
                id = ruleList.getId();
                break;
            }
        }
    }

    @AfterAll
    public void deleteRuleName() {
        ruleNameRepository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void addRuleName() throws Exception {
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void validateRuleName() throws Exception {
        mockMvc.perform(post("/ruleName/validate")
                        .param("name", "new Name")
                        .param("description", "new Description")
                        .param("json", "new Json")
                        .param("template", "new Template")
                        .param("sqlStr", "new SqlStr")
                        .param("sqlPart", "new SqlPart")
                        .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void updateById() throws Exception {
        mockMvc.perform(get("/ruleName/update/{id}", id)
                        .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    public void updateRuleName() throws Exception {
        mockMvc.perform(post("/ruleName/update/{id}", id)
                        .param("name", "update Name")
                        .param("description", "update Description")
                        .param("json", "update Json")
                        .param("template", "update Template")
                        .param("sqlStr", "update SqlStr")
                        .param("sqlPart", "update SqlPart")
                        .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(get("/ruleName/delete/{id}", id))
                .andDo(print())
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

}
