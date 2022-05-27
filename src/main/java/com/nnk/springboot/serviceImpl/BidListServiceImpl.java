package com.nnk.springboot.serviceImpl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.service.BidListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BidListServiceImpl implements BidListService {
    Logger logger = LoggerFactory.getLogger(BidListServiceImpl.class);

    private final BidListRepository bidListRepository;

    public BidListServiceImpl(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }


    @Override
    public void addBidList(BidList bidList) {
        bidListRepository.save(bidList);
        logger.info("New BidList " + bidList + " is created !");
    }

    @Override
    public Boolean updateBidList(int id, BidList bidList) {

        boolean updated = false;
        Optional<BidList> listBidList = bidListRepository.findById(id);
        if (listBidList.isPresent()) {
        BidList newBildList = listBidList.get();
        newBildList.setAccount(bidList.getAccount());
        newBildList.setType(bidList.getType());
        newBildList.setBidQuantity(bidList.getBidQuantity());
        bidListRepository.save(newBildList);
        updated = true;
        logger.info("BidList with id " + id + " is updated as " + newBildList);
        } else {
            logger.info("Failed to update BidList with id " + id + " as" + bidList);
        }
        return updated;
    }

    @Override
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    @Override
    public BidList findById(int id) {
        Optional<BidList> listBidList = bidListRepository.findById(id);
        if(listBidList.isPresent()) {
            logger.info("the id of BidList is " + id);
            return listBidList.get();
        } else {
            logger.info("Failed to find BidList with id " + id);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        Optional<BidList> listBidList = bidListRepository.findById(id);
        if (listBidList.isPresent()) {
            bidListRepository.delete(listBidList.get());
            logger.info("Bidlist with this id " + id + " has been deleted !");
        } else {
            logger.info("Failed to delete BidList with id " + id);
        }
    }
}
