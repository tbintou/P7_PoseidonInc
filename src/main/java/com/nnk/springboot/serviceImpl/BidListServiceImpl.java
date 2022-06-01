package com.nnk.springboot.serviceImpl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.service.BidListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class BidListServiceImpl implements BidListService {

    private final BidListRepository bidListRepository;

    public BidListServiceImpl(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }


    @Override
    public void addBidList(BidList bidList) {
        bidListRepository.save(bidList);
        log.info("New BidList " + bidList + " is created !");
    }

    @Override
    public Boolean updateBidList(int id, BidList bidList) {

        Optional<BidList> listBidList = bidListRepository.findById(id);
        if (listBidList.isPresent()) {
        BidList newBidList = listBidList.get();
            newBidList.setAccount(bidList.getAccount());
            newBidList.setType(bidList.getType());
            newBidList.setBidQuantity(bidList.getBidQuantity());
        bidListRepository.save(newBidList);
        log.info("BidList with id " + id + " is updated as " + newBidList);
        return true;
        }
         log.info("Failed to update BidList with id " + id + " as" + bidList);
        return false;
    }

    @Override
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    @Override
    public BidList findById(int id) {
        Optional<BidList> listBidList = bidListRepository.findById(id);
        if(listBidList.isPresent()) {
            log.info("the id of BidList is " + id);
            return listBidList.get();
        } else {
            log.info("Failed to find BidList with id " + id);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        Optional<BidList> listBidList = bidListRepository.findById(id);
        if (listBidList.isPresent()) {
            bidListRepository.delete(listBidList.get());
            log.info("Bidlist with this id " + id + " has been deleted !");
        } else {
            log.info("Failed to delete BidList with id " + id);
        }
    }
}
