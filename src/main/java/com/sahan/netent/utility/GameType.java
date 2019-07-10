package com.sahan.netent.utility;

public enum GameType {
    FREE("Free", 0),
    NORMAL("Normal", 1);

    private String gameType;
    private Integer gameTypeId;

    GameType(String gameType, int gameTypeId) {
        this.gameType = gameType;
        this.gameTypeId = gameTypeId;
    }

    public String getGameType() {
        return gameType;
    }

    public Integer getGameTypeId() {
        return gameTypeId;
    }
}
