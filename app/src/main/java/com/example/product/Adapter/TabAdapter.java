package com.example.product.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.product.Dashboard.AudioTabFragment;
import com.example.product.Dashboard.MovieTabFragment;
import com.example.product.Dashboard.MusicTabFragment;

public class TabAdapter extends FragmentPagerAdapter {


    Context context;
    int totalTabs;

    public TabAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MovieTabFragment tab1Fragment = new MovieTabFragment();
                return tab1Fragment;
            case 1:
                MusicTabFragment tab2Fragment = new MusicTabFragment();
                return tab2Fragment;
            case 2:
                AudioTabFragment tab3Fragment = new AudioTabFragment();
                return tab3Fragment;
            default:
                return null;
        }


    }


    @Override
    public int getCount() {
        return totalTabs;
    }
}
