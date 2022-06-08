package com.nnk.springboot.repositories.service;

import com.nnk.springboot.domain.Trade;

import java.util.List;

public interface TradeService {

    void  addTrade(Trade trade);

    Boolean updateTrade(int id, Trade trade);

    List<Trade> findAll();

    Trade findById(int id);

    void deleteById(int id);
}
