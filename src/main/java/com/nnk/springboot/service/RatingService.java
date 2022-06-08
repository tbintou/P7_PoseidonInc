package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;

import java.util.List;

public interface RatingService {

    void addRating(Rating rating);

    Boolean updateRating(int id, Rating rating);

    List<Rating> findAll();

    Rating findById(int id);

    void deleteById(int id);
}
