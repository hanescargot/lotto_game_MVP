package com.example.lotto_game_mvp.presenter;

import com.example.lotto_game_mvp.contract.MainContract;

public class MainPresenter implements MainContract.Presenter {
    static MainContract.View view = null;

    public MainPresenter(MainContract.View view){
        this.view = view;
    }

    @Override
    public void updateUserMoney(long money) {
        view.setUserMoney(money);
    }
}
