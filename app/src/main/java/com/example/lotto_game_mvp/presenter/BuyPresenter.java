package com.example.lotto_game_mvp.presenter;

import com.example.lotto_game_mvp.contract.BuyContract;

import java.util.ArrayList;

public class BuyPresenter implements BuyContract.Presenter {
    private BuyContract.View view = null;
    private BuyContract.Model model = null;

    public BuyPresenter(BuyContract.View view, BuyContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onBuyButtonClick(ArrayList<Integer> ticketNum) {
        //구매 버튼 눌렀을 때
        if( model.sendBuyTicket(ticketNum)){
            view.resetCheckedNum("구매 완료");
        };

    }

}