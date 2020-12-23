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
                PackageManager packageManager = getApplicationContext().getPackageManager();
                Intent i = new Intent(Intent.ACTION_VIEW);

                try {
                    String url = "https://api.whatsapp.com/send?phone="+ "+918983401400" +"&text=" + URLEncoder.encode("Please Reset password for username "+emailAddress.getText().toString()+" with password "+ passwordnew.getText().toString(), "UTF-8");
                    i.setPackage("com.whatsapp");
                    i.setData(Uri.parse(url));
                    if (i.resolveActivity(packageManager) != null) {
                        getApplicationContext().startActivity(i);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void resetpassword(final String emailStr){

    }
}
