package com.example.lotto_game_mvp.model;

import com.example.lotto_game_mvp.contract.BuyContract;
import com.example.lotto_game_mvp.network.RetrofitInterface;
import com.example.lotto_game_mvp.utils.ResponseBuyNewLotto;
import com.example.lotto_game_mvp.utils.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuyModel implements BuyContract.Model {

    public ResponseBuyNewLotto sendBuyTicket(ArrayList<Integer> ticket){
        ResponseBuyNewLotto res = new ResponseBuyNewLotto(200,999); // tmp response data
        return res;
        //Retrofit2 http 통신

        //Retrofit 인스턴스 생성
//        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
//                .baseUrl("")    // todo taki baseUrl 등록
//                .addConverterFactory(GsonConverterFactory.create())  // Gson 변환기 등록
//                .build();
//
//        RetrofitInterface service = retrofit.create(RetrofitInterface.class);   // 레트로핏 인터페이스 객체 구현
//        Call<ResponseBuyNewLotto> call = service.sendBuyTicket(ticket);
//        call.enqueue(new Callback<ResponseBuyNewLotto>() {
//            @Override
//            public void onResponse(Call<ResponseBuyNewLotto> call, Response<ResponseBuyNewLotto> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBuyNewLotto> call, Throwable t) {
//
//            }
//        });

    }
}
