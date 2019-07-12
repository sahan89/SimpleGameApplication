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
    private int freeRound = 0;
    private int winningCoins = 0;
    private int generatedNo = 0;

    @Autowired
    public GamePlayServiceImpl(PreviousResultRepository previousResultRepository) {
        this.previousResultRepository = previousResultRepository;
    }

    @Override
    public ResponseEntity<ResponseObject> playNormalGameRound(GamePlay gamePlay) {
        ResponseObject responseObject;
        if ((gamePlay.getGameType() == GameType.NORMAL.getGameTypeId() && gamePlay.getCoins() == 10) && freeRound == 0) {
            coinWinningChance();
            int winRound = freeRoundWinningChance();
            if (winRound == 0) {
                PreviousResult previousResult = new PreviousResult();
                previousResult.setAmount(winningCoins);
                PreviousResult lastRecordFromDb = previousResultRepository.findTopByOrderByUniqueIdDesc();
                if (lastRecordFromDb == null) {
                    previousResult.setUniqueId(1);
                } else {
                    previousResult.setUniqueId(lastRecordFromDb.getId() + 1);
                }
                previousResult.setCreatedDate(new Date());
                PreviousResult saveObject = this.previousResultRepository.save(previousResult);
                responseObject = new ResponseObject("Success! " + ((freeRound == 0) ? "No Free round!" : "You won the Free round!"), true, saveObject);
                return new ResponseEntity<>(responseObject, HttpStatus.OK);
            } else {
                responseObject = new ResponseObject(("Success! You won the Free round!"), true, null);
                return new ResponseEntity<>(responseObject, HttpStatus.OK);
            }
        } else if (gamePlay.getGameType() == GameType.FREE.getGameTypeId() && gamePlay.getCoins() == 0 && freeRound == 1) {
            freeRound = 0;
            winFreeRound();
        } else if (freeRound == 1) {
            responseObject = new ResponseObject("Please use your free round first!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.NOT_ACCEPTABLE);
        } else if (freeRound == 0) {
            responseObject = new ResponseObject("Not available free round!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.NOT_ACCEPTABLE);
        } else {
            responseObject = new ResponseObject("Invalid Input!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
        responseObject = new ResponseObject("Success!", true, null);
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }

    private void winFreeRound() {
        GamePlay newWinningPlay = new GamePlay();
        newWinningPlay.setCoins(10);
        newWinningPlay.setGameType(1);
        playNormalGameRound(newWinningPlay);
    }

    private void coinWinningChance() {
        generatedNo = randomNoGenerator();
        if (generatedNo < 30) {
            winningCoins =+ 20;
        } else {
            winningCoins = 0;
        }
    }

    private int freeRoundWinningChance() {
        generatedNo = randomNoGenerator();
        freeRound = 0;
        if (generatedNo < 10) {
            freeRound += 1;
        }
        return freeRound;
    }

    private int randomNoGenerator() {
        int randomNumber = (int) (Math.random() * 10000);
        generatedNo = randomNumber % 100;
        return generatedNo;
    }
}
