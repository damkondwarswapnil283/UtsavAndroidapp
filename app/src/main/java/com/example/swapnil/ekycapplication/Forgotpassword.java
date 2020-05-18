package com.example.swapnil.ekycapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

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
        FirebaseAuth.getInstance().sendPasswordResetEmail(emailStr)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Forgotpassword.this, "If you are registered with us then you will receive mail at "+emailStr, Toast.LENGTH_SHORT).show();
                            Intent gotologinscreen=new Intent(Forgotpassword.this,Login.class);
                            startActivity(gotologinscreen);

                        }
                    }
                });
    }
}
