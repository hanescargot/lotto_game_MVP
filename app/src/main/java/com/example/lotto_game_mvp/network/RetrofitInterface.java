package com.example.lotto_game_mvp.network;

import com.example.lotto_game_mvp.utils.ResponseBuyNewLotto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @POST("/ticket/ad")  // 새로운 스터디 추가
    Call<ResponseBuyNewLotto> sendBuyTicket(@Body() ArrayList<Integer> buyNumberPadNumCheckedNum); // 남은 사용자 포인트를 응답
}
