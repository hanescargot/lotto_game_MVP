package com.example.lotto_game_mvp.contract;

import com.example.lotto_game_mvp.utils.SixNumDto;
import com.example.lotto_game_mvp.utils.WinNumberDto;

public interface HistoryContract {
    interface View{
        void setWinNumbers(int drwNo, WinNumberDto winNumberDto);
    }
    interface Presenter{
        void onChangeWinNumbers(int drwNo);
    }
    interface Model{
        WinNumberDto getWinNumbers(int drwNo);
    }
}
