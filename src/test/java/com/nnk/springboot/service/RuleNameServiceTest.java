package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RuleNameServiceTest {

    private RuleName ruleName = new RuleName();

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Autowired
    private RuleNameService ruleNameService;

    @BeforeAll
    public void initRuleName() {
        ruleName.setName("Name");
        ruleName.setDescription("Description");
        ruleName.setJson("Json");
        ruleName.setTemplate("Template");
        ruleName.setSqlStr("SqlStr");
        ruleName.setSqlPart("SqlPart");
        ruleNameService.addRuleName(ruleName);
    }

    @AfterAll
    public void deleteRuleName() {
        ruleNameRepository.deleteAll();
    }

    @Test
    public void updateById() {
        Integer ruleNameId = ruleName.getId();
        RuleName ruleNameById = ruleNameService.findById(ruleNameId);
        ruleNameById.setName("New name");
        ruleNameById.setDescription("New description");
        ruleNameById.setJson("New Json");
        ruleNameById.setTemplate("New template");
        ruleNameById.setSqlStr("New SqlStr");
        ruleNameById.setSqlPart("New SqlPart");
        Boolean updateRuleName = ruleNameService.updateRuleName(ruleNameId, ruleNameById);
        assertTrue(updateRuleName);
    }

    @Test
    public void findAll() {
        List<RuleName> ruleNameList = ruleNameService.findAll();
        assertTrue(ruleNameList.size() > 0);
    }

    @Test
    public void findById() {
        Integer ruleNameId = ruleName.getId();
        RuleName ruleNameById = ruleNameService.findById(ruleNameId);
        assertNotNull(ruleNameById);
    }

    @Test
    public void deleteById() {
        Integer ruleNameId = ruleName.getId();
        ruleNameService.deleteById(ruleNameId);
        RuleName ruleNameById = ruleNameService.findById(ruleNameId);
        assertNull(ruleNameById);
    }
}
