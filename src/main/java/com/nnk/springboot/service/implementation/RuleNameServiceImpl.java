package com.nnk.springboot.service.implementation;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RuleNameServiceImpl implements RuleNameService {

    private final RuleNameRepository ruleNameRepository;

    @Autowired
    public RuleNameServiceImpl(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }


    @Override
    public void addRuleName(RuleName ruleName) {
        ruleNameRepository.save(ruleName);
        log.info("New rule name " + ruleName + " is created !");
    }

    @Override
    public Boolean updateRuleName(int id, RuleName ruleName) {

        Optional<RuleName> ruleNameList = ruleNameRepository.findById(id);
        if (ruleNameList.isPresent()){
            RuleName newRuleName = ruleNameList.get();
            newRuleName.setName(ruleName.getName());
            newRuleName.setDescription(ruleName.getDescription());
            newRuleName.setJson(ruleName.getJson());
            newRuleName.setTemplate(ruleName.getTemplate());
            newRuleName.setSqlStr(ruleName.getSqlStr());
            newRuleName.setSqlPart(ruleName.getSqlPart());
            ruleNameRepository.save(newRuleName);
            log.info("Rule name with id " + id + " is updated as " + newRuleName);
            return true;
        }
        log.info("Failed to update rule name with id " + id + " as" + ruleName);
        return false;
    }

    @Override
    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }

    @Override
    public RuleName findById(int id) {

        Optional<RuleName> ruleNameList = ruleNameRepository.findById(id);
        if (ruleNameList.isPresent()){
            log.info("the id of rule name is " + id);
            return ruleNameList.get();
        } else {
            log.info("Failed to find rule name with id " + id);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {

        Optional<RuleName> ruleNameList = ruleNameRepository.findById(id);
        if (ruleNameList.isPresent()){
            ruleNameRepository.delete(ruleNameList.get());
            log.info("Rule name with this id " + id + " has been deleted !");
        }
        log.info("Failed to delete rule name with id " + id);
    }
}
