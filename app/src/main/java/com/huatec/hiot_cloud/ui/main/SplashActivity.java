package com.huatec.hiot_cloud.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.huatec.hiot_cloud.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private static final int HANDLER_MSG_OPENNEW = 1;
    private Handler handler = new Handler(){
    @Override
    public void handleMessage(@NonNull Message msg) {

        super.handleMessage(msg);
        if (msg.what == HANDLER_MSG_OPENNEW){
            //打开界面
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(HANDLER_MSG_OPENNEW);
            }
        },3000);
    }
}
