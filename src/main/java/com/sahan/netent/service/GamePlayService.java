package com.sahan.netent.service;

import com.sahan.netent.model.GamePlay;
import com.sahan.netent.utility.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface GamePlayService {
    ResponseEntity<ResponseObject> playNormalGameRound(GamePlay gamePlay);
}
