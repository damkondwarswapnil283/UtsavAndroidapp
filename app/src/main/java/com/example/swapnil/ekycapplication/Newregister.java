package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
    String newregisterUrl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newregister);

        usernameEt=(EditText)findViewById(R.id.username);
        passwordEt=(EditText)findViewById(R.id.inputpassword);
        mobilenumberEt=(EditText)findViewById(R.id.mobilenumber);
        otpEt=(EditText)findViewById(R.id.otp);
        firstnameEt=(EditText)findViewById(R.id.firstname);
        middlenameEt=(EditText)findViewById(R.id.middlename);
        lastnameEt=(EditText)findViewById(R.id.lastname);
        newregisterUrl= getString(R.string.newregister);
        registerProcess=(ProgressBar)findViewById(R.id.registerprogress);


        submitBtn=(Button)findViewById(R.id.submit);

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
                    }else{
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
                usernameSt=usernameEt.getText().toString();
                passwordSt=passwordEt.getText().toString();
                mobilenumberSt=mobilenumberEt.getText().toString();
                otpSt=otpEt.getText().toString();
                firstnameSt=firstnameEt.getText().toString();
                middlenameSt=middlenameEt.getText().toString();
                lastnameSt=lastnameEt.getText().toString();
                registerProcess.setVisibility(View.VISIBLE);
                checkvalidation();
            }
        });

    }

    public void checkvalidation(){
         if(firstnameSt.trim().equals("")){
            firstnameEt.requestFocus();
            Toast.makeText(this, "Firstname can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(middlenameSt.trim().equals("")){
            middlenameEt.requestFocus();
            Toast.makeText(this, "Middle Name can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(mobilenumberSt.trim().equals("")){
             mobilenumberEt.requestFocus();
             Toast.makeText(this, "Mobilenumber can`t be left blank", Toast.LENGTH_SHORT).show();
         }else if(lastnameSt.trim().equals("")){
            lastnameEt.requestFocus();
            Toast.makeText(this, "Lastname can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(usernameSt.trim().equals("")){
            usernameEt.requestFocus();
            Toast.makeText(this, "Username can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(passwordSt.trim().equals("")){
            passwordEt.requestFocus();
            Toast.makeText(this, "Password can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(otpSt.trim().equals("")){
             passwordEt.requestFocus();
             Toast.makeText(this, "OTP can`t be left blank", Toast.LENGTH_SHORT).show();
         }else {
             AppController.getInstance().addToRequestQueue(stringRequest);
         }
    }
}
