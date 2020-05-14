package com.huatec.hiot_cloud.ui.base;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
    MVP架构presenter层基类
 */
//实验2.2
public class BasePresenter <V extends BaseView>{

    private static final String TAG = "BasePresenter";
    private V view;

    public void setView(V view) {
        this.view = view;
    }

    public BasePresenter() {

    }
    public void destroy(){
        if (viewAttached()){
            view = null;
        }
    }

    public V getView() {
        return view;
    }

    public boolean viewAttached(){
        return view != null;
    }


    public <T> void subscrib(Observable<T> observable, final ResquestCallback<T> callback) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callback.onSubscribe(d);
                    }

                    @Override
                    public void onNext(T t) {
                        callback.onNext(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        callback.onComplete();
                    }
                });
    }


    /**
     * 回调类
     */
    public abstract class ResquestCallback<T> {

        public void onSubscribe(Disposable d) {

        }


        public abstract void onNext(T t);


        public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e.getMessage(), e);
        }


        public void onComplete() {

        }
    }
}
