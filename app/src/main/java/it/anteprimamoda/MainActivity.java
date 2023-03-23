package it.anteprimamoda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String url = "https://anteprimalagosanto.it";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.web_view);
        WebViewClient client = new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
//                if (request.getUrl().getScheme().equals("whatsapp")) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                    intent.setData(request.getUrl());
//                    System.out.println("WHATS: " + request.getUrl());
//                    System.out.println("WHATS: " + request.getUrl().toString());
//                    System.out.println("WHATS: " + Uri.decode(request.getUrl().toString()));
////                    startActivity(intent);
////                    webView.goBack();
//                    return true;
//                }
//                else
                if (url.startsWith("https://wa.me/")) {
                    url = url.replace("https://wa.me/", "whatsapp://send/?phone=");
//                    url = url.replace("+", "%20");
//                    url = url.replace("%21", "!");
                    url = url.replace("?text=", "&text=");
                    url = url + "&type=phone_number&app_absent=0";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.setData(Uri.parse(url));
                    System.out.println("WA:ME: " + Uri.parse(url));
                    System.out.println("WA:ME: " + url);
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(MainActivity.this, "Installa Whatsapp per contattarci direttamente.", Toast.LENGTH_SHORT).show();
                    }
//                    webView.goBack();
                    return true;
                }
                return false;
            }
        };

        webView.setWebViewClient(client);

//        WebChromeClient client2 = new WebChromeClient();
//
//        webView.setWebChromeClient(client2);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(url);

    }

    @Override
    public void onBackPressed(){
        if(webView.canGoBack()) {
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}