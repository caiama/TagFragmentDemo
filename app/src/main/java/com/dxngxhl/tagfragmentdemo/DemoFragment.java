package com.dxngxhl.tagfragmentdemo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
public class DemoFragment extends BaseFragment {
	
	@Override
	protected View setRootView(LayoutInflater inflater, ViewGroup container) {
		// TODO Auto-generated method stub
		 return inflater.inflate(R.layout.fragment_demo,container,false);
	}
	@Override
	protected void initWidght() {
		// TODO Auto-generated method stub
		initView();
		initData();
	}
	private void initView() {

	}

	protected void initData() {

	}
}

