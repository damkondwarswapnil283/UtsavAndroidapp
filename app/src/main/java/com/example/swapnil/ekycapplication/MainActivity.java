package com.example.swapnil.ekycapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
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

public class MainActivity extends AppCompatActivity {

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

    EditText firstnameEt,middlenameEt,lastnameEt,dateofbirthEt,occupatioEt,aboutmeEt,maritalstatusEt,
            firstgotraEt,secondgotraEt,emailidEt,resiaddEt,permaddEt,contactnumberEt,heightEt,bloodgroupEt,complexionEt,educationEt,
            annualincomeEt,mothertonugeEt,birthnameEt,birthtimeEt,birthplaceEt,fathersnameEt,occupation1Et,mothersnameEt,occupation2Et,
            noofbrothersEt,brotherdetailsEt,noofsistersEt,sisterdetailsEt,qualificationEt,annualincome1Et,avantakEt,
            agerangeEt,height1Et,occupation3Et,preferredcityEt,dnum1_et,dnum2_et,dnum3_et,dnum4_et,dnum5_et,dnum6_et,dnum7_et,dnum8_et,current_cityet;

    Bitmap bitmap;
    byte[] imagebytes;
    String imagestring="";
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

    String registerUrl="http://greenleafpureveg.in/utsavapplication/addperson.php";
    // Write a message to the database


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Edittext mapping
         */

        firstnameEt=(EditText)findViewById(R.id.firstname);
        middlenameEt=(EditText)findViewById(R.id.middlename);
        lastnameEt=(EditText)findViewById(R.id.lastname);
        genderMale=(RadioButton)findViewById(R.id.male);
        genderFemale=(RadioButton)findViewById(R.id.female);
        progressBar=(ProgressBar)findViewById(R.id.imageprogress);
        avantakEt=(EditText)findViewById(R.id.avantak);
        occupatioEt=(EditText)findViewById(R.id.occupation);
        aboutmeEt=(EditText)findViewById(R.id.aboutme);
        maritalstatusEt=(EditText)findViewById(R.id.maritalstatus);
        firstgotraEt=(EditText)findViewById(R.id.firstgotra);
        secondgotraEt=(EditText)findViewById(R.id.secondgotra);
        emailidEt=(EditText)findViewById(R.id.emailid);
        resiaddEt=(EditText)findViewById(R.id.resiadd);
        permaddEt=(EditText)findViewById(R.id.permadd);

        contactnumberEt=(EditText)findViewById(R.id.contact1);
        heightEt=(EditText)findViewById(R.id.height);
        bloodgroupEt=(EditText)findViewById(R.id.bloodgroup);
        complexionEt=(EditText)findViewById(R.id.complexion);
        educationEt=(EditText)findViewById(R.id.education);
        annualincomeEt=(EditText)findViewById(R.id.annualincome);
        mothertonugeEt=(EditText)findViewById(R.id.mothertonung);
        birthnameEt=(EditText)findViewById(R.id.birthname);
        birthtimeEt=(EditText)findViewById(R.id.birthtime);
        birthplaceEt=(EditText)findViewById(R.id.birthplace);
        fathersnameEt=(EditText)findViewById(R.id.fathername);
        occupation1Et=(EditText)findViewById(R.id.occupation1);
        mothersnameEt=(EditText)findViewById(R.id.mothername);
        occupation2Et=(EditText)findViewById(R.id.occupation2);
        noofbrothersEt=(EditText)findViewById(R.id.noofbrothers);
        brotherdetailsEt=(EditText)findViewById(R.id.brothersdetails);
        noofsistersEt=(EditText)findViewById(R.id.noofsisters);
        sisterdetailsEt=(EditText)findViewById(R.id.sistersdetails);
        qualificationEt=(EditText)findViewById(R.id.qualification);
        annualincome1Et=(EditText)findViewById(R.id.annualincome1);
        agerangeEt=(EditText)findViewById(R.id.agerange);
        height1Et=(EditText)findViewById(R.id.height1);
        occupation3Et=(EditText)findViewById(R.id.occupation3);
        preferredcityEt=(EditText)findViewById(R.id.preferredcity);
        submitbt=(Button)findViewById(R.id.submitbt);
        selectphotofromgalleryBtn=(Button)findViewById(R.id.selectphotofromgallery);
        usersImg=(ImageView)findViewById(R.id.imageView3) ;
        current_cityet=(EditText)findViewById(R.id.currentcity);



        mainProgress=(ProgressBar)findViewById(R.id.mainprogressbar);
        dnum1_et=(EditText)findViewById(R.id.num1);
        dnum2_et=(EditText)findViewById(R.id.num2);
        dnum3_et=(EditText)findViewById(R.id.num3);
        dnum4_et=(EditText)findViewById(R.id.num4);
        dnum5_et=(EditText)findViewById(R.id.num5);
        dnum6_et=(EditText)findViewById(R.id.num6);
        dnum7_et=(EditText)findViewById(R.id.num7);
        dnum8_et=(EditText)findViewById(R.id.num8);
        clearTxt=(TextView)findViewById(R.id.cleartxt);
        marriedRb=(RadioButton)findViewById(R.id.married);
        nevermarriedRb=(RadioButton)findViewById(R.id.nevermarried);
        divorcedRb=(RadioButton)findViewById(R.id.divorced);
        widowRb=(RadioButton)findViewById(R.id.widow);
        testImage=(ImageView)findViewById(R.id.testdata) ;
        progressBar=(ProgressBar)findViewById(R.id.imageprogress);
        sizemessage=(TextView)findViewById(R.id.sizemessage);

        getid=getIntent().getExtras().getString("id");

        testImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testdata();
            }
        });

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

        firstnameSt=firstnameEt.getText().toString();
        middlenameSt=middlenameEt.getText().toString();
        lastnameSt=lastnameEt.getText().toString();
        avantak_string=avantakEt.getText().toString();
        dateofbirthSt=dnum1_string+dnum2_string+" - "+dnum3_string+dnum4_string+" - "+dnum5_string+dnum6_string+dnum7_string+dnum8_string;
        occupatioSt=occupatioEt.getText().toString();
        aboutmeSt=aboutmeEt.getText().toString();
        if(nevermarriedRb.isChecked()){
            maritalstatusSt="N";
        }else if(marriedRb.isChecked()){
             maritalstatusSt="M";
        }else if(divorcedRb.isChecked()){
             maritalstatusSt="D";
         }else if(widowRb.isChecked()){
             maritalstatusSt="W";
         }
        emailidSt=emailidEt.getText().toString();
        resiaddSt=resiaddEt.getText().toString();
        avantak_string=avantakEt.getText().toString();
        //Native address
        permaddSt=permaddEt.getText().toString();
        currentcityst=current_cityet.getText().toString();
        contactnumberSt=contactnumberEt.getText().toString();
        heightSt=heightEt.getText().toString();
        bloodgroupSt=bloodgroupEt.getText().toString();
        educationSt=educationEt.getText().toString();
        annualincomeSt=annualincomeEt.getText().toString();
        firstgotraSt=firstgotraEt.getText().toString();
        //////////////Family info start
        occupation1St=occupation1Et.getText().toString();
        mothersnameSt=mothersnameEt.getText().toString();
        occupation2St=occupation2Et.getText().toString();
        noofbrothersSt=noofbrothersEt.getText().toString();
        brotherdetailsSt=brotherdetailsEt.getText().toString();
        noofsistersSt=noofsistersEt.getText().toString();
        sisterdetailsSt=sisterdetailsEt.getText().toString();

        qualificationSt=qualificationEt.getText().toString();
        annualincome1St=annualincome1Et.getText().toString();
        agerangeSt=agerangeEt.getText().toString();
        height1St=height1Et.getText().toString();
        occupation3St=occupation3Et.getText().toString();
        preferredcitySt=preferredcityEt.getText().toString();

        addvalidations();
    }
});



selectphotofromgalleryBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       SelectImage();
    }
});

    }

    public void testdata(){
        //Toast.makeText(this, imageString.length()+"", Toast.LENGTH_SHORT).show();
        dnum1_et.setText("1");
        dnum2_et.setText("2");
        dnum3_et.setText("0");
        dnum4_et.setText("3");
        dnum5_et.setText("1");
        dnum6_et.setText("9");
        dnum7_et.setText("9");
        dnum8_et.setText("2");
        genderMale.setChecked(true);
        firstnameEt.setText("firstname");
        middlenameEt.setText("1middlename");
        lastnameEt.setText("lastname");
        avantakEt.setText("avantak");
        occupatioEt.setText("occupatio");
        aboutmeEt.setText("aboutme");
        nevermarriedRb.setChecked(true);
        emailidEt.setText("emailid");
        resiaddEt.setText("resiadd");
        permaddEt.setText("permadd");
        current_cityet.setText("current_city");
        contactnumberEt.setText("contactnumber");
        heightEt.setText("height");
        bloodgroupEt.setText("bloodgroup");
        educationEt.setText("education");
        annualincomeEt.setText("annualincome");
        firstgotraEt.setText("firstgotra");
        occupation1Et.setText("occupation1");
        mothersnameEt.setText("mothersname");
        occupation2Et.setText("occupation");
        noofbrothersEt.setText("noofbrothers");
        brotherdetailsEt.setText("brotherdetails");
        noofsistersEt.setText("noofsisters");
        sisterdetailsEt.setText("sisterdetails");
        qualificationEt.setText("qualification");
        annualincome1Et.setText("annualincome1");
        agerangeEt.setText("agerangeEt");
        height1Et.setText("height1");
        occupation3Et.setText("occupation");
        preferredcityEt.setText("preferredcity");

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

                        Toast.makeText(MainActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                        Intent gotologin=new Intent(MainActivity.this,Selectionactivity.class);
                        gotologin.putExtra("id",getid);
                        startActivity(gotologin);
                    }else{

                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

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



    public void registration(String username,String Password){

                            Toast.makeText(MainActivity.this, "User Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent gotologinscreen=new Intent(MainActivity.this,Login.class);
                            startActivity(gotologinscreen);
                            finish();

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
