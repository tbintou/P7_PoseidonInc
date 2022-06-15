package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "Alice", authorities = {"USER"})
public class RatingControllerTest {
    private int id = 0;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingService ratingService;

    @BeforeAll
    public void init() {
        Rating rating = new Rating("MoodysRating", "SandPrating", "FitchRating", 3);
        ratingService.addRating(rating);
        for (Rating ratingList : ratingService.findAll()) {
            if (rating.getMoodysRating().equals("MoodysRating")) {
                id = ratingList.getId();
                break;
            }
        }
    }

    @AfterAll
    public void deleteRating() {
        ratingRepository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void addRating() throws Exception {
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void validateRating() throws Exception {
        mockMvc.perform(post("/rating/validate")
                        .param("moodysRating", "new MoodysRating")
                        .param("sandPRating", "new SandPRating")
                        .param("fitchRating", "new fitchRating")
                        .param("orderNumber", "50")
                        .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void updateById() throws Exception {
        mockMvc.perform(get("/rating/update/{id}", id)
                        .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    public void updateRating() throws Exception {
        mockMvc.perform(post("/rating/update/{id}", id)
                        .param("moodysRating", "update MoodysRating")
                        .param("sandPRating", "update SandPRating")
                        .param("fitchRating", "FitchRating")
                        .param("orderNumber", "150")
                        .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(get("/rating/delete/{id}", id))
                .andDo(print())
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

}
