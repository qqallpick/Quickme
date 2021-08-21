package com.zsuuu.quickmeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class FourthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)
        var webView = findViewById<View>(R.id.WebView) as WebView
        webView.settings.javaScriptEnabled=true
        webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
//        webView.settings.setAppCacheEnabled(true)
//        webView.settings.setAppCachePath(cacheDir.path)
        webView.webViewClient= WebViewClient()
        webView.loadUrl("https://www.zsuuu.com/?p=1305")
    }
}