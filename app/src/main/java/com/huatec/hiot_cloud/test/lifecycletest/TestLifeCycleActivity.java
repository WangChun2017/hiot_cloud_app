package com.huatec.hiot_cloud.test.lifecycletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.test.mvptest.TestMVPActivity;

public class TestLifeCycleActivity extends AppCompatActivity {

    private static final String TAG = "TestLifeCycleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_life_cycle);
        Button btnStartNew = findViewById(R.id.btn_start_new);
        btnStartNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestLifeCycleActivity.this, TestMVPActivity.class);
                startActivity(intent);
            }
        });

        Button btnFinish = findViewById(R.id.btn_finish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    @Override
    protected void onStart() {
        Log.d(TAG,"start");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"stop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"destroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d(TAG,"pause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG,"resume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG,"restart");
        super.onRestart();
    }
}
