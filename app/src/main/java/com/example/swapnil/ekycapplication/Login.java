package com.example.swapnil.ekycapplication;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends Activity {
Button login ,signup;
TextView forgotpassword;
    private FirebaseAuth mAuth;
    EditText usernameEt,passwordEt;
    String usernameStr,passwordStr;
    ProgressBar loginProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.loginbtn);
        signup=(Button)findViewById(R.id.signup);
        usernameEt=(EditText)findViewById(R.id.input_email);
        passwordEt=(EditText)findViewById(R.id.input_password) ;
        forgotpassword=(TextView)findViewById(R.id.forgotpass);
        loginProgress=(ProgressBar)findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoforgotpassscreen=new Intent(Login.this,Forgotpassword.class);
                startActivity(gotoforgotpassscreen);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProgress.setVisibility(View.VISIBLE);
                usernameStr=usernameEt.getText().toString();
                passwordStr=passwordEt.getText().toString();
                login(usernameStr,passwordStr);
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

    public void login(String username,String password){
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent gotologinscreen=new Intent(Login.this,Selectionactivity.class);
                            startActivity(gotologinscreen);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Wrong Credentials.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
