package com.example.lotto_game_mvp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lotto_game_mvp.R;
import com.example.lotto_game_mvp.utils.Lotto;
import com.example.lotto_game_mvp.utils.LottoTicketDTO;


import java.util.ArrayList;

public class AdapterBoughtTickets extends RecyclerView.Adapter {
    // 사용자가 회차에 구입한 티켓 목록 RecyclerView
    Context context;
    TextView tvNoTickets;
    LayoutInflater inflater;
    Boolean isHistory;
    ArrayList<LottoTicketDTO> userHistoryTickets = new ArrayList<>();

    public AdapterBoughtTickets(Context context, TextView tvNoTickets, ArrayList<LottoTicketDTO> userHistoryTickets){
        this.context = context;
        this.tvNoTickets = tvNoTickets;
        this.userHistoryTickets = userHistoryTickets;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.component_ticket_nums_item, parent, false);
        return new ViewHolder( itemView );
    }

    @Override
    public int getItemCount() {
        this.isHistory = !(Lotto.selectedDrwNo == Lotto.getNextWeekDrwNo()); // todo error null exception

        // 구매하신 티켓이 없습니다 메시지 배경
        if(userHistoryTickets.size()==0){tvNoTickets.setVisibility(View.VISIBLE);
        }else {tvNoTickets.setVisibility(View.INVISIBLE);}

        return userHistoryTickets.size();

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        LottoTicketDTO ticketDTO = userHistoryTickets.get(position);
        ArrayList<Integer> sixNum = ticketDTO.getSixNum();
        for(int i = 0; i< sixNum.size(); i++){
            TextView tvBall = viewHolder.getBallTextViews()[i];
            tvBall.setText(sixNum.get(i));
            tvBall.setBackgroundResource(Lotto.getBgSrc( sixNum.get(i) ));
        }
        if(isHistory){
            //당첨결과 발표된 회차의 사용자 티켓
            //todo : 사용자의 당첨 등수 계산하기
            int result = 1;
            viewHolder.tvResult.setText(result);
        }else{
            //아직 당첨결과 발표되지 않은 회차
            viewHolder.tvResult.setText("대기중");

        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView[] tvBalls = new TextView[6];
        TextView tvResult;
        public ViewHolder(View itemView) {
            super(itemView);
            for(int i=0; i<6; i++){
                tvBalls[i] = itemView.findViewById(R.id.tv_ball_1+i);
            }
            tvResult = itemView.findViewById(R.id.tv_result);
        }
        public TextView[] getBallTextViews() {
            return tvBalls;
        }
    }
}
