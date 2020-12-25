package com.example.swapnil.ekycapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Showuser extends AppCompatActivity {
    String usernameSt,passwordSt,firstnameSt,middlenameSt,lastnameSt,dateofbirthSt,occupatioSt,aboutmeSt,maritalstatusSt,
            firstgotraSt,secondgotraSt,emailidSt,resiaddSt,permaddSt,contactnumberSt,heightSt,bloodgroupSt,complexionSt,
            educationSt, annualincomeSt,mothertonugeSt,birthnameSt,birthtimeSt,birthplaceSt,fathersnameSt,occupation1St,mothersnameSt,
            occupation2St, noofbrothersSt,brotherdetailsSt,noofsistersSt,sisterdetailsSt,qualificationSt,annualincome1St,avantakst,mamaavantakst,
            agerangeSt,height1St,occupation3St,preferredcitySt,chauphalst;
    TextView usernameEt,passwordEt,firstnameEt,middlenameEt,lastnameEt,dateofbirthEt,occupatioEt,aboutmeEt,maritalstatusEt,
            firstgotraEt,secondgotraEt,emailidEt,resiaddEt,permaddEt,contactnumberEt,heightEt,bloodgroupEt,complexionEt,educationEt,
            annualincomeEt,mothertonugeEt,birthnameEt,birthtimeEt,birthplaceEt,fathersnameEt,occupation1Et,mothersnameEt,occupation2Et,
            noofbrothersEt,brotherdetailsEt,noofsistersEt,sisterdetailsEt,qualificationEt,annualincome1Et,mamaavantek,
            agerangeEt,height1Et,occupation3Et,preferredcityEt,avataltext,chauphalaTxt;
    JSONObject jsonObject;
    private NetworkImageView mNetworkImageView;
    private ImageLoader mImageLoader;
    StringRequest stringRequest;
    String genderSt,keystring,getdatabyidurl="http://greenleafpureveg.in/utsavapplication/getdatabyid.php",selfkey;
    Button returnhomebutton;
    ImageLoader imageLoader;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showuser);

        progressBar=(ProgressBar)findViewById(R.id.showuserprogress);
        returnhomebutton=(Button)findViewById(R.id.retuenbtn);
        chauphalaTxt=(TextView)findViewById(R.id.chauphalashow);
        passwordEt=(TextView)findViewById(R.id.inputpassword);
        firstnameEt=(TextView)findViewById(R.id.firstname);
        middlenameEt=(TextView)findViewById(R.id.middlename);
        lastnameEt=(TextView)findViewById(R.id.lastname);
        dateofbirthEt=(TextView)findViewById(R.id.dateofbirth);
        occupatioEt=(TextView)findViewById(R.id.occupation);
        aboutmeEt=(TextView)findViewById(R.id.aboutme);
        maritalstatusEt=(TextView)findViewById(R.id.maritalstatus);
        firstgotraEt=(TextView)findViewById(R.id.firstgotra);
        secondgotraEt=(TextView)findViewById(R.id.secondgotra);
        emailidEt=(TextView)findViewById(R.id.emailid);
        resiaddEt=(TextView)findViewById(R.id.resiadd);
        permaddEt=(TextView)findViewById(R.id.permadd);
        mNetworkImageView=(NetworkImageView)findViewById(R.id.candidateimage);
        contactnumberEt=(TextView)findViewById(R.id.contact1);
        heightEt=(TextView)findViewById(R.id.height);
        bloodgroupEt=(TextView)findViewById(R.id.bloodgroup);
        complexionEt=(TextView)findViewById(R.id.complexion);
        educationEt=(TextView)findViewById(R.id.education);
        annualincomeEt=(TextView)findViewById(R.id.annualincome);
        mothertonugeEt=(TextView)findViewById(R.id.mothertonung);
        birthnameEt=(TextView)findViewById(R.id.birthname);
        birthtimeEt=(TextView)findViewById(R.id.birthtime);
        birthplaceEt=(TextView)findViewById(R.id.birthplace);
        fathersnameEt=(TextView)findViewById(R.id.fathername);
        occupation1Et=(TextView)findViewById(R.id.occupation1);
        mothersnameEt=(TextView)findViewById(R.id.mothername);
        occupation2Et=(TextView)findViewById(R.id.occupation2);
        noofbrothersEt=(TextView)findViewById(R.id.noofbrothers);
        brotherdetailsEt=(TextView)findViewById(R.id.brothersdetails);
        noofsistersEt=(TextView)findViewById(R.id.noofsisters);
        sisterdetailsEt=(TextView)findViewById(R.id.sistersdetails);
        qualificationEt=(TextView)findViewById(R.id.qualification);
        annualincome1Et=(TextView)findViewById(R.id.annualincome1);
        agerangeEt=(TextView)findViewById(R.id.agerange);
        height1Et=(TextView)findViewById(R.id.height1);
        occupation3Et=(TextView)findViewById(R.id.occupation3);
        preferredcityEt=(TextView)findViewById(R.id.preferredcity);
        avataltext=(TextView)findViewById(R.id.showavantak);
        mamaavantek=(TextView)findViewById(R.id.showmamaavantak);
      imageLoader = AppController.getInstance().getImageLoader();


        keystring=getIntent().getExtras().getString("id");
        selfkey=getIntent().getExtras().getString("selfkey");

        stringRequest=new StringRequest(Request.Method.POST, getdatabyidurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();
                try {
                    progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject=new JSONObject(response);
                    JSONObject individualjsonobject;
                    String flag=jsonObject.getString("success");
                    if(flag.equals("1")){

                        try {
                            jsonObject=new JSONObject(response);



                            individualjsonobject=new JSONObject(jsonObject.getString("jsondata"));


                            firstnameSt=individualjsonobject.getString("firstname");

                            middlenameSt=individualjsonobject.getString("middlename");

                            lastnameSt=individualjsonobject.getString("lastname");

                            dateofbirthSt=individualjsonobject.getString("dateofbirth");

                            occupatioSt=individualjsonobject.getString("occupatio");

                            aboutmeSt=individualjsonobject.getString("aboutme");

                            maritalstatusSt=individualjsonobject.getString("maritalstatus");


                            firstgotraSt=individualjsonobject.getString("firstgotra");



                            emailidSt=individualjsonobject.getString("emailid");

                            resiaddSt=individualjsonobject.getString("resiadd");

                            permaddSt=individualjsonobject.getString("nativevillage");

                            contactnumberSt=individualjsonobject.getString("contactnumber");

                            heightSt=individualjsonobject.getString("height");

                            bloodgroupSt=individualjsonobject.getString("bloodgroup");

                            //complexionSt=individualjsonobject.getString("complexionSt");

                            educationSt=individualjsonobject.getString("education");

                            annualincomeSt=individualjsonobject.getString("annualincome");

                           // mothertonugeSt=individualjsonobject.getString("mothertonugeSt");

                          //  birthnameSt=individualjsonobject.getString("birthnameSt");

                         //   birthtimeSt=individualjsonobject.getString("birthtimeSt");

                          //  birthplaceSt=individualjsonobject.getString("birthplaceSt");

                            fathersnameSt=individualjsonobject.getString("middlename");

                            occupation1St=individualjsonobject.getString("fathersoccupation");

                            mothersnameSt=individualjsonobject.getString("mothersname");

                            noofbrothersSt=individualjsonobject.getString("noofbrothers");

                            brotherdetailsSt=individualjsonobject.getString("brotherdetails");

                            noofsistersSt=individualjsonobject.getString("noofsisters");

                            sisterdetailsSt=individualjsonobject.getString("sisterdetails");

                            qualificationSt=individualjsonobject.getString("qualification");

                            annualincome1St=individualjsonobject.getString("annualincome");

                            agerangeSt=individualjsonobject.getString("agerangeexpected");

                            height1St=individualjsonobject.getString("heightexpected");

                            occupation3St=individualjsonobject.getString("occupationexpected");

                            preferredcitySt=individualjsonobject.getString("preferredcitySt");
                            avantakst=individualjsonobject.getString("avantak");
                            chauphalst=individualjsonobject.getString("chaukhala");
                            mamaavantakst=individualjsonobject.getString("mamavantak");


                            String imageurl=jsonObject.getString("image");
                            mNetworkImageView.setImageUrl(imageurl,imageLoader);


                            firstnameEt.setText("Full Name:- "+firstnameSt +" "+ middlenameSt + " "+ lastnameSt);
                            dateofbirthEt.setText("Date of birth:- "+dateofbirthSt);
                            occupatioEt.setText("Occupation:- "+occupatioSt);
                            aboutmeEt.setText("About Me:- "+aboutmeSt);

                            mamaavantek.setText("Mama Avantak:- "+mamaavantakst);

                            if(maritalstatusSt.equals("N")){
                                maritalstatusSt="Not Married";
                            }else if(maritalstatusSt.equals("M")){
                                maritalstatusSt="Married";
                            }else if(maritalstatusSt.equals("D")){
                                maritalstatusSt="Divorced";
                            }else if(maritalstatusSt.equals("W")){
                                maritalstatusSt="Widow";
                            }




                            if(chauphalst.equals("V")){
                                chauphalst="Vagad";
                            }else if(chauphalst.equals("C")){
                                chauphalst="Chappan";
                            }else if(chauphalst.equals("B")){
                                chauphalst="Baran";
                            }else if(chauphalst.equals("CH")){
                                chauphalst="Chansath";
                            }
                            maritalstatusEt.setText("Marital Status:- "+maritalstatusSt);
                            firstgotraEt.setText("First Gotra:-"+firstgotraSt);
//                            secondgotraEt.setText(secondgotraSt);
                            emailidEt.setText("Email:- "+emailidSt);
                            chauphalaTxt.setText("Chauphala:-"+chauphalst);
                            resiaddEt.setText("Ressidance Address:- "+resiaddSt);
                            permaddEt.setText("Permanent Address:- "+permaddSt);
                            contactnumberEt.setText("Contact Number:- "+contactnumberSt);
                            heightEt.setText("Height:- "+heightSt);
                            bloodgroupEt.setText("Blood Group:- "+bloodgroupSt);
                            //complexionEt.setText(complexionSt);
                            educationEt.setText("Education:- "+educationSt);
                            annualincomeEt.setText("Annual Income:- "+annualincomeSt);
                            avataltext.setText("Avantak:- "+avantakst);
                            fathersnameEt.setText("Father Name:- "+fathersnameSt);
                            occupation1Et.setText("Occupation:- "+occupation1St);
                            mothersnameEt.setText("Mother`s Name:- "+mothersnameSt);
                            occupation2Et.setText("Occupation:- "+individualjsonobject.getString("mothersoccupation"));
                            noofbrothersEt.setText("No of Brothers:- "+noofbrothersSt);
                            brotherdetailsEt.setText("Brother Details:- "+brotherdetailsSt);
                            noofsistersEt.setText("No of sisters:- "+noofsistersSt);
                            sisterdetailsEt.setText("Sisters Details:- "+sisterdetailsSt);
                            qualificationEt.setText("Qualification:- "+qualificationSt);
                            annualincome1Et.setText("Annual Income:- "+annualincome1St);
                            agerangeEt.setText("Age range:- "+agerangeSt);
                            height1Et.setText("Height:- "+height1St);
                            occupation3Et.setText("Occupation:- "+occupation3St);
                            preferredcityEt.setText("Prefered City:- "+preferredcitySt);
                        } catch (JSONException e) {
                            Toast.makeText(Showuser.this, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    Log.e("Individual Resp",response);
                    }else{

                    }
                } catch (JSONException e) {
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Showuser.this, "Something went wrong. Please try again", Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userid", keystring);
                return params;
            }

        };

       /* */



        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

AppController.getInstance().addToRequestQueue(stringRequest);


        returnhomebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologinscreen=new Intent(Showuser.this,Selectionactivity.class);
                gotologinscreen.putExtra("id",selfkey);
                startActivity(gotologinscreen);
            }
        });



    }
}
