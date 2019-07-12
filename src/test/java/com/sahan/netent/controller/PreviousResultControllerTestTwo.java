package com.sahan.netent.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sahan.netent.model.PreviousResult;
import com.sahan.netent.service.PreviousResultService;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(PreviousResultController.class)
public class PreviousResultControllerTestTwo {

    private static final int UNIQUE_ID = 32;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PreviousResultService previousResultService;

    @Test
    public void testFindPreviousResult() throws Exception {
        PreviousResult previousResult = new PreviousResult();
        previousResult.setUniqueId(UNIQUE_ID);

        String expectedJson = this.mapToJson(previousResult);

        Mockito.when(previousResultService.findPreviousResultByUniqueId(UNIQUE_ID)).thenReturn(previousResult);

        String URI = "/rest/resultController/result?uniqueId=" + UNIQUE_ID;
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URI).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
        System.out.println("expectedJson -->> " + expectedJson);
        System.out.println("outputInJson -->> " + outputInJson);
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}