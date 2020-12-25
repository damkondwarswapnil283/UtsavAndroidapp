package com.example.swapnil.ekycapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLEncoder;


public class Forgotpassword extends AppCompatActivity {
Button forgotPass;
EditText emailAddress,passwordnew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        forgotPass=(Button)findViewById(R.id.resetpassword);
        emailAddress=(EditText)findViewById(R.id.input_email);
        passwordnew=(EditText)findViewById(R.id.passwordnew);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(emailAddress.equals("")){
                    emailAddress.requestFocus();
                    Toast.makeText(Forgotpassword.this, "Please Enter Email Address", Toast.LENGTH_SHORT).show();
                }else if(passwordnew.equals("")){
                    passwordnew.requestFocus();
                    Toast.makeText(Forgotpassword.this, "Please Enter password", Toast.LENGTH_SHORT).show();
                }else {

                    // from edit text
                    String message
                            = "Please reset password for username for"+emailAddress.getText()
                            .toString() +" with password as "+passwordnew.getText()
                            .toString()
                            ;

                    // Calling the function
                    // to send message
                    sendMessage(message);

                }
            }
        });


    }

    private void sendMessage(String message)
    {

        String toNumber = "+919834179216"; // contains spaces.
        toNumber = toNumber.replace("+", "").replace(" ", "");

        Intent sendIntent = new Intent("android.intent.action.MAIN");
        sendIntent.putExtra("jid", toNumber + "@s.whatsapp.net");
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setPackage("com.whatsapp");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

    }



}
