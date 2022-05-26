package com.example.lotto_game_mvp.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lotto_game_mvp.contract.HistoryContract;
import com.example.lotto_game_mvp.utils.SixNumDto;
import com.example.lotto_game_mvp.utils.WinNumberDto;
import com.google.gson.Gson;

import java.util.HashMap;

public class HistoryModel implements HistoryContract.Model {
    Context context;
    public HistoryModel(Context context){
        this.context = context;
    }

    @Override
    public WinNumberDto getWinNumbers(int drwNo) {
        WinNumberDto winNumSet = new WinNumberDto();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String[] key = {"drwtNo1", "drwtNo2", "drwtNo3", "drwtNo4", "drwtNo5", "drwtNo6"};
        String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo="+drwNo;

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        HashMap<String, Double> data = new Gson().fromJson(response, HashMap.class);
                        if (data.isEmpty()) {
                            Log.i("Error", "API Error: Data is Empty");
                            return; //Error
                        }
                        SixNumDto numbers = new SixNumDto();
                        for (String keyString : key) {
                            //당첨 번호 추가
                            int num = data.get(keyString).intValue();
                            numbers.setNum(numbers.len(),num);
                        }
                        int bNum = data.get("bnusNo").intValue(); // 보너스 넘버
                        winNumSet.setbNum(bNum);
                        winNumSet.setNumbers(numbers);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error", "API Error: Error Response");
                    }
                }
        );

        return  winNumSet;
    }



}
