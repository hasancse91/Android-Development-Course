/**
 * Android Ninja - Class No: 3 | 25 August, 2017
   TODAY's Topics:
    - ImageView with Android Piccasso Library
    - SharedPreference
    - WebView
    - Send an E-mail using Intent
    - Discussing about Singleton Design Pattern
    - Discussing about Android compile and build system. Keyword: Dalvik
 */

package com.hellohasan.thirdclass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private WebView webView;
    private MyPreference myPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View and SharedPreference initialization
        initialization();

        //Load image from server
        Picasso.with(this)
                .load(myPreference.getImageUrl()) //get image url from SharedPreference
                .placeholder(R.drawable.placeholder) //show before image loading from server
                .error(R.drawable.error_image) //show if any error occurred during image loading
                .into(imageView);

        //Load a website into WebView
        loadWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void loadWebView() {
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        String url = "https://hellohasan.com";
        webView.loadUrl(url);
    }

    private void initialization() {
        myPreference = MyPreference.getPreferences(this);
        myPreference.setImageUrl("https://vignette3.wikia.nocookie.net/hayday/images/7/7d/Apple.png/revision/latest/scale-to-width-down/100?cb=20150713085424");

        imageView = (ImageView) findViewById(R.id.imageView);
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new MyBrowser());
    }

    public void sendEmail(View view) {
        String[] TO = {"megamindscobd@gmail.com"};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Mail from Android Ninja App");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email Body");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
