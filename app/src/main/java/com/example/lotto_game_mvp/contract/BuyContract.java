package com.example.lotto_game_mvp.contract;

import com.example.lotto_game_mvp.presenter.BuyPresenter;
import com.example.lotto_game_mvp.utils.Ticket;

public interface BuyContract {
    interface View extends BaseView<Presenter> {
        public void loadToast(String msg);
        public void sendBuyTicket(String msg);
    }
    interface Presenter extends BasePresenter{
    //commit test
    }
}

interface BasePresenter {
    public void send();
}


interface BaseView<T> {
    T presenter;
}

