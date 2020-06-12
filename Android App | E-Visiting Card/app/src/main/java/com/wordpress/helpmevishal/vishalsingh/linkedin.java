package com.wordpress.helpmevishal.vishalsingh;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class linkedin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkedin);
        WebView website = (WebView) findViewById(R.id.webView_linkedin);
        WebSettings websiteSettings = website.getSettings();
        website.setWebViewClient(new AppWebViewClients());
        websiteSettings.setJavaScriptEnabled(true);
        website.getSettings().setUseWideViewPort(true);
        website.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        website.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        website.getSettings().setAppCacheEnabled(true);
        website.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        websiteSettings.setDomStorageEnabled(true);
        websiteSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        websiteSettings.setUseWideViewPort(true);
        websiteSettings.setSavePassword(true);
        websiteSettings.setSaveFormData(true);
        websiteSettings.setEnableSmoothTransition(true);
        website.getSettings().setLoadWithOverviewMode(true);
        website.getSettings().setUseWideViewPort(true);

        website.loadUrl("https://www.linkedin.com/in/kushwahz");
    }

    private class AppWebViewClients extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }

        ProgressDialog pd = null;
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            pd = new ProgressDialog(linkedin.this);
            pd.setTitle("Please wait");
            pd.setMessage("Loading..");
            pd.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pd.dismiss();
        }
    }
}
