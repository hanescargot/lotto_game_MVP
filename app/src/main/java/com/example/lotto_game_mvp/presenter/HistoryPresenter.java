package com.example.lotto_game_mvp.presenter;

import android.util.Log;

import com.example.lotto_game_mvp.contract.HistoryContract;
import com.example.lotto_game_mvp.utils.WinNumberDto;
import com.google.gson.Gson;

public class HistoryPresenter implements HistoryContract.Presenter {
    public HistoryContract.View view = null;
    public HistoryContract.Model model = null;

    public HistoryPresenter(HistoryContract.View view, HistoryContract.Model model){
        this.view = view;
        this.model = model;
    }
    @Override
    public void onChangeWinNumbers(int drwNo) {
        model.getWinNumbers(drwNo, view);
    }

    // view : set
    // presenter : onChange
    // model : get
    // 이래도 되나 ?
}
