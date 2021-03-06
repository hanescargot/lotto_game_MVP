package com.example.lotto_game_mvp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lotto_game_mvp.adapters.AdapterBoughtTickets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DeviceFile {

    public static String FILENAME_WIN_NUM_SET = "win_num_set";

    public static String FILENAME_USER_TICKET_DB_ARRAY = "user_ticket_db_array";
    public static String boughtTicketKey = "week_bought_tickets";

    public static Type TYPE_WIN_NUM_SET = new TypeToken<LottoTicketDTO>(){}.getType();
    public static Type TYPE_USER_TICKET_DB_ARRAY = new TypeToken< ArrayList<UserTicketResultDB> >(){}.getType();


    public static AdapterBoughtTickets adapterHistory = null;

    public static SharedPreferences getSharedPref(Context context, String fileName) {
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }


    //Read
    public static Object getData(Context context, String fileName, String key, Type dataType, Object defValue){
        String stringData = getSharedPref(context, fileName).getString(key, "null_data");
        if(stringData.equals("null_data")){
            return defValue;
        }
        return new Gson().fromJson(stringData, dataType);
    }

    //Update(insert, edit)
    public static void editData(Context context,String fileName, String key, Object data){
        SharedPreferences.Editor editor = getSharedPref(context, fileName).edit();
        String stringData = new Gson().toJson(data);
        editor.putString(key, stringData);
        editor.apply();
    }

    public static void syncData(){
        //todo
    }

//    //Delete
//    public void delData(Context context, String key){
//        SharedPreferences.Editor editor = getSharedPref(context).edit();
//        editor.remove(key).commit();
//    }

}
