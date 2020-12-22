package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class Editmyprofile extends AppCompatActivity {
    String imageString="",firstnameSt,middlenameSt,lastnameSt,dateofbirthSt,occupatioSt,aboutmeSt,maritalstatusSt,
            firstgotraSt,secondgotraSt,emailidSt,resiaddSt,permaddSt,contactnumberSt,heightSt,bloodgroupSt,complexionSt,
            educationSt, annualincomeSt,mothertonugeSt,birthnameSt,birthtimeSt,birthplaceSt,fathersnameSt,occupation1St,mothersnameSt,
            occupation2St, noofbrothersSt,brotherdetailsSt,noofsistersSt,sisterdetailsSt,qualificationSt,annualincome1St,
            agerangeSt,height1St,occupation3St,preferredcitySt,dnum1_string,dnum2_string,dnum3_string,dnum4_string,dnum5_string,
            dnum6_string,avantak_string,
            dnum7_string,dnum8_string,genderSt,currentcityst;
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


    }
}
