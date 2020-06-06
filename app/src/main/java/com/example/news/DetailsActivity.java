package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    TextView tvTitle,tvDate,tvSource,tvDesc;
    WebView webView;
    ImageView imageView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tvDate=findViewById(R.id.tv_Date);
        tvTitle=findViewById(R.id.tv_title);
        tvSource=findViewById(R.id.tv_Source);
        tvDesc=findViewById(R.id.tv_Desc);
        webView=findViewById(R.id.webView);
        imageView=findViewById(R.id.imag_details);
        progressBar=findViewById(R.id.details_loader);
        progressBar.setVisibility(View.VISIBLE);
       // getDetails();

    }

    @Override
    protected void onStart() {
        super.onStart();
        getDetails();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void getDetails(){
        Intent intent =getIntent();
        String title=intent.getStringExtra("title");
        String source=intent.getStringExtra("source");
        String date=intent.getStringExtra("date");
        String image=intent.getStringExtra("imageUrl");
        String url=intent.getStringExtra("url");
        String desc=intent.getStringExtra("desc");
        tvSource.setText(source);
        tvTitle.setText(title);
        tvDate.setText(date);
        tvDesc.setText(desc);
        Picasso.get().load(image).into(imageView);
        webView.loadUrl(url);
        webView.setWebViewClient( new WebViewClient());

        WebSettings webSettings = webView.getSettings();

        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setSupportZoom(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });

        if (webView.isShown()){
            progressBar.setVisibility(View.INVISIBLE);
        }



    }


}
