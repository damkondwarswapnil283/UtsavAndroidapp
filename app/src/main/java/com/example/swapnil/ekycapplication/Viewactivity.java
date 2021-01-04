package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
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

public class Viewactivity extends AppCompatActivity {
    String firstnameSt,middlenameSt,lastnameSt,avantak_string,chaukhalst,bloodgrSt,getid,getdataurl="http://greenleafpureveg.in/utsavapplication/getdatabyidcomm.php",
            bussinessStr,professionStr,ressiStr,nativeaddStr,contactnumStr,qualificationStr,genderStr,addcommunityurl="http://greenleafpureveg.in/utsavapplication/updatecomdata.php";

    JSONObject jsonObject,infojsondata;
    TextView professionSpin,businessSpin,bloodgroupSpin;

    StringRequest stringRequest;
    EditText firstnameEt,middlenameEt,lastnameEt,avantakEt,chaukhalaEt,bloodgrEt,occuptEt,ressiEt,nativeaddEt,
            contactnumEt,qualificationEt;
    String[] business=new String[]{"None","Restaurant","Daily needs","Kirana","Automobile","Others"};
    String[] profession=new String[]{"Teacher","Professor","Doctor","Engineer","Goverment Job","Lawyer","Others"};
    String[] bloodgroup=new String[]{"A+","A-","B+","B-","AB+","AB-","o+","o-"};
    RadioButton genderMale,genderFemale,vagadRb,chappanRb,baranRb,chansathRb,widowerRb;
    ProgressBar mainProgress,getdataprogress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewactivity);

        firstnameEt=(EditText)findViewById(R.id.firstname);
        middlenameEt=(EditText)findViewById(R.id.middlename);
        lastnameEt=(EditText)findViewById(R.id.lastname);
        avantakEt=(EditText)findViewById(R.id.avantak);

        ressiEt=(EditText)findViewById(R.id.resiadd);
        nativeaddEt=(EditText)findViewById(R.id.permadd);
        contactnumEt=(EditText)findViewById(R.id.contact1);
        qualificationEt=(EditText)findViewById(R.id.qualification);
        businessSpin=(TextView) findViewById(R.id.business);
        professionSpin=(TextView)findViewById(R.id.profession);
        bloodgroupSpin=(TextView)findViewById(R.id.bloodgroup);


        vagadRb=(RadioButton)findViewById(R.id.vagad);
        chappanRb=(RadioButton)findViewById(R.id.chappan);
        baranRb=(RadioButton)findViewById(R.id.baran);
        chansathRb=(RadioButton)findViewById(R.id.chansath);
        genderMale=(RadioButton)findViewById(R.id.male);
        genderFemale=(RadioButton)findViewById(R.id.female);
        mainProgress=(ProgressBar)findViewById(R.id.mainprogressbar);
        getdataprogress=(ProgressBar)findViewById(R.id.waitingprogress);





        getid=getIntent().getExtras().getString("id","1");


        stringRequest=new StringRequest(Request.Method.POST, getdataurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getdataprogress.setVisibility(View.GONE);


                try {

                    JSONObject jsonObject=new JSONObject(response);
                    infojsondata=new JSONObject(jsonObject.getString("jsondata"));

                    if(infojsondata.getString("type").equals("M")){
                        genderMale.setChecked(true);
                    }else{
                        genderFemale.setChecked(true);
                    }

                    firstnameEt.setText(infojsondata.getString("firstname"));
                    middlenameEt.setText(infojsondata.getString("middlename"));
                    lastnameEt.setText(infojsondata.getString("lastname"));
                    avantakEt.setText(infojsondata.getString("avantak"));


                    if(infojsondata.getString("chaukhala").equals("V")){
                        vagadRb.setChecked(true);
                    }else if(infojsondata.getString("chaukhala").equals("C")){
                        chappanRb.setChecked(true);
                    }else if(infojsondata.getString("chaukhala").equals("B")){
                        baranRb.setChecked(true);
                    }else {
                        chansathRb.setChecked(true);
                    }



                    bloodgroupSpin.setText("Bloodgroup:- "+infojsondata.getString("bloodgroup"));
                    professionSpin.setText("Occupation:-"+infojsondata.getString("occupatio"));
                    businessSpin.setText("Business:- "+infojsondata.getString("dateofbirth"));

                    qualificationEt.setText(infojsondata.getString("qualification"));
                    ressiEt.setText(infojsondata.getString("aboutme"));
                    nativeaddEt.setText(infojsondata.getString("nativeaddress"));
                    contactnumEt.setText(infojsondata.getString("contactnumber"));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Viewactivity.this, "Exception "+e.toString(), Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getdataprogress.setVisibility(View.GONE);
                Toast.makeText(Viewactivity.this, "Something went wrong. Please try again", Toast.LENGTH_LONG).show();
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

    }
}
