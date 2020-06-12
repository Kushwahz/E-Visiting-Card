package com.wordpress.helpmevishal.vishalsingh;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class my_cv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cv);
        WebView website = (WebView) findViewById(R.id.my_cv_web);
        WebSettings websiteSettings = website.getSettings();
        website.setWebViewClient(new AppWebViewClients());
        websiteSettings.setJavaScriptEnabled(true);
        website.loadUrl("https://drive.google.com/open?id=0ByA1LNYTorRQTmg2akJRVXhEUXc");
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
        website.getSettings().setBuiltInZoomControls(true);
        website.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {

                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Downloading File...", //To notify the Client that the file is being downloaded
                        Toast.LENGTH_LONG).show();

            }
        });


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
            pd = new ProgressDialog(my_cv.this);
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




