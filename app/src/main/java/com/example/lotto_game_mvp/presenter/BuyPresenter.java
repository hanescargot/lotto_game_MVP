package com.example.lotto_game_mvp.presenter;

import com.example.lotto_game_mvp.contract.BuyContract;
import com.example.lotto_game_mvp.model.BuyModel;

public class BuyPresenter implements BuyContract.Presenter {

    public BuyPresenter(){

    }

    @Override
    public void clickButton() {
        model.sendBuyTicket();
    }

    @Override
    public void bindView(){

    }

}