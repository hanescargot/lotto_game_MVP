package com.example.lotto_game_mvp.presenter;

import com.example.lotto_game_mvp.contract.HistoryContract;
import com.example.lotto_game_mvp.utils.Lotto;
import com.example.lotto_game_mvp.utils.LottoTicketDTO;

public class HistoryPresenter implements HistoryContract.Presenter {
    public HistoryContract.View view = null;
    public HistoryContract.Model model = null;

    public HistoryPresenter(HistoryContract.View view, HistoryContract.Model model){
        this.view = view;
        this.model = model;
    }
    @Override
    public void onChangeWinNumbers(int drwNo) {
       model.setWinNumbers(drwNo, view);
    }

    // view : set
    // presenter : onChange
    // model : get
    // 이래도 되나 ?
}
