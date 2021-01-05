package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class Editcommunity extends AppCompatActivity {
    String firstnameSt,middlenameSt,lastnameSt,avantak_string,chaukhalst,bloodgrSt,getid,getdataurl="http://greenleafpureveg.in/utsavapplication/getdatabyidcomm.php",
            bussinessStr,professionStr,ressiStr,nativeaddStr,contactnumStr,qualificationStr,genderStr,addcommunityurl="http://greenleafpureveg.in/utsavapplication/updatecomdata.php";
  String agestr,maritalstatusstr,boystr,girlstr,imageurl;
    JSONObject jsonObject,infojsondata;
    Spinner professionSpin,businessSpin,bloodgroupSpin;
    Button submitbtn;
    StringRequest stringRequest;
    EditText firstnameEt,middlenameEt,lastnameEt,avantakEt,chaukhalaEt,bloodgrEt,occuptEt,ressiEt,nativeaddEt,
            contactnumEt,qualificationEt;
    String[] business=new String[]{"None","Restaurant","Daily needs","Kirana","Automobile","Others"};
    String[] profession=new String[]{"Teacher","Professor","Doctor","Engineer","Goverment Job","Lawyer","Others"};
    String[] bloodgroup=new String[]{"A+","A-","B+","B-","AB+","AB-","o+","o-"};
    String[] childrenaccount=new String[]{"0","1","2","3"};
    String[] agerange=new String[]{"18 or leass than 18 years","Above 18 years"};
    RadioButton genderMale,genderFemale,vagadRb,chappanRb,baranRb,chansathRb,widowerRb;
    ProgressBar mainProgress,getdataprogress;
    TextView childtext;
    LinearLayout childlayout;
    Spinner boyspin,girlspin,agespin;
    RadioGroup maritalstatus;
    RadioButton marriedRb,nevermarriedRb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editcommunity);
        firstnameEt=(EditText)findViewById(R.id.firstname);
        middlenameEt=(EditText)findViewById(R.id.middlename);
        lastnameEt=(EditText)findViewById(R.id.lastname);
        avantakEt=(EditText)findViewById(R.id.avantak);

        ressiEt=(EditText)findViewById(R.id.resiadd);
        nativeaddEt=(EditText)findViewById(R.id.permadd);
        contactnumEt=(EditText)findViewById(R.id.contact1);
        qualificationEt=(EditText)findViewById(R.id.qualification);
        businessSpin=(Spinner)findViewById(R.id.business);
        professionSpin=(Spinner)findViewById(R.id.profession);
        bloodgroupSpin=(Spinner)findViewById(R.id.bloodgroup);
        submitbtn=(Button)findViewById(R.id.submitbt);
        marriedRb=(RadioButton)findViewById(R.id.married);
        nevermarriedRb=(RadioButton)findViewById(R.id.nevermarried);

        vagadRb=(RadioButton)findViewById(R.id.vagad);
        chappanRb=(RadioButton)findViewById(R.id.chappan);
        baranRb=(RadioButton)findViewById(R.id.baran);
        chansathRb=(RadioButton)findViewById(R.id.chansath);
        genderMale=(RadioButton)findViewById(R.id.male);
        genderFemale=(RadioButton)findViewById(R.id.female);
        mainProgress=(ProgressBar)findViewById(R.id.mainprogressbar);
        getdataprogress=(ProgressBar)findViewById(R.id.waitingprogress);
        boyspin=(Spinner)findViewById(R.id.boycount);
        girlspin=(Spinner)findViewById(R.id.girlcount);
        agespin=(Spinner)findViewById(R.id.ageofuser);
        maritalstatus=(RadioGroup)findViewById(R.id.maritalstatusgroup);
        childtext=(TextView)findViewById(R.id.childtext);
        childlayout=(LinearLayout)findViewById(R.id.childlayout);

        getid=getIntent().getExtras().getString("id","0");


        final ArrayAdapter<String> businessadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, business);
        final ArrayAdapter<String> professionadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, profession);
        final ArrayAdapter<String> bloodgrouoadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bloodgroup);
        final ArrayAdapter<String> childrenadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, childrenaccount);
        final ArrayAdapter<String> ageadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, agerange);

        boyspin.setAdapter(childrenadapter);
        girlspin.setAdapter(childrenadapter);
        agespin.setAdapter(ageadapter);
        businessSpin.setAdapter(businessadapter);
        professionSpin.setAdapter(professionadapter);
        bloodgroupSpin.setAdapter(bloodgrouoadapter);

        maritalstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Toast.makeText(Editcommunity.this, checkedId+"", Toast.LENGTH_SHORT).show();
                if(marriedRb.isChecked()==true){
                    childtext.setVisibility(View.VISIBLE);
                    childlayout.setVisibility(View.VISIBLE);
                }else{
                    childtext.setVisibility(View.GONE);
                    childlayout.setVisibility(View.GONE);
                }
                Log.e("Id",checkedId+"");
            }
        });



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

                    int spinnerPositionbg = bloodgrouoadapter.getPosition(infojsondata.getString("bloodgroup"));
                    bloodgroupSpin.setSelection(spinnerPositionbg);

                    int spinnerPositionbu = businessadapter.getPosition(infojsondata.getString("occupatio"));
                    businessSpin.setSelection(spinnerPositionbu);

                    int spinnerPosition = professionadapter.getPosition(infojsondata.getString("dateofbirth"));
                    professionSpin.setSelection(spinnerPosition);

                    int spinnerPositionage = ageadapter.getPosition(infojsondata.getString("ageofuser"));
                    agespin.setSelection(spinnerPositionage);

                    int spinnerPositionboy = childrenadapter.getPosition(infojsondata.getString("boynum"));
                    boyspin.setSelection(spinnerPositionboy);

                    int spinnerPositiongirl = childrenadapter.getPosition(infojsondata.getString("girnum"));
                    girlspin.setSelection(spinnerPositiongirl);

                    if(infojsondata.getString("maritalstatus").equals("Married")){
                        marriedRb.setChecked(true);
                    }else {
                        nevermarriedRb.setChecked(true);
                    }

                    qualificationEt.setText(infojsondata.getString("qualification"));
                    ressiEt.setText(infojsondata.getString("aboutme"));
                    nativeaddEt.setText(infojsondata.getString("nativeaddress"));
                    contactnumEt.setText(infojsondata.getString("contactnumber"));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Editcommunity.this, "Exception "+e.toString(), Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getdataprogress.setVisibility(View.GONE);
                Toast.makeText(Editcommunity.this, "Something went wrong. Please try again", Toast.LENGTH_LONG).show();
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


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainProgress.setVisibility(View.VISIBLE);
                submitbtn.setVisibility(View.GONE);
                if(genderMale.isChecked()){
                    genderStr="M";
                    imageurl="http://greenleafpureveg.in/utsavapplication/newmale.png";
                }else{
                    genderStr="F";
                    imageurl="http://greenleafpureveg.in/utsavapplication/newfemale.png";
                }
                firstnameSt=firstnameEt.getText().toString().replace("\"","").replace("\'","");
                middlenameSt=middlenameEt.getText().toString().replace("\"","").replace("\'","");
                lastnameSt=lastnameEt.getText().toString().replace("\"","").replace("\'","");
                avantak_string=avantakEt.getText().toString().replace("\"","").replace("\'","");
                if(vagadRb.isChecked()){
                    chaukhalst="V";
                }else if(chappanRb.isChecked()){
                    chaukhalst="C";
                }else if(baranRb.isChecked()){
                    chaukhalst="B";
                }else if(chansathRb.isChecked()){
                    chaukhalst="CH";
                }
                bloodgrSt=bloodgroupSpin.getSelectedItem().toString().replace("\"","").replace("\'","");
                professionStr=professionSpin.getSelectedItem().toString().replace("\"","").replace("\'","");
                bussinessStr=businessSpin.getSelectedItem().toString().replace("\"","").replace("\'","");
                ressiStr=ressiEt.getText().toString();
                nativeaddStr=nativeaddEt.getText().toString();
                contactnumStr=contactnumEt.getText().toString();
                qualificationStr=qualificationEt.getText().toString();

                agestr=agespin.getSelectedItem().toString();
                if(marriedRb.isChecked()){
                    maritalstatusstr="Married";
                }else{
                    maritalstatusstr="Unmarried";
                }

                boystr=boyspin.getSelectedItem().toString();
                girlstr=girlspin.getSelectedItem().toString();
                addvalidations();

            }
        });



    }

    public void addvalidations(){
        if(genderStr.trim().equals("")){
            firstnameEt.requestFocus();
            Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show();
        }else if(firstnameSt.trim().equals("")){
            firstnameEt.requestFocus();
            Toast.makeText(this, "Firstname can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(middlenameSt.trim().equals("")){
            middlenameEt.requestFocus();
            Toast.makeText(this, "Middle Name can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(lastnameSt.trim().equals("")){
            lastnameEt.requestFocus();
            Toast.makeText(this, "Lastname can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(avantak_string.trim().equals("")){
            avantakEt.requestFocus();
            Toast.makeText(this, "Avantak can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(chaukhalst.trim().equals("")){
            avantakEt.requestFocus();
            Toast.makeText(this, "Appropriate Chaukhala must be selected", Toast.LENGTH_SHORT).show();
        }else if(bloodgrSt.trim().equals("")){
            avantakEt.requestFocus();
            Toast.makeText(this, "Appropriate Blood group must be selected", Toast.LENGTH_SHORT).show();
        }else if(bussinessStr.trim().equals("")){
            avantakEt.requestFocus();
            Toast.makeText(this, "Appropriate Bussiness option must be selected", Toast.LENGTH_SHORT).show();
        }else if(professionStr.trim().equals("")){
            avantakEt.requestFocus();
            Toast.makeText(this, "Appropriate profession must be selected", Toast.LENGTH_SHORT).show();
        }else if(ressiStr.trim().equals("")){
            avantakEt.requestFocus();
            Toast.makeText(this, "Ressidance address cant be left blank", Toast.LENGTH_SHORT).show();
        }else if(professionStr.trim().equals("")){
            avantakEt.requestFocus();
            Toast.makeText(this, "Profession address cant be left blank", Toast.LENGTH_SHORT).show();
        }else if(contactnumStr.trim().equals("")){
            avantakEt.requestFocus();
            Toast.makeText(this, "Contact number cant be left blank", Toast.LENGTH_SHORT).show();
        }else if(qualificationStr.trim().equals("")){
            avantakEt.requestFocus();
            Toast.makeText(this, "Qualification cant be left blank", Toast.LENGTH_SHORT).show();
        }else{
            createjsonobject();
        }
    }

    public void createjsonobject() {
        jsonObject = new JSONObject();

        try {


            jsonObject.put("type",genderStr);

            jsonObject.put("firstname",firstnameSt);

            jsonObject.put("middlename",middlenameSt);

            jsonObject.put("lastname",lastnameSt);

            jsonObject.put("resiadd",ressiStr);

            jsonObject.put("chaukhala",chaukhalst);

            jsonObject.put("avantak",avantak_string);

            jsonObject.put("bloodgroup",bloodgrSt);

            jsonObject.put("occupatio",bussinessStr);

            jsonObject.put("dateofbirth",professionStr);

            jsonObject.put("aboutme",ressiStr);

            jsonObject.put("nativeaddress",nativeaddStr);

            jsonObject.put("contactnumber",contactnumStr);

            jsonObject.put("qualification",qualificationStr);

            jsonObject.put("ageofuser",agestr);
            jsonObject.put("maritalstatus",maritalstatusstr);
            jsonObject.put("boynum",boystr);
            jsonObject.put("girnum",girlstr);

            jsonObject.put("image",imageurl);




            sendrequest();

        } catch (Exception e){

        }

    }

    public void sendrequest(){
        stringRequest=new StringRequest(Request.Method.POST, addcommunityurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    submitbtn.setVisibility(View.VISIBLE);
                    Log.e("Response",response);
                    mainProgress.setVisibility(View.GONE);
                    if(new JSONObject(response).getString("success").equals("1")){

                        Toast.makeText(Editcommunity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                        Intent gotologin=new Intent(Editcommunity.this,CommunityMode.class);
                        gotologin.putExtra("id",getid);
                        startActivity(gotologin);
                    }else{
                        Toast.makeText(Editcommunity.this, response, Toast.LENGTH_SHORT).show();
                        Toast.makeText(Editcommunity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    mainProgress.setVisibility(View.GONE);
                    submitbtn.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
                mainProgress.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                submitbtn.setVisibility(View.VISIBLE);
                mainProgress.setVisibility(View.GONE);
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("jsondata", String.valueOf(jsonObject));
                params.put("mobilnumber",contactnumStr );
                params.put("type", genderStr);
                params.put("forsearching", firstnameSt+" "+middlenameSt+" "+lastnameSt+" "+contactnumStr);
                params.put("firstname",firstnameSt );
                params.put("middlename", middlenameSt);
                params.put("lastname",lastnameSt );
                params.put("userid",getid );
                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(stringRequest);
    }}
