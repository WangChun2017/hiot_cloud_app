package com.huatec.hiot_cloud.test.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.test.mvptest.model.User;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRetrofitActivity extends AppCompatActivity {

    public static final String TAG = "TestRetrofitActivity";
    private TestRetrofitService service;
    private Retrofit retrofit;
    private EditText etToken;
    private Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);

        //取到edit_tok
        etToken = findViewById(R.id.et_token_retrofit);

        //创建Retrofit
        createRetrofit();

        //百度
        Button btnTest = findViewById(R.id.btn_retrofit_enqueue);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });

        //登录
        Button btnLogin = findViewById(R.id.btn_retrofit_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login("wangchun", "abc123", "app");
            }
        });

        //用户信息
        Button btnUserInfo = findViewById(R.id.btn_retrofit_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserInfo("ef583d8bc9da49c8884b620ba4e4d6c4_928a965167454c889a9639ce9f31baeb_use");
            }
        });

        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_retrofit_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmail("", "");
            }
        });
        //注册
        Button btnRegister = findViewById(R.id.btn_retrofit_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        UserBean userBean = new UserBean();
        userBean.setUsername("wang");
        userBean.setPassword("abc123");
        userBean.setEmail("123456@qq.com");
        userBean.setUserType("1");
        Call<ResponseBody> call = service.register(userBean);
        callEnqueue(call);
    }

    private void updateEmail(String authorization, String email) {
        Call<ResponseBody> call = service.updateEmail(authorization, email);
        callEnqueue(call);
    }


    private void getUserInfo(String authorization) {
//        Call<ResponseBody> call = service.getUserInfo(authorization);
//        callEnqueueUserInfo(call);
//            callEnqueue(call);
        Call<ResultBase<UserBean>> call = service.getUserInfo2(authorization);
        call.enqueue(new Callback<ResultBase<UserBean>>() {
            @Override
            public void onResponse(Call<ResultBase<UserBean>> call, Response<ResultBase<UserBean>> response) {
                ResultBase<UserBean> resultBase = response.body();
                if(resultBase != null && resultBase.getData() != null){
                    resultBase.getData();
                    String str = resultBase.getData().getUsername() + ","  + resultBase.getData().getEmail();
                    Toast.makeText(TestRetrofitActivity.this, str, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResultBase<UserBean>> call, Throwable t) {

            }
        });
    }

    private void login(String userName, String password, String loginCode) {
        Call<ResponseBody> call = service.login(userName, password, loginCode);
        callEnqueueLogin(call);
//            callEnqueue(call);
    }

    private void test() {
        Call<ResponseBody> call = service.test();
        callEnqueue(call);
    }

    private void callEnqueue(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    private void callEnqueueLogin(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Type type = new TypeToken<ResultBase<LoginResultDTO>>() {
                }.getType();
                try {
//                    Log.d(TAG, "onResponse: " + response.body().string());
                    ResultBase<LoginResultDTO> loginResult = gson.fromJson(response.body().string(), type);

                    if (loginResult != null && loginResult.getData() != null) {
                        String token = loginResult.getData().getTokenValue();
                        etToken.setText(token);
                    }
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    private void createRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl(TestRetrofitService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(TestRetrofitService.class);
    }

    private void callEnqueueUserInfo(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
//                    Log.d(TAG, "onResponse: " + response.body().string());
                    gson = new Gson();
                    Type type = new TypeToken<ResultBase<UserBean>>() {
                    }.getType();
                    ResultBase<UserBean> resultBase = gson.fromJson(response.body().string(), type);
                    if (resultBase != null && resultBase.getData() != null) {
                        UserBean userBean = resultBase.getData();
                        String str = String.format("用户名: %s , 密码: %s ,emial: %s, 用户类型: %s",
                                userBean.getUsername(), userBean.getPassword(), userBean.getEmail(), userBean.getUserType());
                        Toast.makeText(TestRetrofitActivity.this, str, Toast.LENGTH_SHORT).show();
                    }
                    if (resultBase != null && resultBase.getMsg() != null) {
                        Toast.makeText(TestRetrofitActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }
}
