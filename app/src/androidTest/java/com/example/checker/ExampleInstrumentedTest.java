package com.example.checker;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.checker.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;

class MainActivity extends AppCompatActivity {

    /*
        var h = document.getElementsByClassName('top container')[0];
        document.body.innerHTML = '';
        document.body.appendChild(h);
     */
    private String getScript =
            "(function() {" +
                    "var h = document.getElementsByClassName('top container')[0];" +
                    "document.body.innerHTML = '';" +
                    "document.body.appendChild(h);" +
                    "})()";

    private ActivityMainBinding binding;
    private WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnSettingClicked(v);
            }
        });

        webSettings = binding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportMultipleWindows(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        binding.webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                view.evaluateJavascript(getScript, null);
                binding.webView.setVisibility(View.VISIBLE);
                binding.lbLoading.setVisibility(View.INVISIBLE);
            }
        });

        binding.webView.loadUrl("https://coronaboard.kr/");
        binding.webView.setVisibility(View.INVISIBLE);
        binding.lbLoading.setVisibility(View.VISIBLE);
    }

    private void onBtnSettingClicked(View v) {

    }
}