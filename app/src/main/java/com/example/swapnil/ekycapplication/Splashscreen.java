package com.example.swapnil.ekycapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splashscreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent gotologinscreen=new Intent(Splashscreen.this,Login.class);
                startActivity(gotologinscreen);
                finish();
            }
        },1000);
    }
}
