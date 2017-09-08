package com.hellohasan.thirdclass;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyBrowser extends WebViewClient {

    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
