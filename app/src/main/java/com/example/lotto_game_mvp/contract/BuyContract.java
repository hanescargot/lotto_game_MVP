package com.example.lotto_game_mvp.contract;

import java.util.ArrayList;

public interface BuyContract {
    interface View  {
//        void show(Ticket ticket);//보여줄 것 없음
        void resetCheckedNum(String toastMsg);

    }

    interface Presenter {
        void onBuyButtonClick(ArrayList<Integer> ticketNum);
    }

    interface Model {
        boolean sendBuyTicket(ArrayList<Integer> ticketNum);
    }
}
