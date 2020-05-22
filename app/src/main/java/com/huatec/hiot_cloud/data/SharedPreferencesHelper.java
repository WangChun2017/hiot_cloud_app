package com.huatec.hiot_cloud.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.huatec.hiot_cloud.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * SharedPreferences 类
 */

@Singleton
public class SharedPreferencesHelper {


    private final SharedPreferences mPref;

    private static final String PREF_FILE_NAME = "userconfig";

    private static final String PREF_KEY_USER_TOKEN = "PREF_KEY_USER_TOKEN";

    private Context context;


    /**
     * 构造函数
     *
     * @param context
     */
    @Inject
    public SharedPreferencesHelper(@ApplicationContext Context context) {
        this.context = context;
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 写入用户Token
     *
     * @param value
     */
    public void setUserToken(String value) {
        mPref.edit().putString(PREF_KEY_USER_TOKEN, value).apply();
    }

    /**
     * 读取Token
     *
     * @return
     */
    public String getUserToken() {
        return mPref.getString(PREF_KEY_USER_TOKEN, "");
    }
}
