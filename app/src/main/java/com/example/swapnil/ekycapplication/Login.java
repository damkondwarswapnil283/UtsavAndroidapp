package com.example.swapnil.ekycapplication;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends Activity {
Button login ,signup;
TextView forgotpassword;

    EditText usernameEt,passwordEt;
    String usernameStr,passwordStr,getid;
    ProgressBar loginProgress;
    String loginurl="http://greenleafpureveg.in/utsavapplication/login.php";
    StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.loginbtn);
        signup=(Button)findViewById(R.id.signup);
        usernameEt=(EditText)findViewById(R.id.input_email);
        passwordEt=(EditText)findViewById(R.id.input_password) ;
        forgotpassword=(TextView)findViewById(R.id.forgotpass);
        loginProgress=(ProgressBar)findViewById(R.id.loginprogress);

        stringRequest=new StringRequest(Request.Method.POST, loginurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();
                try {
                    loginProgress.setVisibility(View.GONE);

                    //login.setVisibility(View.VISIBLE);
                    JSONObject jsonObject=new JSONObject(response);
                    String flag=jsonObject.getString("success");
                    if(flag.equals("1")){
                        loginProgress.setVisibility(View.GONE);
                        Intent gotodash=new Intent(Login.this,selectmode.class);
                        gotodash.putExtra("userid",jsonObject.getString("id"));
                      //  Toast.makeText(Login.this, "Login:- "+ jsonObject.getString("id"), Toast.LENGTH_SHORT).show();
                        startActivity(gotodash);
                        finish();
                    }else{
                        loginProgress.setVisibility(View.GONE);
                        Toast.makeText(Login.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {

                    login.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loginProgress.setVisibility(View.GONE);
                login.setVisibility(View.VISIBLE);
                Toast.makeText(Login.this, "Something went wrong. Please try again", Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", usernameStr);
                params.put("password",passwordStr );
                return params;
            }

        };

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

                usernameStr=usernameEt.getText().toString();
                passwordStr=passwordEt.getText().toString();
                if(usernameStr.equals("")){
                    usernameEt.requestFocus();
                    Toast.makeText(Login.this, "Please Enter username", Toast.LENGTH_SHORT).show();
                }else if(passwordStr.equals("")){
                    passwordEt.requestFocus();
                    Toast.makeText(Login.this, "Please Enter password", Toast.LENGTH_SHORT).show();
                }else{
                    loginProgress.setVisibility(View.VISIBLE);
                    login.setVisibility(View.GONE);
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            20000,
                            0,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    AppController.getInstance().addToRequestQueue(stringRequest);
                }


            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologinscreen=new Intent(Login.this,Usernamecheck.class);
                startActivity(gotologinscreen);
                finish();
            }
        });




    }
    @Override
    public void onBackPressed() {
        Intent gotologinscreen=new Intent(Login.this,Login.class);
        startActivity(gotologinscreen);
        finish();
    }


}
