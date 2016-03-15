package com.example.mykindergarten;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

/**
 * Created by Nir_2 on 09/03/2016.
 */
public class WelcomeScreen extends Activity {

    //Screen timer set to 4 seconds
    private final static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        WebView wv= (WebView) findViewById(R.id.webView);
        wv.loadUrl("file:///android_asset/NEW.gif");

        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setUseWideViewPort(true);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(WelcomeScreen.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
