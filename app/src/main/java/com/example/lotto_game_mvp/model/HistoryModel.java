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
import com.example.lotto_game_mvp.utils.LottoTicketDTO;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class HistoryModel implements HistoryContract.Model {
    Context context;
    HistoryContract.Presenter presenter;
    public HistoryModel(Context context){
        this.context = context;
    }

    @Override
     public void setWinNumbers(int drwNo, HistoryContract.View view) {
        LottoTicketDTO winNumSet = new LottoTicketDTO();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String[] key = {"drwtNo1", "drwtNo2", "drwtNo3", "drwtNo4", "drwtNo5", "drwtNo6"};
        String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo="+drwNo;
        Log.i("Model", "START" );

        //todo 스레드 처리

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("fafasfaf", response);

                        HashMap<String, Object> data = new Gson().fromJson(response, HashMap.class);
                        if (data.isEmpty()) {
                            Log.i("Error", "API Error: Data is Empty");
                            return; //Error
                        } else if(data.get("returnValue") != null && data.get("returnValue").toString().equals("fail")){
                            Log.i("Error", "API Error: RetrunValue Fail");
                            return; //Error
                        }
                        ArrayList<Integer> sixNum = new ArrayList<>();
                        double tempNum ;
                        for (String keyString : key) {
                            //당첨 번호 추가
                            Log.e("tesstet", data.get(keyString).toString());

                            int num = (int)(double)(data.get(keyString));
                            sixNum.add(num);
                        }
                        tempNum = (double)data.get("bnusNo");
                        int bNum = (int)tempNum;  // 보너스 넘버
                        winNumSet.setbNum(bNum);
                        winNumSet.setNumbers(sixNum);
                        Log.i("Model", new Gson().toJson(winNumSet) );
                        Log.i("Model", response );
                        view.setWinNumbers(drwNo, winNumSet);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error", "API Error: Error Response");
                        Log.i("Error", error.toString());
                    }
                }
        );
        requestQueue.add(request);
        Log.i("Model", "END" );
    }

    @Override
    public ArrayList<LottoTicketDTO> getUserBoughtTickets(int drwNo) {
        // todo 사용자가 구매한 티켓 리스트
        ArrayList<LottoTicketDTO> userTicketHistoryList = new ArrayList<>();

        return userTicketHistoryList;
    }
}
