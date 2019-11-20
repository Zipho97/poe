package com.example.poe3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CalculatorFragment extends Fragment {

    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_calculator, container, false);

        webView = (WebView)rootView.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.calculator.net/bmi-calculator.html?ctype=metric&cage=22&csex=m&cheightfeet=5&cheightinch=10&cpound=7&cheightmeter=180&ckg=70&printit=0&x=78&y=25");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        return rootView;
    }

    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {

        }
    }
}
