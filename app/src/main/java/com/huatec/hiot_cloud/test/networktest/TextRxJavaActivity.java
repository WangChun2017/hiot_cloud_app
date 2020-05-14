package com.huatec.hiot_cloud.test.networktest;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.data.NetworkService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TextRxJavaActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private NetworkService service;
    private final static String TAG = "TextRxJavaActivity";
    private EditText etToken;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_rx_java);


        //editText
        etToken = findViewById(R.id.et_rxjava_token);
//        etToken.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                login("wangchun", "abc123");
//            }
//        });
        //创建retrofit
        createRetrofit();

        //登录
        Button btnLogin = findViewById(R.id.btn_rxjava_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login("wangchun ", "abc123");
            }
        });

        //用户信息
        Button btnUserInfo = findViewById(R.id.btn_rxjava_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserInfo(etToken.getText().toString());

            }
        });
        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_rxjava_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmail(etToken.getText().toString(), "12315456452@qq.com");
            }
        });
        //注册
        Button btnRegister = findViewById(R.id.btn_rxjava_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserBean userBean = new UserBean();
                userBean.setUsername("liuyi");
                userBean.setPassword("abc123");
                userBean.setEmail("11046345@qq.com");
                userBean.setUserType("1");

               register(userBean);
            }
        });

    }

    /**
     * 注册
     * @param userBean
     */

    private void register(UserBean userBean) {
        service.register(userBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBase<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<UserBean> resultBase) {
                        //第二种
                        if (resultBase != null && resultBase.getData() != null) {
                            //第一种
//                        if (resultBase != null && resultBase.getStatus() == 200 && resultBase.getData() != null) {
                            //第二种
                            UserBean newusererBean = resultBase.getData();
                            String userstr = String.format("用户: %s, 邮箱: %s",
                                    newusererBean.getUsername(), newusererBean.getEmail());
                            Toast.makeText(TextRxJavaActivity.this, userstr, Toast.LENGTH_SHORT).show();
                            //第一种
//                            Toast.makeText(TextRxJavaActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
//                            UserBean userBean = resultBase.getData();
//                            String str = String.format("用户: %s, 邮箱: %s",
//                                    userBean.getUsername(), userBean.getEmail());
//                            Toast.makeText(TextRxJavaActivity.this, str, Toast.LENGTH_SHORT).show();
//                            email = userBean.getEmail();
                        } else if (resultBase != null && TextUtils.isEmpty(resultBase.getMsg()) || resultBase.getStatus() != 200) {
                            //第二种
                            Toast.makeText(TextRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
//                          第一种
//                            Toast.makeText(TextRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    /**
     * 修改邮箱
     *
     * @param authorization
     */
    private void updateEmail(String authorization, final String emails) {

        service.updateEmail(authorization, emails)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBase<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<String> resultBase) {
                        if (resultBase != null && !TextUtils.isEmpty(resultBase.getData())) {
                            String newEmail = resultBase.getData();
                            Toast.makeText(TextRxJavaActivity.this, "修改成功，新邮箱" + newEmail, Toast.LENGTH_SHORT).show();

                        } else if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                            Toast.makeText(TextRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 获取用户信息
     *
     * @param authorization
     */
    private void getUserInfo(String authorization) {
        Observable<ResultBase<UserBean>> observable = service.getUserInfo(authorization);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultBase<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<UserBean> resultBase) {
                        if (resultBase != null && resultBase.getData() != null ) {
                            UserBean userBean = resultBase.getData();
                            String str = String.format("用户: %s, 邮箱: %s",
                                    userBean.getUsername(), userBean.getEmail());
                            Toast.makeText(TextRxJavaActivity.this, str, Toast.LENGTH_SHORT).show();
                            email = userBean.getEmail();
                        } else if (resultBase != null && TextUtils.isEmpty(resultBase.getMsg())) {
                            Toast.makeText(TextRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    /**
     * 登录
     *
     * @param userName
     * @param password
     */
    private void login(String userName, String password) {
        service.login(userName, password, "app")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBase<LoginResultDTO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultBase<LoginResultDTO> resultBase) {
                        if (resultBase != null && resultBase.getData() != null) {
                            LoginResultDTO loginResult = resultBase.getData();
                            Log.d(TAG, "onNext: " + loginResult.getTokenValue());
                            etToken.setText(loginResult.getTokenValue());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage(), e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 创建retorfit
     */
    private void createRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl(TestRetrofitService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service = retrofit.create(NetworkService.class);
    }
}
