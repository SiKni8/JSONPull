package com.sikni8.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class DisplayWeb extends Activity {
	
	private WebView webView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.displayweb);
 
		webView = (WebView) findViewById(R.id.wbView);
		webView.getSettings().setJavaScriptEnabled(false);
		webView.loadUrl("http://www.pagesbyz.com");
	}
}