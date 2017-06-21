package com.game.android.mahfuzcse11.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    WebView webView;
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebView());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        etSearch = (EditText) findViewById(R.id.etSearch);

    }

    public void btnGo(View view) {
        loadUrl(etSearch.getText().toString());
    }

    String url;

    public void loadUrl(String url) {
        this.url = url;
        webView.loadUrl(url);
    }

    public void btnBack(View view) {
        webView.goBack();
    }

    public void btnNext(View view) {

        webView.goForward();
    }


    private class MyWebView extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(url);
            return true;
        }


    }
}
