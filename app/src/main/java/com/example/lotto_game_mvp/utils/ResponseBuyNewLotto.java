package com.example.lotto_game_mvp.utils;

public class ResponseBuyNewLotto {
    int resultCode;
    long userPoint;

    public ResponseBuyNewLotto(int resultCode, long point ){
        this.resultCode = resultCode;
        this.userPoint = point;
    }

    public int getResultCode() {
        return resultCode;
    }

    public long getUserPoint() {
        return userPoint;
    }
}
