package com.example.lotto_game_mvp.adapters;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lotto_game_mvp.view.buy.BuyFragment;
import com.example.lotto_game_mvp.view.research.ResearchFragment;


public class MainPagerAdapter extends FragmentStateAdapter {

    androidx.fragment.app.Fragment[] fragments = new androidx.fragment.app.Fragment[4];

    public MainPagerAdapter(FragmentActivity fragmentActivity){
        super(fragmentActivity);

        fragments[0] = new BuyFragment();
        fragments[1] = new BuyFragment();
        fragments[2] = new BuyFragment();
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
