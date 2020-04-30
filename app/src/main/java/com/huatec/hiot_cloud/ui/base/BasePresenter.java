package com.huatec.hiot_cloud.ui.base;
/*
    MVP架构presenter层基类
 */
//实验2.2
public class BasePresenter <V extends BaseView>{
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
}
