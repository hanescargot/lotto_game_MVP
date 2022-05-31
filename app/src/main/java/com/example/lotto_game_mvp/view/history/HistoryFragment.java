package com.example.lotto_game_mvp.view.history;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.lotto_game_mvp.R;
import com.example.lotto_game_mvp.adapters.AdapterBoughtTickets;
import com.example.lotto_game_mvp.contract.HistoryContract;
import com.example.lotto_game_mvp.model.HistoryModel;
import com.example.lotto_game_mvp.presenter.HistoryPresenter;
import com.example.lotto_game_mvp.utils.Auction;
import com.example.lotto_game_mvp.utils.Lotto;
import com.example.lotto_game_mvp.utils.WinNumberDto;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HistoryFragment extends Fragment implements HistoryContract.View {
    HistoryContract.Model model;
    HistoryContract.Presenter presenter;

    TextView[] tvBallNum = new TextView[7];
    TextView tvDrwNo, tvNoTickets;
    TextView tvTimer;
    View resultBalls;
    AdapterBoughtTickets adapterHistory;

    RequestQueue requestQueue;
    String[] key = {"drwtNo1", "drwtNo2", "drwtNo3", "drwtNo4", "drwtNo5", "drwtNo6"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new HistoryModel(getContext()); // model 과 presenter 연결
        presenter = new HistoryPresenter(this, model);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_histroy, container, false);
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for(int i=0; i<tvBallNum.length; i++){
            // 당첨번호 tv binding
            tvBallNum[i] = view.findViewById(R.id.tv_ball_1+i);
        }
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        tvTimer = view.findViewById(R.id.tv_timer); // 당첨번호가 없는 경우 다음 당첨 결과까지의 시간
        resultBalls = view.findViewById(R.id.result_balls); // 당첨번호 공 Set
        tvNoTickets = view.findViewById(R.id.tv_no_tickets);  // not bought history
        adapterHistory = new AdapterBoughtTickets(getActivity(), tvNoTickets); // 사용자가 구매한 티켓 목록 recyclerView
        //SharedPref.getData("week_bought_tickets", )
//        DeviceFile.adapterHistory  = adapterHistory;  // ??? 왜 저장해둠??
        recyclerView.setAdapter(adapterHistory);

        tvDrwNo = view.findViewById(R.id.drw_no);
        tvDrwNo.setText(Lotto.selectedDrwNo+" 회");

        presenter.onChangeWinNumbers(Lotto.selectedDrwNo); // todo : 최신 회차로 Setting

        ImageButton btnLeft = view.findViewById(R.id.btn_left);
        ImageButton btnRight = view.findViewById(R.id.btn_right);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lotto.selectedDrwNo -= 1;
                tvDrwNo.setText(Lotto.selectedDrwNo+" 회");
                presenter.onChangeWinNumbers(Lotto.selectedDrwNo);
                if(  Lotto.selectedDrwNo != Lotto.getThisWeekDrwNo()) btnRight.setVisibility(View.VISIBLE);
                if( Lotto.selectedDrwNo == 1 )btnLeft.setVisibility(View.INVISIBLE);
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lotto.selectedDrwNo += 1;
                tvDrwNo.setText(Lotto.selectedDrwNo+" 회");
                presenter.onChangeWinNumbers(Lotto.selectedDrwNo);
                if( Lotto.selectedDrwNo == Lotto.getThisWeekDrwNo() )btnRight.setVisibility(View.INVISIBLE);
                if( Lotto.selectedDrwNo != 1 )btnLeft.setVisibility(View.VISIBLE);
            }
        });

        if( Lotto.selectedDrwNo >= Lotto.getThisWeekDrwNo() )btnRight.setVisibility(View.INVISIBLE);
        if( Lotto.selectedDrwNo == 1 )btnLeft.setVisibility(View.INVISIBLE);

        Handler handler = new Handler(){
            public void handleMessage(Message msg) {
                String endTimeString = "2021-10-30 23:59:59";//todo : 다음 회차까지 남은 시간
                tvTimer.setText("당첨 결과까지 "+ Auction.getLeftTime(endTimeString)+" 남음");
            }
        };

        TimerTask task = new TimerTask(){
            public void run(){
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        };
        Timer historyTimer = new Timer();
        historyTimer.scheduleAtFixedRate(task, 0, 1000); //1000ms = 1sec  // ???이게... 뭐한거였더라..
    }


    @Override
    public void setWinNumbers(int drwNo, WinNumberDto windNumber) {
        // 보여 줘야할 당첨 결과 정보가 바뀌었을 때

        // 당첨 결과 ball view setting by 회차
        adapterHistory.notifyDataSetChanged();
        if(drwNo == Lotto.getThisWeekDrwNo()){
            //아직 당첨 결과가 없는 주
            tvTimer.setVisibility(View.VISIBLE);
            resultBalls.setVisibility(View.INVISIBLE);
            return;
        }
        //과거 당첨결과 일 때
        tvTimer.setVisibility(View.INVISIBLE);
        resultBalls.setVisibility(View.VISIBLE);
        ArrayList<Integer> sixNum = windNumber.getSixNum();
        for (int i = 0; i< sixNum.size(); i++){
            int num = sixNum.get(i);
            tvBallNum[i].setText(num+"");
            tvBallNum[i].setBackgroundResource( Lotto.getBgSrc(num) );
        }
    }
}