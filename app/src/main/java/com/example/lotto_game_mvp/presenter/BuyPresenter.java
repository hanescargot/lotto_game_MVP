package com.example.lotto_game_mvp.presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class BuyPresenter implements AccountContract.Presenter{

    public MutableLiveData<String> mText;

    public BuyPresenter() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}