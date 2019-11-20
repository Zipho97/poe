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

public class TrainingFragment extends Fragment {

    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_training, container, false);

        webView = (WebView)rootView.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.bodybuilding.com/exercises/finder");

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
