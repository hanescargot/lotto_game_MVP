package com.example.lotto_game_mvp.contract;

import com.example.lotto_game_mvp.presenter.BuyPresenter;
import com.example.lotto_game_mvp.utils.Ticket;

import java.util.ArrayList;

public interface BuyContract {
    interface View  {
//        void show(Ticket ticket);//보여줄 것 없음
        void resetCheckedNum();

    }

    interface Presenter {
        void onBuyButtonClick(ArrayList<Integer> ticketNum);
    }

    interface Model {
        void sendBuyTicket(ArrayList<Integer> ticketNum);
    }
}
