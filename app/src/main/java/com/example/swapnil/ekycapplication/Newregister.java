package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Newregister extends AppCompatActivity {
EditText usernameEt,passwordEt,mobilenumberEt,firstnameEt,middlenameEt,lastnameEt,otpEt;
String usernameSt,passwordSt,mobilenumberSt,firstnameSt,middlenameSt,lastnameSt,otpSt;
ProgressBar registerProcess;
Button sendotpBtn,submitBtn;
    StringRequest stringRequest;
    String newregisterUrl="",namestring;
    TextView usernametext,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newregister);

        username=(TextView)findViewById(R.id.username);
        usernametext=(TextView)findViewById(R.id.usernametext);
        passwordEt=(EditText)findViewById(R.id.inputpassword);
        mobilenumberEt=(EditText)findViewById(R.id.mobilenumber);
        firstnameEt=(EditText)findViewById(R.id.firstname);
        middlenameEt=(EditText)findViewById(R.id.middlename);
        lastnameEt=(EditText)findViewById(R.id.lastname);
        newregisterUrl= getString(R.string.newregister);
        registerProcess=(ProgressBar)findViewById(R.id.registerprogress);
        submitBtn=(Button)findViewById(R.id.submit);
        namestring=getIntent().getExtras().getString("username");
        username.setText(namestring);
        stringRequest=new StringRequest(Request.Method.POST, newregisterUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);

                    if(jsonObject.getString("success").equals("1")){
                        Toast.makeText(Newregister.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                        registerProcess.setVisibility(View.GONE);
                        Intent gotologin=new Intent(Newregister.this,Login.class);
                        startActivity(gotologin);
                        finish();
                    }else if(jsonObject.getString("success").equals("2")){
                        registerProcess.setVisibility(View.GONE);
                        usernametext.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                usernametext.setVisibility(View.GONE);
                            }
                        },6000);
                        Toast.makeText(Newregister.this, "Username already exists,Please select something different", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        registerProcess.setVisibility(View.GONE);
                        Toast.makeText(Newregister.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                registerProcess.setVisibility(View.GONE);
                Toast.makeText(Newregister.this, "Something went wrong.Please Try again !!!", Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", usernameSt);
                params.put("password",passwordSt );
                params.put("mobilenumber", mobilenumberSt);
                params.put("firstname",firstnameSt );
                params.put("middlename", middlenameSt);
                params.put("lastname",lastnameSt );
                return params;
            }

        };

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameSt=username.getText().toString();
                passwordSt=passwordEt.getText().toString();
                mobilenumberSt=mobilenumberEt.getText().toString();
               // otpSt=otpEt.getText().toString();
                firstnameSt=firstnameEt.getText().toString();
                middlenameSt=middlenameEt.getText().toString();
                lastnameSt=lastnameEt.getText().toString();

                checkvalidation();
            }
        });

    }

    public void checkvalidation(){
         if(firstnameSt.trim().equals("")){
            Toast.makeText(Newregister.this, "Firstname can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(middlenameSt.trim().equals("")){
            middlenameEt.requestFocus();
            Toast.makeText(Newregister.this, "Middle Name can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(mobilenumberSt.length()!=10){
             mobilenumberEt.requestFocus();
             Toast.makeText(Newregister.this, "Please check mobilenumber", Toast.LENGTH_SHORT).show();
         }else if(lastnameSt.trim().equals("")){
            lastnameEt.requestFocus();
            Toast.makeText(Newregister.this, "Lastname can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(passwordSt.trim().equals("")){
            passwordEt.requestFocus();
            Toast.makeText(Newregister.this, "Password can`t be left blank", Toast.LENGTH_SHORT).show();
        }else {
             registerProcess.setVisibility(View.VISIBLE);
             AppController.getInstance().addToRequestQueue(stringRequest);
         }
    }
}
