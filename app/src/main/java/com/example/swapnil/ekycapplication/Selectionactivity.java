package com.example.swapnil.ekycapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

public class Selectionactivity extends Activity {
    LinearLayout OldCustomerbtn,NewcustomerBtn,createmyprofile,editmyprofile,removeprofile;
    String getid;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectionactivity);
        StringRequest stringRequest;
        String isregisteredinMatrimony="http://greenleafpureveg.in/utsavapplication/isexist.php";
        OldCustomerbtn = (LinearLayout) findViewById(R.id.oldcustomerll);
        NewcustomerBtn = (LinearLayout) findViewById(R.id.newcustomerll);
        progressBar=(ProgressBar)findViewById(R.id.progresswidget);
        createmyprofile=(LinearLayout)findViewById(R.id.createmyprofile);
        editmyprofile=(LinearLayout)findViewById(R.id.editmyprofilell);
        removeprofile=(LinearLayout)findViewById(R.id.removemeforlistinll) ;
        getid=getIntent().getExtras().getString("id");


        //Toast.makeText(this, getid+"", Toast.LENGTH_SHORT).show();

        stringRequest=new StringRequest(Request.Method.POST, isregisteredinMatrimony, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String flag=jsonObject.getString("success");

                   if(flag.trim().equals("1")){
                       progressBar.setVisibility(View.GONE);
                       editmyprofile.setVisibility(View.VISIBLE);
                       removeprofile.setVisibility(View.VISIBLE);
                    }else{
                       progressBar.setVisibility(View.GONE);
                       createmyprofile.setVisibility(View.VISIBLE);
                                            }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.toString());

               // Toast.makeText(Selectionactivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userid", getid);
                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(stringRequest);

        createmyprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocreateprofile = new Intent(Selectionactivity.this, MainActivity.class);
                gotocreateprofile.putExtra("id",getid);
                startActivity(gotocreateprofile);
            }
        });

        editmyprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoeditprofile = new Intent(Selectionactivity.this, Editmyprofile.class);
                gotoeditprofile.putExtra("id",getid);
                startActivity(gotoeditprofile);
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

        removeprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoeditprofile = new Intent(Selectionactivity.this, Removemefromlisting.class);
                gotoeditprofile.putExtra("id",getid);
                startActivity(gotoeditprofile);
            }
        });


    }
}
