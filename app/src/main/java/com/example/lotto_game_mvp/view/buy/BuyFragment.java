package com.example.lotto_game_mvp.view.buy;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.lotto_game_mvp.R;
import com.example.lotto_game_mvp.adapters.NumberPadAdapter;
import com.example.lotto_game_mvp.contract.BuyContract;
import com.example.lotto_game_mvp.contract.MainContract;
import com.example.lotto_game_mvp.model.BuyModel;
import com.example.lotto_game_mvp.utils.UserSelectedTicket;
import com.example.lotto_game_mvp.presenter.BuyPresenter;

import java.util.Random;

public class BuyFragment extends Fragment implements BuyContract.View {
    private BuyContract.Presenter presenter;
    TextView tvNumPadTicketDescription;
    ImageView[] arrayIv;
    ImageView buyBtn;

    GridView gridView;
    NumberPadAdapter gridAdapter;

    public BuyFragment(MainContract.View mainView){
        presenter = new BuyPresenter(this, new BuyModel(), mainView);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buy, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvNoti = view.findViewById(R.id.noti_tv);
        tvNoti.setSelected(true);

        TextView tvTime = view.findViewById(R.id.time_tv); // 몇시간 후에 무료 티켓 구매 가능한지

        buyBtn = view.findViewById(R.id.button);
        //set number check pad
        gridView = view.findViewById(R.id.number_pad_gridview);
        gridAdapter = new NumberPadAdapter(getActivity(), buyBtn);
        gridView.setAdapter(gridAdapter);

        View resetBtn = view.findViewById(R.id.reset);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCheckedNum("리셋 완료");
            }
        });

        View randomBtn = view.findViewById(R.id.random);
        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int newNumCount = UserSelectedTicket.buyNumberPadNumCheckedNum.size();
                if(newNumCount == 6){
                    showToast("이미 모든 번호가 선택되어 있습니다");
                }else{
                    showToast("자동 번호 생성");
                    for (int i=0; i<(6-newNumCount); i++){
                        int newNum = random.nextInt(45)+1;
                        while (UserSelectedTicket.buyNumberPadNumCheckedNum.contains(newNum)){
                            newNum = random.nextInt(45)+1;
                        }
                        UserSelectedTicket.buyNumberPadNumCheckedNum.add( newNum );
                    }
                    gridAdapter.notifyDataSetChanged();
                    buyBtn.setImageResource(R.drawable.clickable_btn_red);
                }

            }
        });

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserSelectedTicket.buyNumberPadNumCheckedNum.size()!=6){ return; }
                presenter.onBuyButtonClick(UserSelectedTicket.buyNumberPadNumCheckedNum); // checkedNum reset 은 presenter 가 해줌
            }
        });

        tvNumPadTicketDescription = view.findViewById(R.id.number_pad_text); // 티켓 종류 설명 Text
        tvNumPadTicketDescription.setText(UserSelectedTicket.getNumPadText());

        arrayIv = new ImageView[]{
                view.findViewById(R.id.buy_coin),
                view.findViewById(R.id.buy_ad),
                view.findViewById(R.id.buy_time)
        };

        for(ImageView iv : arrayIv){
            // 티켓 종류 클릭 리스너 추가
            int ivTagNum = Integer.parseInt(iv.getTag().toString());
            if ( ivTagNum==(UserSelectedTicket.selected) ){
                iv.setBackground( getResources().getDrawable(R.drawable.buy_empty_round_box) );
            }
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(ImageView iv : arrayIv){
                        iv.setBackground( null );
                    }
                    UserSelectedTicket.selected = Integer.parseInt(v.getTag().toString());
                    tvNumPadTicketDescription.setText(UserSelectedTicket.getNumPadText());
                    ((ImageView)v).setBackground( getResources().getDrawable(R.drawable.buy_empty_round_box) );
                }
            });
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public Toast toast;
    public void showToast(String str){
        if(toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(
                getActivity(),
                str,
                Toast.LENGTH_SHORT
        );
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }

    @Override
    public void resetCheckedNum(String toastMsg) {
        showToast(toastMsg);
        UserSelectedTicket.buyNumberPadNumCheckedNum.clear();
        gridAdapter.notifyDataSetChanged();
        buyBtn.setImageResource(R.drawable.ic_red_button_grey);
    }

}