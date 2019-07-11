package com.sahan.netent.service.serviceImpl;

import com.sahan.netent.model.GamePlay;
import com.sahan.netent.model.PreviousResult;
import com.sahan.netent.repository.PreviousResultRepository;
import com.sahan.netent.service.GamePlayService;
import com.sahan.netent.utility.GameType;
import com.sahan.netent.utility.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GamePlayServiceImpl implements GamePlayService {

    private final PreviousResultRepository previousResultRepository;

    @Autowired
    public GamePlayServiceImpl(PreviousResultRepository previousResultRepository) {
        this.previousResultRepository = previousResultRepository;
    }


    @Override
    public ResponseEntity<ResponseObject> playNormalGameRound(GamePlay gamePlay) {
        ResponseObject responseObject;

        if (gamePlay.getGameType() == GameType.NORMAL.getGameTypeId() && (gamePlay.getCoins() == 10)) {
            int winningCoins = coinWinningChance();
            int winningFreeRound = freeRoundWinningChance();
            int randomNumber = (int) (Math.random());

            if (winningFreeRound != 0) {
                GamePlay newWinningPlay = new GamePlay();
                newWinningPlay.setCoins(0);
                newWinningPlay.setGameType(GameType.NORMAL.getGameTypeId());
                playNormalGameRound(newWinningPlay);
            }

            PreviousResult previousResult = new PreviousResult();
            previousResult.setAmount(winningCoins);
            PreviousResult lastRecordFromDb = previousResultRepository.findTopByOrderByUniqueIdDesc();
            if (lastRecordFromDb == null) {
                previousResult.setUniqueId(1 + randomNumber);
            } else {
                previousResult.setUniqueId(lastRecordFromDb.getId() + randomNumber);
            }

            previousResult.setCreatedDate(new Date());
            PreviousResult saveObject = this.previousResultRepository.save(previousResult);
            responseObject = new ResponseObject("Success!", true, saveObject);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            responseObject = new ResponseObject("Invalid Input!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private int coinWinningChance() {
        int generatedNo = randomNoGenerator();
        int earnCoins = 0;
        if (generatedNo < 30) {
            earnCoins += 20;
        }
        return earnCoins;
    }

    private int freeRoundWinningChance() {
        int generatedNo = randomNoGenerator();
        int freeRound = 0;
        if (generatedNo < 10) {
            freeRound += 1;
        }
        return freeRound;
    }

    private int randomNoGenerator() {
        int randomNumber = (int) (Math.random());
        int generatedNo = randomNumber % 100;
        return generatedNo;
    }
}
