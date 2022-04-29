package com.example.lotto_game_mvp.contract;

public interface BuyContract {
    interface View {
        public void showText();
    }

    interface Presenter {
        public void clickButton();
        public void bindView();
    }
}
