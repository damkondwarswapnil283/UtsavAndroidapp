package com.example.swapnil.ekycapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Selectionactivity extends Activity {
    LinearLayout OldCustomerbtn,NewcustomerBtn,createmyprofile,editmyprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectionactivity);
        StringRequest stringRequest;
        String isregisteredinMatrimony="";
        OldCustomerbtn = (LinearLayout) findViewById(R.id.oldcustomerll);
        NewcustomerBtn = (LinearLayout) findViewById(R.id.newcustomerll);

        createmyprofile=(LinearLayout)findViewById(R.id.createmyprofile);
        editmyprofile=(LinearLayout)findViewById(R.id.editmyprofile);


        stringRequest=new StringRequest(Request.Method.POST, isregisteredinMatrimony, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String flag=jsonObject.getString("success");
                    if(flag.equals("1")){

                    }else{

                                            }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Selectionactivity.this, "Something went wrong. Please try again", Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "");
                params.put("password","" );
                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(stringRequest);

        createmyprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocreateprofile = new Intent(Selectionactivity.this, MainActivity.class);
                startActivity(gotocreateprofile);
            }
        });

        editmyprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocreateprofile = new Intent(Selectionactivity.this, Editmyprofile.class);
                startActivity(gotocreateprofile);
            }
        });


        OldCustomerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GotoSelectionActivity = new Intent(Selectionactivity.this, showlist.class);
                GotoSelectionActivity.putExtra("gender","M");
                startActivity(GotoSelectionActivity);

            }
        });

        NewcustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GotoSelectionActivity=new Intent(Selectionactivity.this,showlist.class);
                GotoSelectionActivity.putExtra("gender","F");
                startActivity(GotoSelectionActivity);

            }
        });



    }
}
