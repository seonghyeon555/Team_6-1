package com.example.checker;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.checker.databinding.ActivityMainBinding;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    /*
        var h = document.getElementsByClassName('top container')[0];
        document.body.innerHTML = '';
        document.body.appendChild(h);
     */
    Button Btn1,Btn2;
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
                Uri uri = Uri.parse("http://baidu.com");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                startActivity(intent);
            }
        });
        webSettings = binding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportMultipleWindows(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        binding.webView.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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