package com.huatec.hiot_cloud.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.base.BaseActivity;
import com.huatec.hiot_cloud.base.BasePresenter;
import com.huatec.hiot_cloud.utils.Constans;

//实验2.1
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewPager vpMain = findViewById(R.id.vp_main);
        vpMain.setAdapter(new MainViewPagerAdapter());
        vpMain.setOffscreenPageLimit(Constans.MAIN_FRAGMENT_COUNT);
        RadioGroup rgMain = findViewById(R.id.rg_main);
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_message:
                        vpMain.setCurrentItem(Constans.MAIN_VIEWPAGER_INDEX_MESAAGE);
                        break;
                    case R.id.rb_equipment:
                        vpMain.setCurrentItem(Constans.MAIN_VIEWPAGER_INDEX_EQUIPMENT);
                        break;
                    case R.id.rb_scene:
                        vpMain.setCurrentItem(Constans.MAIN_VIEWPAGER_INDEX_SCENE);
                        break;
                    case R.id.rb_mine:
                        vpMain.setCurrentItem(Constans.MAIN_VIEWPAGER_INDEX_MINE);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }
}
