package com.dreamsoft.tableview

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_webview.*


class TableWebViewActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        initViews()
    }

    private fun initViews() {
        webview.getSettings().setUseWideViewPort(true);
        webview.settings.javaScriptEnabled = true
        webview.loadUrl("file:///android_asset/weekview.html")
        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
              //  webview.loadUrl("javascript:init('" + theArgumentYouWantToPass.toString() + "')")
            }
        }
    }

}