package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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
            educationSt, annualincomeSt,mothertonugeSt,birthnameSt,birthtimeSt,birthplaceSt,fathersnameSt,occupation1St,mothersnameSt,
            occupation2St, noofbrothersSt,brotherdetailsSt,noofsistersSt,sisterdetailsSt,qualificationSt,annualincome1St,
            agerangeSt,height1St,occupation3St,preferredcitySt,dnum1_string,dnum2_string,dnum3_string,dnum4_string,dnum5_string,
            dnum6_string,avantak_string,
            dnum7_string,dnum8_string,genderSt,currentcityst,getid;
    ProgressBar progressBar,mainProgress;
    ImageView testImage;
    StringRequest stringRequest;
    JSONObject infojsondata;
    ProgressBar getdataprogress;

    EditText firstnameEt,middlenameEt,lastnameEt,dateofbirthEt,occupatioEt,aboutmeEt,maritalstatusEt,
            firstgotraEt,secondgotraEt,emailidEt,resiaddEt,permaddEt,contactnumberEt,heightEt,bloodgroupEt,complexionEt,educationEt,
            annualincomeEt,mothertonugeEt,birthnameEt,birthtimeEt,birthplaceEt,fathersnameEt,occupation1Et,mothersnameEt,occupation2Et,
            noofbrothersEt,brotherdetailsEt,noofsistersEt,sisterdetailsEt,qualificationEt,annualincome1Et,avantakEt,
            agerangeEt,height1Et,occupation3Et,preferredcityEt,dnum1_et,dnum2_et,dnum3_et,dnum4_et,dnum5_et,dnum6_et,dnum7_et,dnum8_et,current_cityet;

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
    RadioButton marriedRb,nevermarriedRb,divorcedRb,widowRb,genderMale,genderFemale;
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

        contactnumberEt=(EditText)findViewById(R.id.editcontact1);
        heightEt=(EditText)findViewById(R.id.editheight);
        bloodgroupEt=(EditText)findViewById(R.id.editbloodgroup);

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
        height1Et=(EditText)findViewById(R.id.editheight1);
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
        testImage=(ImageView)findViewById(R.id.edittestdata) ;
        progressBar=(ProgressBar)findViewById(R.id.editimageprogress);
        sizemessage=(TextView)findViewById(R.id.editsizemessage);

        getid=getIntent().getExtras().getString("id");

        submitbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    Toast.makeText(Editmyprofile.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();

                   /* dnum1_et.setText("");
                    dnum2_et.setText("");
                    dnum3_et.setText("");
                    dnum4_et.setText("");
                    dnum5_et.setText("");
                    dnum6_et.setText("");type

                    dnum7_et.setText("");
                    dnum8_et.setText("");*/


        if(jsonObject.getString("jsondata").trim().equals("M")){
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
                    emailidEt.setText(infojsondata.getString("emailid"));
                    resiaddEt.setText(infojsondata.getString("resiadd"));

                    permaddEt.setText(infojsondata.getString("nativevillage"));
                    current_cityet.setText(infojsondata.getString("currentcity"));
                    contactnumberEt.setText(infojsondata.getString("contactnumber"));
                    heightEt.setText(infojsondata.getString("height"));
                    bloodgroupEt.setText(infojsondata.getString("bloodgroup"));
                    educationEt.setText(infojsondata.getString("education"));
                    annualincomeEt.setText(infojsondata.getString("annualincome"));
                    firstgotraEt.setText(infojsondata.getString("firstgotra"));

                    occupation1Et.setText(infojsondata.getString("fathersoccupation"));
                    //////////////////////////
                    mothersnameEt.setText(infojsondata.getString("mothersname"));
                    occupation2Et.setText(infojsondata.getString("mothersoccupation"));
                    noofbrothersEt.setText(infojsondata.getString("noofbrothers"));
                    brotherdetailsEt.setText(infojsondata.getString("brotherdetails"));
                    noofsistersEt.setText(infojsondata.getString("noofsisters"));
                    sisterdetailsEt.setText(infojsondata.getString("sisterdetails"));
                    qualificationEt.setText(infojsondata.getString("qualification"));
                    annualincome1Et.setText(infojsondata.getString("annualincome"));
                    agerangeEt.setText(infojsondata.getString("agerangeexpected"));
                    height1Et.setText(infojsondata.getString("heightexpected"));
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

        AppController.getInstance().addToRequestQueue(stringRequest);


    }

    public void addvalidations(){
        if(genderSt.trim().equals("")){
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
        }else if(maritalstatusSt.trim().equals("")){
            aboutmeEt.requestFocus();
            Toast.makeText(this, "Please select marital status", Toast.LENGTH_SHORT).show();
        } else if(resiaddSt.trim().equals("")){
            resiaddEt.requestFocus();
            Toast.makeText(this, "Residence address can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(permaddSt.trim().equals("")){
            permaddEt.requestFocus();
            Toast.makeText(this, "Native village(Rajsthan) can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(currentcityst.trim().equals("")){
            current_cityet.requestFocus();
            Toast.makeText(this, "Current city can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(contactnumberSt.trim().equals("")){
            contactnumberEt.requestFocus();
            Toast.makeText(this, "Contact number can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(heightSt.trim().equals("")){
            heightEt.requestFocus();
            Toast.makeText(this, "Height can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(bloodgroupSt.trim().equals("")){
            bloodgroupEt.requestFocus();
            Toast.makeText(this, "Blood Group can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(mothersnameSt.trim().equals("")){
            mothersnameEt.requestFocus();
            Toast.makeText(this, "Mother Name can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(agerangeSt.trim().equals("")){
            agerangeEt.requestFocus();
            Toast.makeText(this, "Age range can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(height1St.trim().equals("")){
            heightEt.requestFocus();
            Toast.makeText(this, "Height can`t be left blank", Toast.LENGTH_SHORT).show();
        }
        else if(dateofbirthSt.trim().equals("")){
            lastnameEt.requestFocus();
            Toast.makeText(this, "Date of birth is not appropriate", Toast.LENGTH_SHORT).show();
        }else if(maritalstatusSt.trim().equals("")){
            firstgotraEt.requestFocus();
            Toast.makeText(this, "Select Marital status", Toast.LENGTH_SHORT).show();
        }else if(firstgotraSt.trim().equals("")){
            firstgotraEt.requestFocus();
            Toast.makeText(this, "First gotra can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(imageString.trim().equals("")){
            firstgotraEt.requestFocus();
            Toast.makeText(this, "Please select image", Toast.LENGTH_SHORT).show();
        }
        else {
            createjsonobject();
        }

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


            //

            // jsonObject.put("secondgotraSt",secondgotraSt);

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

            // jsonObject.put("mothertonugeSt",mothertonugeSt);

            // jsonObject.put("birthnameSt",birthnameSt);

            // jsonObject.put("birthtimeSt",birthtimeSt);

            //   jsonObject.put("birthplaceSt",birthplaceSt);

            //   jsonObject.put("fathersnameSt",fathersnameSt);

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
                        Intent gotologin=new Intent(Editmyprofile.this,Login.class);

                        startActivity(gotologin);
                    }else{

                        Toast.makeText(Editmyprofile.this, "Something went wrong", Toast.LENGTH_SHORT).show();

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
                params.put("jsondata", String.valueOf(jsonObject));
                params.put("mobilnumber",contactnumberSt );
                params.put("type", genderSt);
                params.put("image",imageString );
                params.put("forsearching", firstnameSt+" "+middlenameSt+" "+lastnameSt+" "+contactnumberSt);
                params.put("firstname",firstnameSt );
                params.put("middlename", middlenameSt);
                params.put("lastname",lastnameSt );
                params.put("userid",getid );
                return params;
            }

        };

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

            if(imageString.length()<72000) {
                usersImg.setImageBitmap(bitmap);
                sizemessage.setText("Image Selected successfully !!");
            }else{
                usersImg.setImageResource(R.drawable.warning);

                sizemessage.setText("Please select Image with smaller size !!");
            };
            progressBar.setVisibility(View.GONE);

        }


    }
}
