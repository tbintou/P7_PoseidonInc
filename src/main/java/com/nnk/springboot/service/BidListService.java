package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;

import java.util.List;

public interface BidListService {

    void addBidList(BidList bidList);

    Boolean updateBidList(int id, BidList bidList);

    List<BidList> findAll();

    BidList findById(int id);

    void deleteById(int id);
}
