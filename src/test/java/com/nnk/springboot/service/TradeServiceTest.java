package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TradeServiceTest {

    private Trade trade = new Trade();

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TradeService tradeService;

    @BeforeAll
    public void initTrade() {
        trade.setAccount("Account");
        trade.setType("Type");
        trade.setBuyQuantity(200.90);
        tradeService.addTrade(trade);
    }

    @AfterAll
    public void deleteTrade() {
        tradeRepository.deleteAll();
    }

    @Test
    public void updateById() {
        Integer tradeId = trade.getTradeId();
        Trade tradeById = tradeService.findById(tradeId);
        tradeById.setAccount("New account");
        tradeById.setType("New type");
        tradeById.setBuyQuantity(800.55);
        Boolean updateTrade = tradeService.updateTrade(tradeId, tradeById);
        assertTrue(updateTrade);
    }

    @Test
    public void findAll() {
        List<Trade> tradeList = tradeService.findAll();
        assertTrue(tradeList.size() > 0);
    }

    @Test
    public void findById() {
        Integer tradeId = trade.getTradeId();
        Trade tradeById = tradeService.findById(tradeId);
        assertNotNull(tradeById);
    }

    @Test
    public void deleteById() {
        Integer tradeId = trade.getTradeId();
        tradeService.deleteById(tradeId);
        Trade tradeById = tradeService.findById(tradeId);
        assertNull(tradeById);
    }
}
