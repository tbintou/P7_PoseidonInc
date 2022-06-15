package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RatingServiceTest {

    private Rating rating = new Rating();

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingService ratingService;

    @BeforeAll
    public void initRating() {
        rating.setMoodysRating("MoodysRating");
        rating.setSandPrating("SandPrating");
        rating.setFitchRating("FitchRating");
        rating.setOrderNumber(30);
        ratingService.addRating(rating);
    }

    @AfterAll
    public void deleteRating() {
        ratingRepository.deleteAll();
    }

    @Test
    public void updateById() {
        Integer ratingId = rating.getId();
        Rating ratingById = ratingService.findById(ratingId);
        ratingById.setMoodysRating("New moodysRating");
        ratingById.setSandPrating("New sandPrating");
        ratingById.setFitchRating("New FitchRating");
        ratingById.setOrderNumber(100);
        Boolean updateRating = ratingService.updateRating(ratingId, ratingById);
        assertTrue(updateRating);
    }

    @Test
    public void findAll() {
        List<Rating> ratingList = ratingService.findAll();
        assertTrue(ratingList.size() > 0);
    }

    @Test
    public void findById() {
        Integer ratingId = rating.getId();
        Rating ratingById = ratingService.findById(ratingId);
        assertNotNull(ratingById);
    }

    @Test
    public void deleteById() {
        Integer ratingId = rating.getId();
        ratingService.deleteById(ratingId);
        Rating ratingById = ratingService.findById(ratingId);
        assertNull(ratingById);
    }
}
