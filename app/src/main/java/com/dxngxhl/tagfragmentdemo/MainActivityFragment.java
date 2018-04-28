package com.dxngxhl.tagfragmentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class MainActivityFragment extends FragmentActivity {

    List<String> titles = new ArrayList<String>();
    Fragment nowFragment = null;
    LinearLayout titleLabelLayout;
    TitleLabelClickListener labelClickListener = new TitleLabelClickListener();
    TitleLabelCloseListener labelCloseListener = new TitleLabelCloseListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_main);
        titleLabelLayout = (LinearLayout) findViewById(R.id.title_layout);
        	addNewFragment("首页", new IndexWebFragment(), "https://www.baidu.com");
//        addNewFragment("首页",new MyFragment(),null);
    }
    FragmentTransaction fragmentTransaction;
    Fragment fragmentC;
    protected void addNewFragment(String title,BaseFragment fragment,String weburl){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentC = getSupportFragmentManager().findFragmentByTag(title);
        if (fragmentC == null){
            Log.d("","========添加");
            addTitleLabelView(title);
            titles.add(title);
            fragment.web_url = weburl;
            //
//            getSupportFragmentManager().findFragmentByTag("");
            fragmentTransaction.add(R.id.framelayout,fragment,title);
            if (nowFragment != null){
                Log.d("","========add-隐藏这个");
                fragmentTransaction.hide(nowFragment);
            }
            fragmentTransaction.commit();
            nowFragment = fragment;
        }else {
            Log.d("","========"+fragmentC.getTag());
            titleLabelLayout.getChildAt(titles.indexOf(nowFragment.getTag())).setSelected(false);
            titleLabelLayout.getChildAt(titles.indexOf(fragmentC.getTag())).setSelected(true);
            fragmentTransaction.hide(nowFragment);
            fragmentTransaction.show(fragmentC);
            fragmentTransaction.commit();
            nowFragment = fragmentC;
//            viewPager.setCurrentItem(titles.indexOf(title));
        }
    }
    /**
     * 添加标签布局
     * */
    private void addTitleLabelView(String title) {
        //改掉上一个的背景颜色
        if (nowFragment != null){
            View v = titleLabelLayout.getChildAt(titles.indexOf(nowFragment.getTag()));
            Log.d("","=======addTitleLabelView-nowFragment.getTag()="+nowFragment.getTag());
            if (v != null){
                v.setSelected(false);
            }
        }
        //添加title-View
        TitleLabelView button = new TitleLabelView(this);
        //自动注册id
//            button.setId(View.NO_ID);
        button.setTag(title);
        button.setText(title);
        button.setSelected(true);
        //标签点击事件
        button.setOnClickListener(labelClickListener);
        //关闭点击事件
        button.setCloseListener(labelCloseListener);
        titleLabelLayout.addView(button);
        Log.d("","=======addTitleLabelView-button.getText()="+button.getText());
    }


    private void removeFragment(String arg){
        //第一个是不可以移除的
        if (titles.indexOf(arg) == 0){
            return;
        }
        //移除fragment
        fragmentC = getSupportFragmentManager().findFragmentByTag(arg);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (nowFragment == fragmentC){
            Log.d("","=======移除当前显示的="+fragmentC.getTag());
            //移除当前显示的，将显示切换到第一个
            nowFragment = getSupportFragmentManager().findFragmentByTag(titles.get(0));
            fragmentTransaction.show(nowFragment);
            titleLabelLayout.getChildAt(0).setSelected(true);
        }
        //移除fragment
        fragmentTransaction.remove(fragmentC);
        fragmentTransaction.commit();
        //移除title-View
        titleLabelLayout.removeViewAt(titles.indexOf(arg));
        //移除title
        titles.remove(arg);
    }

    private void switchFragment(String arg0){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentC = getSupportFragmentManager().findFragmentByTag(arg0);
        Log.d("","=======是否为空="+(fragmentC == null));
        fragmentTransaction.hide(nowFragment);
        fragmentTransaction.show(fragmentC);
        fragmentTransaction.commit();
        nowFragment = fragmentC;
    }
    /**
     * 标签选中
     * */
    class TitleLabelClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.d("","=======TitleLabelClickListener="+view.getTag());
            //将上一个选中取消
            titleLabelLayout.getChildAt(titles.indexOf(nowFragment.getTag())).setSelected(false);
            switchFragment((String) view.getTag());
            view.setClickable(true);
            view.setSelected(true);
        }
    }/**
     * 标签关闭
     * */
    class TitleLabelCloseListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.d("","=======TitleLabelCloseListener="+view.getTag());
            removeFragment((String) view.getTag());
        }
    }
//    protected void addNewFragment(Fragment fragment){
//
//    }

    public static class LabelBean implements Serializable{
        public String title;
        public BaseFragment fragment;
        public String url;

        public LabelBean(String title, BaseFragment fragment, String url) {
            this.title = title;
            this.fragment = fragment;
            this.url = url;
        }
    }
    protected Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 11){
                //web功能
                LabelBean labelBean = (LabelBean) msg.obj;
                addNewFragment(labelBean.title,labelBean.fragment,labelBean.url);
            }else if (msg.what == 12) {
                //本地功能
                int tag = (int) msg.obj;
                selectToLocFragment(tag);
            }else if (0 == msg.what) {

            }
        }
    };

    public void selectToLocFragment(int tag){
        switch (tag){
            case 1:
                addNewFragment("qwer",new DemoFragment(),null);
                break;
            case 2:
                Intent intent = new Intent(MainActivityFragment.this,DemoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
