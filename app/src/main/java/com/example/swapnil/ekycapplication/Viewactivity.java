package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
    String addcomment,getid,getdataurl="http://greenleafpureveg.in/utsavapplication/getdatabyidcomm.php",addcommunityurl="http://greenleafpureveg.in/utsavapplication/updatecomdata.php";

    JSONObject jsonObject,infojsondata;
    TextView professionSpin,businessSpin,bloodgroupSpin,ageText,maritalText,childText,gendertxt,chauphalatxt;


    TextView addcommenttxt;
    StringRequest stringRequest;
    TextView firstnameEt,middlenameEt,lastnameEt,avantakEt,chaukhalaEt,bloodgrEt,occuptEt,ressiEt,nativeaddEt,
            contactnumEt,qualificationEt;
    String[] business=new String[]{"None","Restaurant","Daily needs","Kirana","Automobile","Others"};
    String[] profession=new String[]{"Teacher","Professor","Doctor","Engineer","Goverment Job","Lawyer","Others"};
    String[] bloodgroup=new String[]{"A+","A-","B+","B-","AB+","AB-","o+","o-"};
    RadioButton genderMale,genderFemale,vagadRb,chappanRb,baranRb,chansathRb,widowerRb;
    ProgressBar mainProgress,getdataprogress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_viewactivity);

        firstnameEt=(TextView)findViewById(R.id.firstname);
        middlenameEt=(TextView)findViewById(R.id.middlename);
        lastnameEt=(TextView)findViewById(R.id.lastname);
        avantakEt=(TextView)findViewById(R.id.avantak);
        addcommenttxt=(TextView)findViewById(R.id.addcommenttxt);
        ressiEt=(TextView)findViewById(R.id.resiadd);
        nativeaddEt=(TextView)findViewById(R.id.permadd);
        contactnumEt=(TextView)findViewById(R.id.contact1);
        qualificationEt=(TextView)findViewById(R.id.qualification);
        businessSpin=(TextView) findViewById(R.id.business);
        professionSpin=(TextView)findViewById(R.id.profession);
        bloodgroupSpin=(TextView)findViewById(R.id.bloodgroup);

        ageText=(TextView)findViewById(R.id.agetext);

        maritalText=(TextView)findViewById(R.id.statustext);

        childText=(TextView)findViewById(R.id.childstatus) ;

        bloodgroupSpin=(TextView)findViewById(R.id.bloodgroup);


        mainProgress=(ProgressBar)findViewById(R.id.mainprogressbar);
        getdataprogress=(ProgressBar)findViewById(R.id.waitingprogress);
        gendertxt=(TextView)findViewById(R.id.gendertxt);
        chauphalatxt=(TextView)findViewById(R.id.chauphalatext);




        getid=getIntent().getExtras().getString("id","1");


        stringRequest=new StringRequest(Request.Method.POST, getdataurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getdataprogress.setVisibility(View.GONE);


                try {

                    JSONObject jsonObject=new JSONObject(response);
                    infojsondata=new JSONObject(jsonObject.getString("jsondata"));

                    if(infojsondata.getString("type").equals("M")){

                        gendertxt.setText("Gender:- Male ");
                    }else{

                        gendertxt.setText("Gender:- Female ");
                    }

                    firstnameEt.setText("Firstname:- "+infojsondata.getString("firstname"));
                    middlenameEt.setText("Middlename:- "+infojsondata.getString("middlename"));
                    lastnameEt.setText("Lastname:- "+infojsondata.getString("lastname"));
                    avantakEt.setText("Avantak:- "+infojsondata.getString("avantak"));


                    if(infojsondata.getString("chaukhala").equals("V")){

                        chauphalatxt.setText("Chaukhala:- Vagad ");
                    }else if(infojsondata.getString("chaukhala").equals("C")){

                        chauphalatxt.setText("Chaukhala:- Chappan ");
                    }else if(infojsondata.getString("chaukhala").equals("B")){

                        chauphalatxt.setText("Chaukhala:- Baran");
                    }else {

                        chauphalatxt.setText("Chaukhala:- Chansath ");
                    }



                    bloodgroupSpin.setText("Bloodgroup:- "+infojsondata.getString("bloodgroup"));
                    professionSpin.setText("Occupation:-"+infojsondata.getString("occupatio"));
                    businessSpin.setText("Business:- "+infojsondata.getString("dateofbirth"));

                    qualificationEt.setText("Qualification:- "+infojsondata.getString("qualification"));
                    ressiEt.setText("Residential Address:- "+infojsondata.getString("aboutme"));
                    nativeaddEt.setText("Native address:- "+infojsondata.getString("nativeaddress"));
                    contactnumEt.setText("Contact number:- "+infojsondata.getString("contactnumber"));

                    ageText.setText("Age:- "+infojsondata.getString("ageofuser"));
                    maritalText.setText("Marital Status:- "+infojsondata.getString("maritalstatus"));
                    childText.setText("Boy :- "+infojsondata.getString("boynum")+", Girl:- "+infojsondata.getString("girnum"));
                    addcommenttxt.setText(infojsondata.getString("morecommenst"));
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
