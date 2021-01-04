package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CommunityMode extends AppCompatActivity {
Button addcommdata,editcomdata,viewcomdata;
StringRequest stringRequest;
String getid,isregistered="http://greenleafpureveg.in/utsavapplication/iscommexist.php";
ProgressBar selectprogress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_mode);
        addcommdata=(Button)findViewById(R.id.addcomdata);
        editcomdata=(Button)findViewById(R.id.editcomdata);
        viewcomdata=(Button)findViewById(R.id.viewcommdata);
        selectprogress=(ProgressBar)findViewById(R.id.selectprogress);
        getid=getIntent().getExtras().getString("id");



        stringRequest=new StringRequest(Request.Method.POST, isregistered, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String flag=jsonObject.getString("success");

                    if(flag.trim().equals("1")){
                        selectprogress.setVisibility(View.GONE);
                        addcommdata.setVisibility(View.GONE);
                        editcomdata.setVisibility(View.VISIBLE);

                    }else{
                        selectprogress.setVisibility(View.GONE);
                        editcomdata.setVisibility(View.GONE);
                       addcommdata.setVisibility(View.VISIBLE);
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


        addcommdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologinscreen=new Intent(CommunityMode.this,Createcommunity.class);
                gotologinscreen.putExtra("id",getid);
                startActivity(gotologinscreen);
            }
        });


        editcomdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologinscreen=new Intent(CommunityMode.this,Editcommunity.class);
                gotologinscreen.putExtra("id",getid);
                startActivity(gotologinscreen);
            }
        });


        viewcomdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologinscreen=new Intent(CommunityMode.this,showlist.class);
                gotologinscreen.putExtra("gender","X");
                startActivity(gotologinscreen);
            }
        });

    }
}
