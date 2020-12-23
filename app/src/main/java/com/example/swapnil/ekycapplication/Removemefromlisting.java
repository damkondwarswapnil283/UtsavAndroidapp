package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class Removemefromlisting extends AppCompatActivity {
Button removeBtn;
    StringRequest stringRequest;
    String removelistingurl="http://greenleafpureveg.in/utsavapplication/removelisting.php",getid,status;
    ProgressBar progressbar;
    CheckBox checkBox,addcheckbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_removemefromlisting);
        removeBtn=(Button)findViewById(R.id.removebtn);
        progressbar=(ProgressBar)findViewById(R.id.progressBar);
        checkBox=(CheckBox)findViewById(R.id.removekistcheckbox);
        addcheckbox=(CheckBox)findViewById(R.id.addcheckbox);

        getid=getIntent().getExtras().getString("id");
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox.isChecked()){
                    status="N";
                    progressbar.setVisibility(View.VISIBLE);
                    sendrequest();
                }else if(addcheckbox.isChecked()){
                    status="Y";
                    progressbar.setVisibility(View.VISIBLE);
                    sendrequest();
                }else{
                    Toast.makeText(Removemefromlisting.this, "Please Select any one of the option", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void sendrequest(){
        stringRequest=new StringRequest(Request.Method.POST, removelistingurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("Response",response);
                    if(new JSONObject(response).getString("success").equals("1")){
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(Removemefromlisting.this, "You are successfully removed from listing", Toast.LENGTH_SHORT).show();
                        Intent gotologin=new Intent(Removemefromlisting.this,Login.class);

                        startActivity(gotologin);
                    }else{

                        Toast.makeText(Removemefromlisting.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    progressbar.setVisibility(View.GONE);
                    e.printStackTrace();
                }
                progressbar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressbar.setVisibility(View.GONE);
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userid", getid);
                params.put("status", status);

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
