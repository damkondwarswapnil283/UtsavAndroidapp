package com.example.swapnil.ekycapplication;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends Activity {
Button login ,signup;
TextView forgotpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.loginbtn);
        signup=(Button)findViewById(R.id.submitbt);
        forgotpassword=(TextView)findViewById(R.id.forgotpass);

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoforgotpassscreen=new Intent(Login.this,Forgotpassword.class);
                startActivity(gotoforgotpassscreen);
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologinscreen=new Intent(Login.this,MainActivity.class);
                startActivity(gotologinscreen);
                finish();
            }
        });

    }
}
