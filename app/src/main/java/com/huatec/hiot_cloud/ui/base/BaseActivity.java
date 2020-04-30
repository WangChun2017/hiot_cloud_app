package com.huatec.hiot_cloud.ui.base;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;

import com.huatec.hiot_cloud.App;
import com.huatec.hiot_cloud.injection.component.ActivityComponent;
import com.huatec.hiot_cloud.injection.component.ApplicationComponent;
import com.huatec.hiot_cloud.injection.component.DaggerActivityComponent;
import com.huatec.hiot_cloud.injection.module.ActivityModule;

//实验2.2
public abstract class BaseActivity <V extends BaseView, P extends BasePresenter<V>>extends AppCompatActivity implements BaseView {


    private P presenter;
    /**
     * 活动注入器
     */
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectIndependies();
        presenter = createPresenter();
        if(presenter != null){
            presenter.setView((V)this);
        }

    }
    public abstract P createPresenter();

    public abstract  void injectIndependies();

    @Override
    public void onPrepareSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onPrepareSupportNavigateUpTaskStack(builder);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public ActivityComponent getActivityComponent() {
        if (null == mActivityComponent) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(getActivityModule())
                    .applicationComponent(getApplicationComponent())
                    .build();
        }
        return mActivityComponent;
    }

    public ApplicationComponent getApplicationComponent() {

        Application application = getApplication();
        App app = (App) application;
        return app.component();
    }

    /**
     * Get an Activity module for dependency injection.
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
