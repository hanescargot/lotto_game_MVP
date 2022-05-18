package com.example.lotto_game_mvp.presenter;

import com.example.lotto_game_mvp.contract.BuyContract;
import com.example.lotto_game_mvp.model.BuyModel;
import com.example.lotto_game_mvp.utils.Ticket;
import com.example.lotto_game_mvp.view.buy.BuyFragment;

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
       model.sendBuyTicket(ticketNum);
       view.resetCheckedNum();
    }

}