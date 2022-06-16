package com.nnk.springboot.repository;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BidListRepositoryTest {

    @Autowired
    private BidListRepository bidListRepository;

    @Test
    public void bidListTest() {
        BidList bidList = new BidList();
                bidList.setAccount("Account");
                bidList.setType("Type");
                bidList.setBidQuantity(20.01);

        // Save
        bidList = bidListRepository.save(bidList);
        Assertions.assertNotNull(bidList.getBidListId());
        Assertions.assertEquals(bidList.getBidQuantity(), 20.01);

		// Update
		bidList.setBidQuantity(40.50);
        bidList = bidListRepository.save(bidList);
		Assertions.assertEquals(bidList.getBidQuantity(), 40.50);

		// Find
		List<BidList> listResult = bidListRepository.findAll();
		Assertions.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = bidList.getBidListId();
		bidListRepository.delete(bidList);
		Optional<BidList> listBidList = bidListRepository.findById(id);
		Assertions.assertFalse(listBidList.isPresent());
    }

}
