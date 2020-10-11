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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String usernameSt,passwordSt,firstnameSt,middlenameSt,lastnameSt,dateofbirthSt,occupatioSt,aboutmeSt,maritalstatusSt,
            firstgotraSt,secondgotraSt,emailidSt,resiaddSt,permaddSt,contactnumberSt,heightSt,bloodgroupSt,complexionSt,
            educationSt, annualincomeSt,mothertonugeSt,birthnameSt,birthtimeSt,birthplaceSt,fathersnameSt,occupation1St,mothersnameSt,
            occupation2St, noofbrothersSt,brotherdetailsSt,noofsistersSt,sisterdetailsSt,qualificationSt,annualincome1St,
            agerangeSt,height1St,occupation3St,preferredcitySt,dnum1_string,dnum2_string,dnum3_string,dnum4_string,dnum5_string,
            dnum6_string,
            dnum7_string,dnum8_string,genderSt,imageString;
    ProgressBar progressBar;
    EditText usernameEt,passwordEt,firstnameEt,middlenameEt,lastnameEt,dateofbirthEt,occupatioEt,aboutmeEt,maritalstatusEt,
            firstgotraEt,secondgotraEt,emailidEt,resiaddEt,permaddEt,contactnumberEt,heightEt,bloodgroupEt,complexionEt,educationEt,
            annualincomeEt,mothertonugeEt,birthnameEt,birthtimeEt,birthplaceEt,fathersnameEt,occupation1Et,mothersnameEt,occupation2Et,
            noofbrothersEt,brotherdetailsEt,noofsistersEt,sisterdetailsEt,qualificationEt,annualincome1Et,
            agerangeEt,height1Et,occupation3Et,preferredcityEt,dnum1_et,dnum2_et,dnum3_et,dnum4_et,dnum5_et,dnum6_et,dnum7_et,dnum8_et;
    private StorageReference mStorageRef,storageReffront;
    Bitmap bitmap;
    byte[] imagebytes;
    String imagestring="";
    TextView clearTxt;
    private final int PICK_IMAGE_REQUEST = 22;
    Button selectphotofromgalleryBtn;
    JSONObject jsonObject;
    private ImageView usersImg;
    private FirebaseAuth mAuth;
    private Uri filePath;
    Button submitbt;
    RadioButton marriedRb,nevermarriedRb,genderMale,genderFemale;
    ProgressBar progressBarimage;
    ByteArrayOutputStream bytearrayimage;
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("data");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Edittext mapping
         */
        usernameEt=(EditText)findViewById(R.id.inputemail);
        passwordEt=(EditText)findViewById(R.id.inputpassword);
        firstnameEt=(EditText)findViewById(R.id.firstname);
        middlenameEt=(EditText)findViewById(R.id.middlename);
        lastnameEt=(EditText)findViewById(R.id.lastname);
        genderMale=(RadioButton)findViewById(R.id.male);
        genderFemale=(RadioButton)findViewById(R.id.female);
        progressBar=(ProgressBar)findViewById(R.id.imageprogress);

        occupatioEt=(EditText)findViewById(R.id.occupation);
        aboutmeEt=(EditText)findViewById(R.id.aboutme);
        maritalstatusEt=(EditText)findViewById(R.id.maritalstatus);
        firstgotraEt=(EditText)findViewById(R.id.firstgotra);
        secondgotraEt=(EditText)findViewById(R.id.secondgotra);
        emailidEt=(EditText)findViewById(R.id.emailid);
        resiaddEt=(EditText)findViewById(R.id.resiadd);
        permaddEt=(EditText)findViewById(R.id.permadd);
        usernameEt=(EditText)findViewById(R.id.inputemail);
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
        ///
        mStorageRef = FirebaseStorage.getInstance().getReference();
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
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
        progressBar=(ProgressBar)findViewById(R.id.imageprogress);


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
        usernameSt=usernameEt.getText().toString();
        passwordSt=passwordEt.getText().toString();
        firstnameSt=firstnameEt.getText().toString();
        middlenameSt=middlenameEt.getText().toString();
        lastnameSt=lastnameEt.getText().toString();
        dateofbirthSt=dnum1_string+dnum2_string+" - "+dnum3_string+dnum4_string+" - "+dnum5_string+dnum6_string+dnum7_string+dnum8_string;
        occupatioSt=occupatioEt.getText().toString();
        aboutmeSt=aboutmeEt.getText().toString();

        firstgotraSt=firstgotraEt.getText().toString();
        secondgotraSt=secondgotraEt.getText().toString();
        emailidSt=emailidEt.getText().toString();
        resiaddSt=resiaddEt.getText().toString();
        permaddSt=permaddEt.getText().toString();
        contactnumberSt=contactnumberEt.getText().toString();
        heightSt=heightEt.getText().toString();
        bloodgroupSt=bloodgroupEt.getText().toString();
        complexionSt=complexionEt.getText().toString();
        educationSt=educationEt.getText().toString();
        annualincomeSt=annualincomeEt.getText().toString();
        mothertonugeSt=mothertonugeEt.getText().toString();
        birthnameSt=birthnameEt.getText().toString();
        birthtimeSt=birthtimeEt.getText().toString();
        birthplaceSt=birthplaceEt.getText().toString();
        fathersnameSt=fathersnameEt.getText().toString();
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
        usersImg=(ImageView)findViewById(R.id.imageView3);
        testdata();
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
        Toast.makeText(this, imageString.length()+"", Toast.LENGTH_SHORT).show();
    }

    public void addvalidations(){
        if(usernameSt.trim().equals("")){
            usernameEt.requestFocus();
            Toast.makeText(this, "Username can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(passwordSt.trim().equals("")){
            passwordEt.requestFocus();
            Toast.makeText(this, "Password can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(genderSt.trim().equals("")){
            usernameEt.requestFocus();
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
        }else if(firstgotraSt.trim().equals("")){
            firstgotraEt.requestFocus();
            Toast.makeText(this, "First gotra can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(resiaddSt.trim().equals("")){
            resiaddEt.requestFocus();
            Toast.makeText(this, "Residance address can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(permaddSt.trim().equals("")){
            permaddEt.requestFocus();
            Toast.makeText(this, "Permanent address can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(contactnumberSt.trim().equals("")){
            contactnumberEt.requestFocus();
            Toast.makeText(this, "Contact number can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(heightSt.trim().equals("")){
            heightEt.requestFocus();
            Toast.makeText(this, "Height can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(bloodgroupSt.trim().equals("")){
            bloodgroupEt.requestFocus();
            Toast.makeText(this, "Blood Group can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(complexionSt.trim().equals("")){
            complexionEt.requestFocus();
            Toast.makeText(this, "Complexion can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(educationSt.trim().equals("")){
            educationEt.requestFocus();
            Toast.makeText(this, "Education can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(annualincomeSt.trim().equals("")){
            annualincome1Et.requestFocus();
            Toast.makeText(this, "Annual Income can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(mothertonugeSt.trim().equals("")){
            mothertonugeEt.requestFocus();
            Toast.makeText(this, "Mother Tongue can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(fathersnameSt.trim().equals("")){
            fathersnameEt.requestFocus();
            Toast.makeText(this, "Father Name can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(mothersnameSt.trim().equals("")){
            mothersnameEt.requestFocus();
            Toast.makeText(this, "Mother Name can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(noofbrothersSt.trim().equals("")){
            noofbrothersEt.requestFocus();
            Toast.makeText(this, "No of brother can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(noofsistersSt.trim().equals("")){
            noofsistersEt.requestFocus();
            Toast.makeText(this, "No of sister can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(qualificationSt.trim().equals("")){
            qualificationEt.requestFocus();
            Toast.makeText(this, "Qualification can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(agerangeSt.trim().equals("")){
            agerangeEt.requestFocus();
            Toast.makeText(this, "Age range can`t be left blank", Toast.LENGTH_SHORT).show();
        }else if(dateofbirthSt.trim().equals("")){
            lastnameEt.requestFocus();
            Toast.makeText(this, "Date of birth is not appropriate", Toast.LENGTH_SHORT).show();
        }else if(maritalstatusSt.trim().equals("")){
            firstgotraEt.requestFocus();
            Toast.makeText(this, "Select Marital status", Toast.LENGTH_SHORT).show();
        }else {
            createjsonobject();
        }

    }

    public void createjsonobject(){
        jsonObject=new JSONObject();
        try {
            jsonObject.put("usernameSt",usernameSt);

            jsonObject.put("passwordSt",passwordSt);

            jsonObject.put("firstnameSt",firstnameSt);

            jsonObject.put("middlenameSt",middlenameSt);

            jsonObject.put("lastnameSt",lastnameSt);

            jsonObject.put("dateofbirthSt",dateofbirthSt);

            jsonObject.put("occupatioSt",occupatioSt);

            jsonObject.put("aboutmeSt",aboutmeSt);

            jsonObject.put("maritalstatusSt",maritalstatusSt);

            jsonObject.put("firstgotraSt",firstgotraSt);

            jsonObject.put("secondgotraSt",secondgotraSt);

            jsonObject.put("emailidSt",emailidSt);

            jsonObject.put("resiaddSt",resiaddSt);

            jsonObject.put("permaddSt",permaddSt);

            jsonObject.put("contactnumberSt",contactnumberSt);

            jsonObject.put("heightSt",heightSt);

            jsonObject.put("bloodgroupSt",bloodgroupSt);

            jsonObject.put("complexionSt",complexionSt);

            jsonObject.put("educationSt",educationSt);

            jsonObject.put("annualincomeSt",annualincomeSt);

            jsonObject.put("mothertonugeSt",mothertonugeSt);

            jsonObject.put("birthnameSt",birthnameSt);

            jsonObject.put("birthtimeSt",birthtimeSt);

            jsonObject.put("birthplaceSt",birthplaceSt);

            jsonObject.put("fathersnameSt",fathersnameSt);

            jsonObject.put("occupation1St",occupation1St);

            jsonObject.put("mothersnameSt",mothersnameSt);

            jsonObject.put("noofbrothersSt",noofbrothersSt);

            jsonObject.put("brotherdetailsSt",brotherdetailsSt);

            jsonObject.put("noofsistersSt",noofsistersSt);

            jsonObject.put("sisterdetailsSt",sisterdetailsSt);

            jsonObject.put("qualificationSt",qualificationSt);

            jsonObject.put("annualincome1St",annualincome1St);

            jsonObject.put("agerangeSt",agerangeSt);

            jsonObject.put("height1St",height1St);

            jsonObject.put("occupation3St",occupation3St);

            jsonObject.put("preferredcitySt",preferredcitySt);

            myRef.child(genderSt).child(contactnumberSt).setValue(jsonObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
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


            bitmap.compress(Bitmap.CompressFormat.JPEG,5,bytearrayimage);

            imagebytes = bytearrayimage.toByteArray();

            imageString = Base64.encodeToString(imagebytes,0);

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            usersImg.setImageBitmap(bitmap);
            progressBar.setVisibility(View.GONE);

        }


    }
}
