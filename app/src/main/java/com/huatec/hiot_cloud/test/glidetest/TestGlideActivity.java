package com.huatec.hiot_cloud.test.glidetest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.utils.ImageUtils;

public class TestGlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_glide);

        final String url = "http://p1.pstatp.com/large/166200019850062839d3";
        final ImageView iv = findViewById(R.id.iv_glide_test);
        Button btnGlideLoad = findViewById(R.id.btn_glide_load_test);
        btnGlideLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(TestGlideActivity.this)
                        .asBitmap()
                        .load(url)
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.error)
//                        .transition(new DrawableTransitionOptions().crossFade(1000))
                        .centerCrop()
                        .into(iv);

            }
        });


        //使用工具类显示
        Button btnUtils = findViewById(R.id.btn_glide_load_util_test);
        btnUtils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageUtils.show(TestGlideActivity.this, iv, url);
            }
        });
    }
}
