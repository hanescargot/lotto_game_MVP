package com.example.lotto_game_mvp.contract;

public interface MainContract {
    interface View{
        void setUserMoney(long money);
    }

    interface Presenter {
        void updateUserMoney(long money);
    }
}
