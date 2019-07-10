package com.sahan.netent.controller;

import com.sahan.netent.model.PreviousResult;
import com.sahan.netent.service.PreviousResultService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PreviousResultController.class)
public class PreviousResultControllerTest {

    private static final int ID = 3;
    private static final double AMOUNT = 20;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PreviousResultService service;

    @Before
    public void setUp() {
        when(service.findPreviousResultByUniqueId(ID)).thenReturn(createResultMock());
    }

    @Test
    public void testFindPreviousResult() throws Exception {
        this.mockMvc.perform(get("/rest/resultController/result").param("uniqueId", String.valueOf(ID)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", Matchers.is(AMOUNT)))
                .andExpect(jsonPath("$.id", Matchers.is(ID)))
                .andExpect(jsonPath("$.uniqueId",Matchers.is(ID)));
    }

    private PreviousResult createResultMock() {
        PreviousResult result = new PreviousResult();
        result.setAmount(AMOUNT);
        result.setId(ID);
        result.setUniqueId(ID);
        return result;
    }
}