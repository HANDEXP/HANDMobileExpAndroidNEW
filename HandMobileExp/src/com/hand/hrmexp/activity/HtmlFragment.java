package com.hand.hrmexp.activity;

import com.hand.R;
import com.handexp.utl.AsNetWorkUtl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

public class HtmlFragment extends Fragment{
	private  WebView contentWebView; 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View root = inflater.inflate(R.layout.activity_html_base, container,false);
		bindAllViews(root);
		return root;
	}
	
	private void bindAllViews(View root) {
		contentWebView = (WebView) root.findViewById(R.id.activity_html_base_webview);
		WebSettings webSettings = contentWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);

	} 
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String url = getArguments().getString("url");
		load(url);
	}

	/**
	 * @param url
	 */
    protected void load(String url) {
    	//System.out.println(AsNetWorkUtl.getAsNetWorkUtl(null).getAbsoluteUrl(url.replace("${base_url}", "")));
	    contentWebView.loadUrl(AsNetWorkUtl.getAsNetWorkUtl(null).getAbsoluteUrl(url.replace("${base_url}", "")));
    }
	
}
