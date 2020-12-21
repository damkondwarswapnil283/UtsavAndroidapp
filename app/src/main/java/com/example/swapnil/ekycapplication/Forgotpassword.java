package com.example.swapnil.ekycapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Forgotpassword extends AppCompatActivity {
Button forgotPass;
EditText emailAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        forgotPass=(Button)findViewById(R.id.resetpassword);
        emailAddress=(EditText)findViewById(R.id.input_email);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpassword(emailAddress.getText().toString());
            }
        });
    }

    public void resetpassword(final String emailStr){

    }
}
