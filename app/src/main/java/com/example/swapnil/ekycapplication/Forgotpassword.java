package com.example.swapnil.ekycapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class Forgotpassword extends AppCompatActivity {
Button forgotPass,sendotp;
EditText emailAddress,passwordnew,otppasswordet;
String OTP,updatepasswordUrl="http://greenleafpureveg.in/utsavapplication/passwordupdate.php";
String forgotpassUrl="http://greenleafpureveg.in/utsavapplication/sendotp.php",otpstr;
ProgressBar sendotpprogress,updateprogressbar;
    StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        forgotPass=(Button)findViewById(R.id.resetpassword);
        emailAddress=(EditText)findViewById(R.id.input_email);
        passwordnew=(EditText)findViewById(R.id.passwordnew);
        otppasswordet=(EditText)findViewById(R.id.otppassword);
        sendotp=(Button)findViewById(R.id.sendotp);
        sendotpprogress=(ProgressBar)findViewById(R.id.otpprogress);
        updateprogressbar=(ProgressBar)findViewById(R.id.updateprogressbar) ;


        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordnew.equals("")){
                    Toast.makeText(Forgotpassword.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }else if(otppasswordet.equals("")){
                    Toast.makeText(Forgotpassword.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(Forgotpassword.this, otpstr, Toast.LENGTH_SHORT).show();
                    if(otpstr.equals(otppasswordet.getText().toString().trim())){
                        updatepassword();
                    }else{
                        Toast.makeText(Forgotpassword.this, "OTP is wrong", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });


        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(emailAddress.equals("")){
                    emailAddress.requestFocus();
                    Toast.makeText(Forgotpassword.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                }
                else {

                    sendMessage();
                }
            }
        });


    }

    private void updatepassword()
    {
        updateprogressbar.setVisibility(View.VISIBLE);
        stringRequest=new StringRequest(Request.Method.POST, updatepasswordUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    updateprogressbar.setVisibility(View.GONE);

                    JSONObject jsonObject=new JSONObject(response);

                    if(jsonObject.getString("success").equals("1")){
                        Intent gotodash=new Intent(Forgotpassword.this,Login.class);
                        Toast.makeText(Forgotpassword.this, "Password successfully changed", Toast.LENGTH_SHORT).show();
                        startActivity(gotodash);
                                }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                updateprogressbar.setVisibility(View.GONE);
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userid", emailAddress.getText().toString());
                params.put("password", passwordnew.getText().toString());
                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(stringRequest);

    }



    private void sendMessage()
    {
        sendotpprogress.setVisibility(View.VISIBLE);
        stringRequest=new StringRequest(Request.Method.POST, forgotpassUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    sendotpprogress.setVisibility(View.GONE);

                    JSONObject jsonObject=new JSONObject(response);

                    if(jsonObject.getString("success").equals("1")){
                        otpstr=jsonObject.getString("otp").toString();
                       // Toast.makeText(Forgotpassword.this, jsonObject.getString("otp").toString(), Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                sendotpprogress.setVisibility(View.GONE);

                     }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userid", emailAddress.getText().toString());

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(stringRequest);

    }



}
