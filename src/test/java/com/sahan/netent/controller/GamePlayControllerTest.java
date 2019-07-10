package com.sahan.netent.controller;

import com.sahan.netent.model.GamePlay;
import com.sahan.netent.service.GamePlayService;
import com.sahan.netent.utility.ResponseObject;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GamePlayController.class)
public class GamePlayControllerTest {

    private static final int COINS = 10;
    private static final int GAME_TYPE = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GamePlayService gamePlayService;

    @Before
    public void setUp() {
        GamePlay gamePlay = new GamePlay();
        gamePlay.setCoins(COINS);
        gamePlay.setGameType(GAME_TYPE);
        when(gamePlayService.playNormalGameRound(gamePlay)).thenReturn(createResultMock());
    }

    @Test
    public void testPlayNormalGameRound() throws Exception {
        String gamePlayJson = "{\n" +
                "  \"coins\": 10,\n" +
                "  \"gameType\": 1 \n" +
                "}";

        mockMvc.perform(post("/rest/gamePlayController/normalGameRound")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gamePlayJson))
                .andExpect(status().isOk());
    }

    private ResponseEntity<ResponseObject> createResultMock() {
        ResponseObject responseObject;
        GamePlay gamePlay = new GamePlay();
        gamePlay.setCoins(COINS);
        gamePlay.setGameType(GAME_TYPE);
        responseObject = new ResponseObject("Success!", true, gamePlay);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
}