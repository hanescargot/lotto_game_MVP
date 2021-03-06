package com.example.lotto_game_mvp.utils;

import java.util.ArrayList;

public class UserSelectedTicket {
    // 구매 페이지에 사용자 티켓 상태
    public static ArrayList<Integer> buyNumberPadNumCheckedNum = new ArrayList<>();  // 6개 체크 가능
    public static ArrayList<Integer> researchNumberPadNum = new ArrayList<>();  // 6개 체크 가능

    private static final int COIN = 0 ;
    private static final int AD = 1 ;
    private static final int TIME = 2 ;

    public static int selected = UserSelectedTicket.AD;
//    public static ArrayList<int[]> bought = new ArrayList<>();

    public static String getNumPadText(){
        if(selected == COIN)return "1000 Coin";
        if(selected == AD)return "광고보고 무료티켓 받기";
        if(selected == TIME)return "24시간 1회 무료";
        return "티켓 오류";
    }

}
