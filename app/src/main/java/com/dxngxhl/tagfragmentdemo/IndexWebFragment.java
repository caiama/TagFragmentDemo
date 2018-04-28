package com.dxngxhl.tagfragmentdemo;

import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Cool on 2017/8/11.
 */

public class IndexWebFragment extends BaseFragment{
    WebView webView;
    MainActivityFragment mainActivityFragment;
    int webnum = 0,locnum = 0;
    @Override
    protected View setRootView(LayoutInflater inflater, ViewGroup container) {
//        Toast.makeText(getActivity(),"oncreateview1111",Toast.LENGTH_SHORT).show();
        Log.i("","=======onCreateView");
        return inflater.inflate(R.layout.fragment_index_web,container,false);
    }
    @Override
    protected void initWidght() {
        mainActivityFragment = (MainActivityFragment) getActivity();
        Log.i("","=======initwidght");
        webView = getView(R.id.fragment_web);
        //支持js(必要)
        webView.getSettings().setJavaScriptEnabled(true);
        //    添加js对象(必要)
//        webView.addJavascriptInterface(this, "android");
        webView.loadUrl(web_url);
        Log.e("","========web="+web_url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("","=====shouldOverrideUrlLoading=url="+url);
//                webView.loadUrl(url);
                Message message = new Message();
                message.what = 11;
                if (webnum == 2) webnum = 0;
                message.obj = new MainActivityFragment.LabelBean("web-" + (++webnum),new WebFragment(),url);
                mainActivityFragment.mHandler.sendMessage(message);
                return true;
            }

//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                Log.d("","=====shouldOverrideUrlLoading222=url="+request.getUrl().toString());
//
//                return super.shouldOverrideUrlLoading(view, request);
//            }
        });
        
        getView(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.what = 12;
                message.obj = (++locnum);
                mainActivityFragment.mHandler.sendMessage(message);
            }
        });

    }
}
