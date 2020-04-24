package com.huatec.hiot_cloud.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.huatec.hiot_cloud.utils.Constans;

class MainViewPagerAdapter extends FragmentPagerAdapter {
    public MainViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public MainViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case Constans.MAIN_VIEWPAGER_INDEX_MESAAGE:
                //创建messageFrament todo

                break;
            case Constans.MAIN_VIEWPAGER_INDEX_EQUIPMENT:
                break;
            case Constans.MAIN_VIEWPAGER_INDEX_SCENE:
                break;
            case Constans.MAIN_VIEWPAGER_INDEX_MINE:
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return Constans.MAIN_FRAGMENT_COUNT;
    }
}
