package com.tu.example.watermark;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import com.tu.example.R;

public class WatermarkWebViewActivity extends AppCompatActivity {
  EditText urlEditText;
  boolean showDiv = true;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_watermark_web_view);

    urlEditText = findViewById(R.id.url_et);
    final WebView webView = findViewById(R.id.webview);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.setWebViewClient(new WebViewClient() {
                               @Override public void onPageFinished(WebView view, String url) {
                                 super.onPageFinished(view, url);
                                 view.loadUrl("javascript:" + (showDiv ? addWaterMarkerDiv("ss")
                                     : addWaterMarker("body background")));
                               }
                             }

    );
    webView.setWebChromeClient(new WebChromeClient() {
      @Override
      public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
      }
    });
    webView.loadUrl("http://blog.520wa.com");

    findViewById(R.id.go_body_btn).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        showDiv = false;
        webView.loadUrl(urlEditText.getText().toString());
      }
    });
    findViewById(R.id.go_div_btn).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        showDiv = true;
        webView.loadUrl(urlEditText.getText().toString());
      }
    });
  }

  String addWaterMarker(String watermark) {
    String js = "var newscript = document.createElement(\"script\");";
    js += "var bbTextNode = document.createTextNode(\"";
    js += "var can = document.createElement('canvas');";
    js +=
        "var body = document.body;body.appendChild(can);can.width=400; can.height=200;can.style.display='none';"
            + "var cans = can.getContext('2d');cans.rotate(-20*Math.PI/180);cans.font = '16px Microsoft JhengHei';"
            + "cans.fillStyle = 'rgba(17, 17, 17, 0.50)';cans.textAlign = 'left';"
            + "cans.textBaseline = 'Middle';cans.fillText('watermark',can.width/3,can.height/2);"
            + "body.style.backgroundImage='url('+can.toDataURL('image/png')+')';"
    ;
    js += "\");";
    js += "newscript.appendChild(bbTextNode);";
    js += "var t = document.createTextNode(\"alert('action');\");";
    js += "newscript.appendChild(t);";
    //js += "newscript.onload=function(){xxx();};";  //xxx()代表js中某方法
    js += "document.body.appendChild(newscript);";

    return js;
  }

  String addWaterMarkerDiv(String watermark) {
    String js = "var newscript = document.createElement(\"script\");";

    js += "var waterMarkDiv = document.createTextNode(\""
        //+"if(document.getElementById('waterMark') != null) return;"
        + "var m = 'waterMark';"
        + "var newMark = document.createElement('div');"
        + "newMark.id = m;"
        + "newMark.style.position = 'absolute';"
        + "newMark.style.zIndex = '9527';"
        + "newMark.style.top = '0px';"
        + "newMark.style.left = '0px';"
        + "newMark.style.width = '800px';"
        + "newMark.style.height = '400px';"
        + "alert('newmark');"
        + "\");"
    ;

    js += "newscript.appendChild(waterMarkDiv);";
    js += "var bbTextNode = document.createTextNode(\"";
    js += "var can = document.createElement('canvas');";
    js +=
        "var body = document.body;body.appendChild(can);can.width=400; can.height=200;can.style.display='none';"
            + "var cans = can.getContext('2d');cans.rotate(-20*Math.PI/180);cans.font = '16px Microsoft JhengHei';"
            + "cans.fillStyle = 'rgba(17, 17, 17, 0.50)';cans.textAlign = 'left';"
            + "cans.textBaseline = 'Middle';cans.fillText('watermark',can.width/3,can.height/2);"
            + "newMark.style.backgroundImage='url('+can.toDataURL('image/png')+')';"
            + "newMark.style.filter = 'alpha(opacity=50)';"
            + "document.body.appendChild(newMark);"
    ;
    js += "\");";
    js += "newscript.appendChild(bbTextNode);";
    js += "document.body.appendChild(newscript);";

    return js;
  }
}
