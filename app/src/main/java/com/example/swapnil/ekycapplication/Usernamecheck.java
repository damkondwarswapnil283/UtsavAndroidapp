package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Usernamecheck extends AppCompatActivity {
ProgressBar mainProgress;
StringRequest stringRequest;
Button continueBtn;
EditText checkusername;
TextView isvailable;
String nameString,checkurl="http://greenleafpureveg.in/utsavapplication/usernamecheck.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usernamecheck);
        mainProgress=(ProgressBar)findViewById(R.id.userprogress);
        checkusername=(EditText)findViewById(R.id.checkusername);
        continueBtn=(Button)findViewById(R.id.check);
        isvailable=(TextView)findViewById(R.id.isvailable);




        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkusername.getText().toString().equals("")){

                    Toast.makeText(Usernamecheck.this, "Please enter username", Toast.LENGTH_SHORT).show();
                }else{
                    mainProgress.setVisibility(View.VISIBLE);
                    sendrequest();
                }

            }
        });

    }

    public void sendrequest(){
        isvailable.setVisibility(View.GONE);
        nameString=checkusername.getText().toString();
        stringRequest=new StringRequest(Request.Method.POST, checkurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("Response",response);
                    if(new JSONObject(response).getString("success").equals("1")){

                        Toast.makeText(Usernamecheck.this, "Great!! This username is available", Toast.LENGTH_SHORT).show();
                        Intent gotoregister=new Intent(Usernamecheck.this,Newregister.class);
                        gotoregister.putExtra("username",nameString);
                        startActivity(gotoregister);
                    }else{

                        isvailable.setVisibility(View.VISIBLE);
                        isvailable.setText(nameString+" allready exists please try other username");

                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
                mainProgress.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                mainProgress.setVisibility(View.GONE);
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username",nameString);

                return params;
            }

        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
