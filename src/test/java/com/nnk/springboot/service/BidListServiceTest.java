package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidListServiceTest {
    private BidList bidList = new BidList();

    @Autowired
    private BidListRepository bidListRepository;

    @Autowired
    private BidListService bidListService;

    @BeforeAll
    public void initBidList() {
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(89.99);
        bidListService.addBidList(bidList);
    }

    @AfterAll
    public void deletedBidList() {
        bidListRepository.delete(bidList);
    }

    @Test
    public void updatedById() {
        Integer bidListId = bidList.getBidListId();
        BidList bidListById = bidListService.findById(bidListId);
        bidListById.setAccount(" New account");
        bidListById.setType("New type");
        bidListById.setBidQuantity(500.50);
        Boolean updatedBidListById = bidListService.updateBidList(bidListId, bidListById);
        assertTrue(updatedBidListById);
    }

  /*  @Test
    public void findAll() {
        List<BidList> testBidListList = bidListService.findAll();
        assertTrue(testBidListList.size() > 0);
    }

    @Test
    public void findById() {
        Integer bidListId = bidList.getBidListId();
        bidListService.findById(bidListId);
        assertEquals("Account", bidList.getAccount());
        assertEquals("Type", bidList.getType());
        assertEquals(89.99, bidList.getBidQuantity(), 0.01);
    }

    @Test
    public void deleteById() {
        Integer bidListId = bidList.getBidListId();
        bidListService.deleteById(bidListId);
        BidList bidListById = bidListService.findById(bidListId);
        assertNull(bidListById);
    }*/
}
