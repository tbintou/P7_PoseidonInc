package com.nnk.springboot.serviceImpl;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.repositories.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TradeServiceImpl implements TradeService {

    private final TradeRepository tradeRepository;

    @Autowired
    public TradeServiceImpl(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public void addTrade(Trade trade) {
        tradeRepository.save(trade);
        log.info("New trade " + trade + " is created !");
    }

    @Override
    public Boolean updateTrade(int id, Trade trade) {

        Optional<Trade> tradeList = tradeRepository.findById(id);
        if (tradeList.isPresent()){
            Trade newTrade = tradeList.get();
            newTrade.setAccount(trade.getAccount());
            newTrade.setType(trade.getType());
            newTrade.setBuyQuantity(trade.getBuyQuantity());
            tradeRepository.save(newTrade);
            log.info("Trade with id " + id + " is updated as " + newTrade);
            return true;
        }
        log.info("Failed to update trade with id " + id + " as" + trade);
        return false;
    }

    @Override
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    @Override
    public Trade findById(int id) {

        Optional<Trade> tradeList = tradeRepository.findById(id);
        if (tradeList.isPresent()){
            log.info("the id of trade is " + id);
            return tradeList.get();
        } else {
            log.info("Failed to find trade with id " + id);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {

        Optional<Trade> tradeList = tradeRepository.findById(id);
        if (tradeList.isPresent()){
            tradeRepository.delete(tradeList.get());
            log.info("Trade with this id " + id + " has been deleted !");
        }
        log.info("Failed to delete trade with id " + id);
    }
}
