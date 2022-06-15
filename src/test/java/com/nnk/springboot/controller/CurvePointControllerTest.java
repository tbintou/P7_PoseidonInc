package com.nnk.springboot.controller;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurvePointService;
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
public class CurvePointControllerTest {

    private int id = 0;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Autowired
    private CurvePointService curvePointService;

    @BeforeAll
    public void init() {
        CurvePoint curvePoint = new CurvePoint(25, 3.5, 600.95);
        curvePointService.addCurvePoint(curvePoint);
        for (CurvePoint curvePointList : curvePointService.findAll()) {
            if (curvePointList.getCurvePointId() == 25) {
                id = curvePointList.getId();
                break;
            }
        }
    }

    @AfterAll
    public void deleteCurvePoint() {
        curvePointRepository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void addCurvePoint() throws Exception {
        mockMvc.perform(post("/curvePoint/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void validateCurvePoint() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                .param("curvePointId", "123")
                .param("term", "4.0")
                .param("value", "60.5")
                .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void updateById() throws Exception {
        mockMvc.perform(get("/curvePoint/update/{id}", id)
                .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCurvePointById() throws Exception {
        mockMvc.perform(post("/curvePoint/update/{id}", id)
                .param("curvePointId", "456")
                .param("term", "20.6")
                .param("value", "99.0")
                .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(get("/curvePoint/delete/{id}", id))
                .andDo(print())
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

}
