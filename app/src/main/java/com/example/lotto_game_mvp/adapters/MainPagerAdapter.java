package com.example.lotto_game_mvp.adapters;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lotto_game_mvp.contract.MainContract;
import com.example.lotto_game_mvp.utils.Auction;
import com.example.lotto_game_mvp.view.auction.AuctionFragment;
import com.example.lotto_game_mvp.view.buy.BuyFragment;
import com.example.lotto_game_mvp.view.history.HistoryFragment;
import com.example.lotto_game_mvp.view.research.ResearchFragment;


public class MainPagerAdapter extends FragmentStateAdapter {

    androidx.fragment.app.Fragment[] fragments = new androidx.fragment.app.Fragment[4];

    public MainPagerAdapter(FragmentActivity fragmentActivity){
        super(fragmentActivity);

        fragments[0] = new BuyFragment((MainContract.View)fragmentActivity);
        fragments[1] = new HistoryFragment();
        fragments[2] = new AuctionFragment();
        fragments[3] = new ResearchFragment();

    }
    @Override
    public androidx.fragment.app.Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }

}
