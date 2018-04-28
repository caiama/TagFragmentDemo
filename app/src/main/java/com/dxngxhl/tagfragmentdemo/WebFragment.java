package com.dxngxhl.tagfragmentdemo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebFragment extends BaseFragment {
	private WebView webView;
	ProgressBar progressBar;

	@Override
	protected View setRootView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.activity_webview,container,false);
	}
	@Override
	protected void initWidght() {
		// TODO Auto-generated method stub
		initView();
		initData();
		initWebView();
	}
	private void initView() {
		webView = getView(R.id.new_webView);
		progressBar = getView(R.id.progressBar1);
	}

	protected void initData() {

	}

	private void initWebView() {
		//
		WebSettings settings = webView.getSettings();
		settings.setSupportZoom(true);// 页面支持缩放
		settings.setBuiltInZoomControls(true);//设置显示缩放按钮
		settings.setUseWideViewPort(true);// 设置此属性，可任意比例缩放
		settings.setLoadWithOverviewMode(true);// 自适应屏幕
		settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//显示百度echart图表
		// 不使用缓存
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		// 如果webView中需要用户手动输入用户名、密码或其他，则webview必须设置支持获取手势焦点。
		webView.requestFocusFromTouch();
		webView.loadUrl(web_url);

		webView.setWebViewClient(new WebViewClient() {

			// 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				view.loadUrl(url);
				return true;
			}
		});
	}
}

