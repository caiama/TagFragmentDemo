package com.dxngxhl.tagfragmentdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment {
    /*布局*/
    private View view = null;
    /**/
    boolean isVisible = false,isInit = false;
    public ViewGroup container = null;
    public Context mContext;
    //是否删除
    public boolean isDelete = false;
    protected String web_url = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = setRootView(inflater,container);
        Log.d("包名："+getClass().getName(),"onCreateView");
        mContext = getActivity();
        initWidght();
//        initData();
//        onVisible();
        this.container = container;
        return view;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    protected View setRootView(LayoutInflater inflater, ViewGroup container){
        return null;
    }
    protected abstract void initWidght();
//    protected abstract void initData();
//    protected abstract ViewGroup getContainer();
    /**
     * 解决预加载
     * */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("包名："+getClass().getName()," setUserVisibleHint  显示"+getUserVisibleHint());
        if (getUserVisibleHint()){
            isVisible = true;
            onVisible();
        }else {
//            onInvisible();
        }
    }

    /**
     *  预加载--显示-加载数据
     * */
    private void onVisible() {
        Log.i("","========"+getClass().getName()+"    isVisible:"+isVisible+"   view:"+(view != null));
        if (isVisible && view != null){
            Log.i("","========isInit1:"+isInit);
            if (!isInit){
                initWidght();
                isInit = true;
            }else {
//                initData();
            }
        }
    }
    /**
     * 预加载--不显示时
     * */
    private void onInvisible() {
        isVisible = false;
    }
    protected <T extends View>T getView(int resourcesId){
        return (T) view.findViewById(resourcesId);
    }
    /**
     * Toast提示;
     * @param content 提示内容;
     */
    protected void showToast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }
    
}