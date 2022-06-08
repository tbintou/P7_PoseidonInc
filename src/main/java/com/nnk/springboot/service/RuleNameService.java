package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;

import java.util.List;

public interface RuleNameService {

    void addRuleName(RuleName ruleName);

    Boolean updateRuleName(int id, RuleName ruleName);

    List<RuleName> findAll();

    RuleName findById(int id);

    void deleteById(int id);
}
