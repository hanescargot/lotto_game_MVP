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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.lotto_game_mvp.R;
import com.example.lotto_game_mvp.adapters.NumberPadAdapter;
import com.example.lotto_game_mvp.contract.BuyContract;
import com.example.lotto_game_mvp.utils.DeviceFile;
import com.example.lotto_game_mvp.utils.SixNum;
import com.example.lotto_game_mvp.utils.Ticket;
import com.example.lotto_game_mvp.utils.UserTicketDAO;
import com.example.lotto_game_mvp.utils.UserTicketResultDB;
import com.example.lotto_game_mvp.databinding.FragmentDashboardBinding;
import com.example.lotto_game_mvp.presenter.BuyPresenter;

import java.util.Random;

public class BuyFragment extends Fragment implements BuyContract.View {
    private BuyPresenter presenter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView tvNumPad;
    ImageView[] arrayIv;
    ImageView buyBtn;

    GridView gridView;
    NumberPadAdapter gridAdapter;

    @Override
    public void showText() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BuyPresenter();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            Toast.makeText(getActivity(), ""+mParam1+mParam2, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buy, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvNoti = view.findViewById(R.id.noti_tv);
        tvNoti.setSelected(true);

        TextView tvTime = view.findViewById(R.id.time_tv); //티켓 없으면  Adapter 대신 보여주기

        buyBtn = view.findViewById(R.id.button);
        //set number check pad
        gridView = view.findViewById(R.id.number_pad_gridview);
        gridAdapter = new NumberPadAdapter(getActivity(), buyBtn);
        gridView.setAdapter(gridAdapter);

        View resetBtn = view.findViewById(R.id.reset);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("초기화 완료");
                Ticket.buyNumberPadNum.clear();

                gridAdapter.notifyDataSetChanged();
                buyBtn.setImageResource(R.drawable.ic_red_button_grey);
            }
        });

        View randomBtn = view.findViewById(R.id.random);
        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int newNumCount = Ticket.buyNumberPadNum.size();
                if(newNumCount == 6){
                    showToast("이미 모든 번호가 선택되어 있습니다");
                }else{
                    showToast("자동 번호 생성");
                    for (int i=0; i<(6-newNumCount); i++){
                        int newNum = random.nextInt(45)+1;
                        while (Ticket.buyNumberPadNum.contains(newNum)){
                            newNum = random.nextInt(45)+1;
                        }
                        Ticket.buyNumberPadNum.add( newNum );
                    }
                    gridAdapter.notifyDataSetChanged();
                    buyBtn.setImageResource(R.drawable.clickable_btn_red);
                }

            }
        });

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Ticket.buyNumberPadNum.size()!=6){ return; }
                UserTicketResultDB newNumbers = new UserTicketResultDB(getActivity(), new SixNum(Ticket.buyNumberPadNum));
                UserTicketDAO.addNewTicket( getActivity(),  newNumbers );
                if(DeviceFile.adapterHistory != null){
                    DeviceFile.adapterHistory.notifyDataSetChanged();
                }
//                todo 서버로 보내기
                    presenter.clickButton();
//                가격 계산하기
//                롤백 기능
                showToast("구매 완료");
                Ticket.buyNumberPadNum.clear();
                gridAdapter.notifyDataSetChanged();
                buyBtn.setImageResource(R.drawable.ic_red_button_grey);
//
            }
        });

        tvNumPad = view.findViewById(R.id.number_pad_text);
        tvNumPad.setText(Ticket.getNumPadText());

        arrayIv = new ImageView[]{
                view.findViewById(R.id.buy_coin),
                view.findViewById(R.id.buy_ad),
                view.findViewById(R.id.buy_time)
        };
        for(ImageView iv : arrayIv){
            int ivTagNum = Integer.parseInt(iv.getTag().toString());
            if ( ivTagNum==(Ticket.selected) ){
                iv.setBackground( getResources().getDrawable(R.drawable.buy_empty_round_box) );
            }
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(ImageView iv : arrayIv){
//                        iv.setImageTintList( ColorStateList.valueOf(Color.parseColor("#777777")) );
//                        iv.setBackground( getResources().getDrawable(R.drawable.buy_round_box) );
                        iv.setBackground( null );
                    }
                    Ticket.selected = Integer.parseInt(v.getTag().toString());
                    tvNumPad.setText(Ticket.getNumPadText());
//                    ((ImageView)v).setImageTintList( null );
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
}