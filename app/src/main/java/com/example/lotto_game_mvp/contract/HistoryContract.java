package com.example.lotto_game_mvp.contract;

import com.example.lotto_game_mvp.utils.LottoTicketDTO;

import java.util.ArrayList;

public interface HistoryContract {
    interface View{
        void setWinNumbers(int drwNo, LottoTicketDTO lottoTicketDTO);
    }
    interface Presenter{
        void onChangeWinNumbers(int drwNo);
    }
    interface Model{
        void setWinNumbers(int drwNo , HistoryContract.View view);
        ArrayList<LottoTicketDTO> getUserBoughtTickets(int drwNo);
    }
}
