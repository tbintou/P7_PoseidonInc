package com.nnk.springboot.serviceImpl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public void addRating(Rating rating) {
        ratingRepository.save(rating);
        log.info("New rating " + rating + " is created !");
    }

    @Override
    public Boolean updateRating(int id, Rating rating) {

        Optional<Rating> ratingList = ratingRepository.findById(id);
        if (ratingList.isPresent()){
            Rating newRating = ratingList.get();
            newRating.setMoodysRating(rating.getMoodysRating());
            newRating.setSandPrating(rating.getSandPrating());
            newRating.setFitchRating(rating.getFitchRating());
            newRating.setOrderNumber(rating.getOrderNumber());
            ratingRepository.save(newRating);
            log.info("Rating with id " + id + " is updated as " + newRating);
            return true;
        }
        log.info("Failed to update rating with id " + id + " as" + rating);
        return false;
    }

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating findById(int id) {

        Optional<Rating> ratingList = ratingRepository.findById(id);
        if (ratingList.isPresent()){
            log.info("the id of rating is " + id);
            return ratingList.get();
        } else {
            log.info("Failed to find rating with id " + id);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {

        Optional<Rating> ratingList = ratingRepository.findById(id);
        if (ratingList.isPresent()){
            ratingRepository.delete(ratingList.get());
            log.info("Rating with this id " + id + " has been deleted !");
        }
        log.info("Failed to delete rating with id " + id);
    }
}
