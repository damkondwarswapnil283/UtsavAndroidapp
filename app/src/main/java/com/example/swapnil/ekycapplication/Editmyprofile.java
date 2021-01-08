package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Editmyprofile extends AppCompatActivity {
    String imageString="",firstnameSt,middlenameSt,lastnameSt,dateofbirthSt,occupatioSt,aboutmeSt,maritalstatusSt,
            firstgotraSt,secondgotraSt,emailidSt,resiaddSt,permaddSt,contactnumberSt,heightSt,bloodgroupSt,complexionSt,
            educationSt, annualincomeSt,mothertonugeSt,birthnameSt,birthtimeSt,birthplaceSt,fathersnameSt,occupation1St,mothersnameSt,mamaavantakSt,
            occupation2St, noofbrothersSt,brotherdetailsSt,noofsistersSt,sisterdetailsSt,qualificationSt,annualincome1St,
            agerangeSt,height1St,occupation3St,preferredcitySt,dnum1_string,dnum2_string,dnum3_string,dnum4_string,dnum5_string,
            dnum6_string,avantak_string,chaukhalst,
            dnum7_string,dnum8_string,genderSt,currentcityst,getid;
    ProgressBar progressBar,mainProgress;
    ImageView testImage;
    StringRequest stringRequest;
    JSONObject infojsondata;
    ProgressBar getdataprogress;
    Spinner professionSpin,businessSpin,bloodgroupSpin;


    EditText firstnameEt,middlenameEt,lastnameEt,dateofbirthEt,occupatioEt,aboutmeEt,maritalstatusEt,
            firstgotraEt,secondgotraEt,emailidEt,resiaddEt,permaddEt,contactnumberEt,complexionEt,educationEt,
            annualincomeEt,mothertonugeEt,birthnameEt,birthtimeEt,birthplaceEt,fathersnameEt,occupation1Et,mothersnameEt,occupation2Et,mamavantakEt,
            noofbrothersEt,brotherdetailsEt,noofsistersEt,sisterdetailsEt,qualificationEt,annualincome1Et,avantakEt,
            agerangeEt,occupation3Et,preferredcityEt,dnum1_et,dnum2_et,dnum3_et,dnum4_et,dnum5_et,dnum6_et,dnum7_et,dnum8_et,current_cityet;
    Spinner height1Et,heightEt;
    Bitmap bitmap;
    byte[] imagebytes;
    String imagestring="",getdataurl="http://greenleafpureveg.in/utsavapplication/getdatabyid.php";
    TextView clearTxt,sizemessage;
    private final int PICK_IMAGE_REQUEST = 22;
    Button selectphotofromgalleryBtn;
    JSONObject jsonObject;
    private ImageView usersImg;

    private Uri filePath;
    Button submitbt;
    RadioButton marriedRb,nevermarriedRb,divorcedRb,widowRb,widowerRb,genderMale,genderFemale,vagadRb,chappanRb,baranRb,chansathRb;
    ProgressBar progressBarimage;
    ByteArrayOutputStream bytearrayimage;

    String registerUrl="http://greenleafpureveg.in/utsavapplication/dataupdate.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmyprofile);
        firstnameEt=(EditText)findViewById(R.id.editfirstname);
        middlenameEt=(EditText)findViewById(R.id.editmiddlename);
        lastnameEt=(EditText)findViewById(R.id.editlastname);
        genderMale=(RadioButton)findViewById(R.id.editmale);
        genderFemale=(RadioButton)findViewById(R.id.editfemale);
        progressBar=(ProgressBar)findViewById(R.id.editimageprogress);
        avantakEt=(EditText)findViewById(R.id.editavantak);
        occupatioEt=(EditText)findViewById(R.id.editoccupation);
        aboutmeEt=(EditText)findViewById(R.id.editaboutme);
        getdataprogress=(ProgressBar)findViewById(R.id.getdataprogress);
        firstgotraEt=(EditText)findViewById(R.id.editfirstgotra);
        secondgotraEt=(EditText)findViewById(R.id.editsecondgotra);
        emailidEt=(EditText)findViewById(R.id.editemailid);
        resiaddEt=(EditText)findViewById(R.id.editresiadd);
        permaddEt=(EditText)findViewById(R.id.editpermadd);
        mamavantakEt=(EditText)findViewById(R.id.editmamaavantak);
        contactnumberEt=(EditText)findViewById(R.id.editcontact1);
        heightEt=(Spinner) findViewById(R.id.editheight);
        bloodgroupSpin=(Spinner)findViewById(R.id.bloodgroup);

        educationEt=(EditText)findViewById(R.id.editeducation);
        annualincomeEt=(EditText)findViewById(R.id.editannualincome);

        occupation1Et=(EditText)findViewById(R.id.editoccupation1);
        mothersnameEt=(EditText)findViewById(R.id.editmothername);
        occupation2Et=(EditText)findViewById(R.id.editoccupation2);
        noofbrothersEt=(EditText)findViewById(R.id.editnoofbrothers);
        brotherdetailsEt=(EditText)findViewById(R.id.editbrothersdetails);
        noofsistersEt=(EditText)findViewById(R.id.editnoofsisters);
        sisterdetailsEt=(EditText)findViewById(R.id.editsistersdetails);
        qualificationEt=(EditText)findViewById(R.id.editqualification);
        annualincome1Et=(EditText)findViewById(R.id.editannualincome1);
        agerangeEt=(EditText)findViewById(R.id.editagerange);
        height1Et=(Spinner) findViewById(R.id.editheight1);
        occupation3Et=(EditText)findViewById(R.id.editoccupation3);
        preferredcityEt=(EditText)findViewById(R.id.editpreferredcity);
        submitbt=(Button)findViewById(R.id.editsubmitbt);
        selectphotofromgalleryBtn=(Button)findViewById(R.id.editselectphotofromgallery);
        usersImg=(ImageView)findViewById(R.id.editimageView3) ;
        current_cityet=(EditText)findViewById(R.id.editcurrentcity);



        mainProgress=(ProgressBar)findViewById(R.id.editmainprogressbar);
        dnum1_et=(EditText)findViewById(R.id.editnum1);
        dnum2_et=(EditText)findViewById(R.id.editnum2);
        dnum3_et=(EditText)findViewById(R.id.editnum3);
        dnum4_et=(EditText)findViewById(R.id.editnum4);
        dnum5_et=(EditText)findViewById(R.id.editnum5);
        dnum6_et=(EditText)findViewById(R.id.editnum6);
        dnum7_et=(EditText)findViewById(R.id.editnum7);
        dnum8_et=(EditText)findViewById(R.id.editnum8);
        clearTxt=(TextView)findViewById(R.id.editcleartxt);
        marriedRb=(RadioButton)findViewById(R.id.editmarried);
        nevermarriedRb=(RadioButton)findViewById(R.id.editnevermarried);
        divorcedRb=(RadioButton)findViewById(R.id.editdivorced);
        widowRb=(RadioButton)findViewById(R.id.editwidow);
        widowerRb=(RadioButton)findViewById(R.id.widower) ;
        testImage=(ImageView)findViewById(R.id.edittestdata) ;
        progressBar=(ProgressBar)findViewById(R.id.editimageprogress);
        sizemessage=(TextView)findViewById(R.id.editsizemessage);

        vagadRb=(RadioButton)findViewById(R.id.editvagad);
        chappanRb=(RadioButton)findViewById(R.id.editchappan);
        baranRb=(RadioButton)findViewById(R.id.editbaran);
        chansathRb=(RadioButton)findViewById(R.id.editchansath);

        getid=getIntent().getExtras().getString("id");

        blink();

        String[] heights=new String[]{"100","101","102","103","104","105","106","107","108","109","110","111","112",
                "113","114","115","116","117","118","119","120","121","122","123","124","125","126","127","128","129"
                ,"130","131","132","133","134","135","136","137","138","139","140","141","142","143","144","145","146"
                ,"147","148","149","150","151","152","153","154","155","156","157","158","159","160","161","162","163",
                "164","165","166","167","168","169","170","171","172","173","174","175","176","177","178","179","180",
                "181","182","183","184","185","186","187","188","189","190","191","192","193","194","195","196","197",
                "198","199","200","201","202","203","204","205","206","207","208","209","210","211","212","213","214",
                "215","216","217","218","219","220","221","222","223","224","225","226","227","228","229","230","231",
                "232","233","234","235","236","237","238","239","240","241","242","243","244","245","246","247","248",
                "249","250","251","252","253","254","255","256","257","258","259","260","261","262","263","264","265",
                "266","267","268","269","270","271","272","273","274","275","276","277","278","279","280","281","282",
                "283","284","285","286","287","288","289","290","291","292","293","294","295","296","297","298","299","300",};

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, heights);
        heightEt.setAdapter(adapter);
        height1Et.setAdapter(adapter);
        String[] bloodgroup=new String[]{"A+","A-","B+","B-","AB+","AB-","o+","o-"};

        final ArrayAdapter<String> bloodgrouoadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bloodgroup);

        bloodgroupSpin.setAdapter(bloodgrouoadapter);


        dnum1_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dnum2_et.requestFocus();
            }
        });

        dnum2_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dnum3_et.requestFocus();
            }
        });

        dnum3_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dnum4_et.requestFocus();
            }
        });

        dnum4_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dnum5_et.requestFocus();
            }
        });

        dnum5_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dnum6_et.requestFocus();
            }
        });

        dnum6_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dnum7_et.requestFocus();
            }
        });

        dnum7_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dnum8_et.requestFocus();
            }
        });

        dnum8_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        clearTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dnum1_et.setText("");
                dnum2_et.setText("");
                dnum3_et.setText("");
                dnum4_et.setText("");
                dnum5_et.setText("");
                dnum6_et.setText("");
                dnum7_et.setText("");
                dnum8_et.setText("");
                dnum1_et.requestFocus();
            }
        });

        submitbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitbt.setVisibility(View.GONE);
                dnum1_string=dnum1_et.getText().toString();
                dnum2_string=dnum2_et.getText().toString();
                dnum3_string=dnum3_et.getText().toString();
                dnum4_string=dnum4_et.getText().toString();
                dnum5_string=dnum5_et.getText().toString();
                dnum6_string=dnum6_et.getText().toString();
                dnum7_string=dnum7_et.getText().toString();
                dnum8_string=dnum8_et.getText().toString();


                if(genderMale.isChecked()){
                    genderSt="M";
                }else{
                    genderSt="F";
                }

                firstnameSt=firstnameEt.getText().toString().replace("\"","").replace("\'","");
                middlenameSt=middlenameEt.getText().toString().replace("\"","").replace("\'","");
                lastnameSt=lastnameEt.getText().toString().replace("\"","").replace("\'","");
                avantak_string=avantakEt.getText().toString().replace("\"","").replace("\'","");
                dateofbirthSt=dnum1_string+dnum2_string+" - "+dnum3_string+dnum4_string+" - "+dnum5_string+dnum6_string+dnum7_string+dnum8_string;;
                occupatioSt=occupatioEt.getText().toString().replace("\"","").replace("\'","");
                aboutmeSt=aboutmeEt.getText().toString().replace("\"","").replace("\'","");
                if(nevermarriedRb.isChecked()){
                    maritalstatusSt="N";
                }else if(marriedRb.isChecked()){
                    maritalstatusSt="M";
                }else if(divorcedRb.isChecked()){
                    maritalstatusSt="D";
                }else if(widowRb.isChecked()){
                    maritalstatusSt="W";
                }else {
                    maritalstatusSt="Wi";
                }

                if(vagadRb.isChecked()){
                    chaukhalst="V";
                }else if(chappanRb.isChecked()){
                    chaukhalst="C";
                }else if(baranRb.isChecked()){
                    chaukhalst="B";
                }else if(chansathRb.isChecked()){
                    chaukhalst="CH";
                }
                emailidSt=emailidEt.getText().toString().replace("\"","").replace("\'","");
                resiaddSt=resiaddEt.getText().toString().replace("\"","").replace("\'","");
                avantak_string=avantakEt.getText().toString().replace("\"","").replace("\'","");
                mamaavantakSt=mamavantakEt.getText().toString().replace("\"","").replace("\'","");

                //Native address
                permaddSt=permaddEt.getText().toString().replace("\"","").replace("\'","");
                currentcityst=current_cityet.getText().toString().replace("\"","").replace("\'","");
                contactnumberSt=contactnumberEt.getText().toString().replace("\"","").replace("\'","");
                heightSt=heightEt.getSelectedItem().toString().replace("\"","").replace("\'","");
                bloodgroupSt=bloodgroupSpin.getSelectedItem().toString().replace("\"","").replace("\'","");
                educationSt=educationEt.getText().toString().replace("\"","").replace("\'","");
                annualincomeSt=annualincomeEt.getText().toString().replace("\"","").replace("\'","");
                firstgotraSt=firstgotraEt.getText().toString().replace("\"","").replace("\'","");
                //////////////Family info start
                occupation1St=occupation1Et.getText().toString().replace("\"","").replace("\'","");
                mothersnameSt=mothersnameEt.getText().toString().replace("\"","").replace("\'","");
                occupation2St=occupation2Et.getText().toString().replace("\"","").replace("\'","");
                noofbrothersSt=noofbrothersEt.getText().toString().replace("\"","").replace("\'","");
                brotherdetailsSt=brotherdetailsEt.getText().toString().replace("\"","").replace("\'","");
                noofsistersSt=noofsistersEt.getText().toString().replace("\"","").replace("\'","");
                sisterdetailsSt=sisterdetailsEt.getText().toString().replace("\"","").replace("\'","");

                qualificationSt=qualificationEt.getText().toString().replace("\"","").replace("\'","");
                annualincome1St=annualincome1Et.getText().toString().replace("\"","").replace("\'","");
                agerangeSt=agerangeEt.getText().toString().replace("\"","").replace("\'","");
                height1St=height1Et.getSelectedItem().toString().replace("\"","").replace("\'","");
                occupation3St=occupation3Et.getText().toString().replace("\"","").replace("\'","");
                preferredcitySt=preferredcityEt.getText().toString().replace("\"","").replace("\'","");

                addvalidations();
            }
        });

        selectphotofromgalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SelectImage();
            }
        });

        stringRequest=new StringRequest(Request.Method.POST, getdataurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getdataprogress.setVisibility(View.GONE);


                try {

                    JSONObject jsonObject=new JSONObject(response);
                    infojsondata=new JSONObject(jsonObject.getString("jsondata"));

                   /* dnum1_et.setText("");
                    dnum2_et.setText("");
                    dnum3_et.setText("");
                    dnum4_et.setText("");
                    dnum5_et.setText("");
                    dnum6_et.setText("");type

                    dnum7_et.setText("");
                    dnum8_et.setText("");*/
                    genderSt=infojsondata.getString("type");

        if(infojsondata.getString("type").equals("M")){
           genderMale.setChecked(true);
        }else{
            genderFemale.setChecked(true);
        }

                    firstnameEt.setText(infojsondata.getString("firstname"));
                    middlenameEt.setText(infojsondata.getString("middlename"));
                    lastnameEt.setText(infojsondata.getString("lastname"));
                    avantakEt.setText(infojsondata.getString("avantak"));
                    dateofbirthSt=dnum1_string+dnum2_string+" - "+dnum3_string+dnum4_string+" - "+dnum5_string+dnum6_string+dnum7_string+dnum8_string;
                    occupatioEt.setText(infojsondata.getString("occupatio"));
                    aboutmeEt.setText(infojsondata.getString("aboutme"));
        if(infojsondata.getString("maritalstatus").equals("N")){
            nevermarriedRb.setChecked(true);
        }else if(infojsondata.getString("maritalstatus").equals("M")){
            marriedRb.setChecked(true);
        }else if(infojsondata.getString("maritalstatus").equals("D")){
            divorcedRb.setChecked(true);
         }else if(infojsondata.getString("maritalstatus").equals("W")){
             widowRb.setChecked(true);
         }
        else if(infojsondata.getString("maritalstatus").equals("WI")){
            widowerRb.setChecked(true);
        }
                    if(infojsondata.getString("chaukhala").equals("V")){
                        vagadRb.setChecked(true);
                    }else if(infojsondata.getString("chaukhala").equals("C")){
                        chappanRb.setChecked(true);
                    }else if(infojsondata.getString("chaukhala").equals("B")){
                        baranRb.setChecked(true);
                    }else {
                        chansathRb.setChecked(true);
                    }


                    emailidEt.setText(infojsondata.getString("emailid"));
                    resiaddEt.setText(infojsondata.getString("resiadd"));

                    permaddEt.setText(infojsondata.getString("nativevillage"));
                    current_cityet.setText(infojsondata.getString("currentcity"));
                    contactnumberEt.setText(infojsondata.getString("contactnumber"));

                    heightEt.setSelection(adapter.getPosition(infojsondata.getString("height")));
                    bloodgroupSpin.setSelection(bloodgrouoadapter.getPosition(infojsondata.getString("bloodgroup")));

                    educationEt.setText(infojsondata.getString("education"));
                    annualincomeEt.setText(infojsondata.getString("annualincome"));
                    firstgotraEt.setText(infojsondata.getString("firstgotra"));
                    occupation1Et.setText(infojsondata.getString("fathersoccupation"));
                    mothersnameEt.setText(infojsondata.getString("mothersname"));
                    mamavantakEt.setText(infojsondata.getString("mamavantak"));
                    occupation2Et.setText(infojsondata.getString("mothersoccupation"));
                    noofbrothersEt.setText(infojsondata.getString("noofbrothers"));
                    brotherdetailsEt.setText(infojsondata.getString("brotherdetails"));
                    noofsistersEt.setText(infojsondata.getString("noofsisters"));
                    sisterdetailsEt.setText(infojsondata.getString("sisterdetails"));
                    qualificationEt.setText(infojsondata.getString("qualification"));
                    annualincome1Et.setText(infojsondata.getString("annualincome"));
                    agerangeEt.setText(infojsondata.getString("agerangeexpected"));
                    heightEt.setSelection(adapter.getPosition(infojsondata.getString("heightexpected")));
                    occupation3Et.setText(infojsondata.getString("occupationexpected"));
                    preferredcityEt.setText(infojsondata.getString("preferredcitySt"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getdataprogress.setVisibility(View.GONE);
                Toast.makeText(Editmyprofile.this, "Something went wrong. Please try again", Toast.LENGTH_LONG).show();
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

    public void addvalidations(){
        if(genderSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            firstnameEt.requestFocus();
            Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show();
        }else if(firstnameSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            firstnameEt.requestFocus();
            Toast.makeText(this, "Firstname can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(middlenameSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            middlenameEt.requestFocus();
            Toast.makeText(this, "Middle Name can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(lastnameSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            lastnameEt.requestFocus();
            Toast.makeText(this, "Lastname can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(avantak_string.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            avantakEt.requestFocus();
            Toast.makeText(this, "Avantak can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(mamaavantakSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            avantakEt.requestFocus();
            Toast.makeText(this, "Mama Avantak can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(maritalstatusSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            aboutmeEt.requestFocus();
            Toast.makeText(this, "Please select marital status", Toast.LENGTH_SHORT).show();
        } else if(resiaddSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            resiaddEt.requestFocus();
            Toast.makeText(this, "Residence address can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(permaddSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            permaddEt.requestFocus();
            Toast.makeText(this, "Native village(Rajsthan) can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(currentcityst.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            current_cityet.requestFocus();
            Toast.makeText(this, "Current city can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(contactnumberSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            contactnumberEt.requestFocus();
            Toast.makeText(this, "Contact number can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(heightSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            heightEt.requestFocus();
            Toast.makeText(this, "Height can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(bloodgroupSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            contactnumberEt.requestFocus();
            Toast.makeText(this, "Blood Group can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(mothersnameSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            mothersnameEt.requestFocus();
            Toast.makeText(this, "Mother Name can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(agerangeSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            agerangeEt.requestFocus();
            Toast.makeText(this, "Age range can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(height1St.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            heightEt.requestFocus();
            Toast.makeText(this, "Height can`t be left blank", Toast.LENGTH_SHORT).show();
        }
        else if(dateofbirthSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            lastnameEt.requestFocus();
            Toast.makeText(this, "Date of birth is not appropriate", Toast.LENGTH_SHORT).show();
        }else if(maritalstatusSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            firstgotraEt.requestFocus();
            Toast.makeText(this, "Select Marital status", Toast.LENGTH_SHORT).show();
        }else if(firstgotraSt.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            firstgotraEt.requestFocus();
            Toast.makeText(this, "First gotra can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(imageString.trim().equals("")){
            submitbt.setVisibility(View.VISIBLE);
            firstgotraEt.requestFocus();
            Toast.makeText(this, "Please select image", Toast.LENGTH_SHORT).show();
        }
        else {
            createjsonobject();
        }
       // submitbt.setVisibility(View.VISIBLE);

    }

    public void createjsonobject(){
        jsonObject=new JSONObject();
        try {


            jsonObject.put("id",getid);

            jsonObject.put("type",genderSt);

            jsonObject.put("firstname",firstnameSt);

            jsonObject.put("middlename",middlenameSt);

            jsonObject.put("lastname",lastnameSt);

            jsonObject.put("dateofbirth",dateofbirthSt);

            jsonObject.put("occupatio",occupatioSt);

            jsonObject.put("aboutme",aboutmeSt);

            jsonObject.put("maritalstatus",maritalstatusSt);

            jsonObject.put("emailid",emailidSt);

            jsonObject.put("resiadd",resiaddSt);

            jsonObject.put("nativevillage",permaddSt);

            jsonObject.put("currentcity",currentcityst);

            jsonObject.put("contactnumber",contactnumberSt);

            jsonObject.put("height",heightSt);

            jsonObject.put("bloodgroup",bloodgroupSt);

            //jsonObject.put("complexionSt",complexionSt);

            jsonObject.put("education",educationSt);

            jsonObject.put("annualincome",annualincomeSt);

            jsonObject.put("firstgotra",firstgotraSt);
            jsonObject.put("avantak",avantak_string);
            jsonObject.put("mamavantak",mamaavantakSt);
            jsonObject.put("chaukhala",chaukhalst);


            jsonObject.put("fathersoccupation",occupation1St);

            jsonObject.put("mothersname",mothersnameSt);

            jsonObject.put("mothersoccupation",occupation2St);

            jsonObject.put("noofbrothers",noofbrothersSt);

            jsonObject.put("brotherdetails",brotherdetailsSt);

            jsonObject.put("noofsisters",noofsistersSt);

            jsonObject.put("sisterdetails",sisterdetailsSt);

            jsonObject.put("qualification",qualificationSt);

            jsonObject.put("annualincomeexpected",annualincome1St);

            jsonObject.put("agerangeexpected",agerangeSt);

            jsonObject.put("heightexpected",height1St);

            jsonObject.put("occupationexpected",occupation3St);

            jsonObject.put("preferredcitySt",preferredcitySt);

            Log.e("Json Object",jsonObject.toString());
            mainProgress.setVisibility(View.VISIBLE);
            sendrequest();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendrequest(){
        stringRequest=new StringRequest(Request.Method.POST, registerUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Log.e("Response",response);
                    if(new JSONObject(response).getString("success").equals("1")){

                        Toast.makeText(Editmyprofile.this, "Registration Successfull-Please Login again", Toast.LENGTH_SHORT).show();
                        Intent gotologin=new Intent(Editmyprofile.this,Selectionactivity.class);
                         gotologin.putExtra("id",getid);
                        startActivity(gotologin);
                    }else{
                        submitbt.setVisibility(View.VISIBLE);
                        Toast.makeText(Editmyprofile.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    submitbt.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
                mainProgress.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                submitbt.setVisibility(View.VISIBLE);
                mainProgress.setVisibility(View.GONE);
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("jsondata", String.valueOf(jsonObject));
                params.put("mobilnumber",contactnumberSt );
                params.put("type", genderSt);
                params.put("image",imageString );
                params.put("forsearching", firstnameSt+" "+middlenameSt+" "+lastnameSt+" "+contactnumberSt+" "+avantak_string);
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
    }

    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);

                bytearrayimage=new ByteArrayOutputStream();
                ///here asynctask call

              ImageClass imageClass=new ImageClass();
                imageClass.execute(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }



    class ImageClass extends AsyncTask<Bitmap,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(Bitmap... bitmaps) {


            bitmap.compress(Bitmap.CompressFormat.JPEG,20,bytearrayimage);

            imagebytes = bytearrayimage.toByteArray();

            imageString = Base64.encodeToString(imagebytes,0);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(imageString.length()<92000) {
                usersImg.setImageBitmap(bitmap);
                sizemessage.setText("Image Selected successfully !!");
            }else{
                usersImg.setImageResource(R.drawable.warning);

                sizemessage.setText("Please select Image with smaller size (150KB) !!");
            };
            progressBar.setVisibility(View.GONE);

        }




    }

    private void blink(){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 500;    //in milissegunds
                try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        if(sizemessage.getVisibility() == View.VISIBLE){
                            sizemessage.setVisibility(View.INVISIBLE);
                        }else{
                            sizemessage.setVisibility(View.VISIBLE);
                        }
                        blink();
                    }
                });
            }
        }).start();
    }
}
