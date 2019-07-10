package com.sahan.netent.controller;

import com.sahan.netent.model.GamePlay;
import com.sahan.netent.service.GamePlayService;
import com.sahan.netent.utility.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/gamePlayController")
public class GamePlayController {
    private final String APPLICATION_JSON = "application/json";

    private final GamePlayService gamePlayService;

    @Autowired
    public GamePlayController(GamePlayService gamePlayService) {
        this.gamePlayService = gamePlayService;
    }

    @RequestMapping(value = "/normalGameRound", method = RequestMethod.POST, produces = {APPLICATION_JSON})
    public ResponseEntity<ResponseObject> playNormalGameRound(@RequestBody GamePlay gamePlay) {
        return gamePlayService.playNormalGameRound(gamePlay);
    }
}
