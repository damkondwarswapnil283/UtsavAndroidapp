package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String usernameSt,passwordSt,firstnameSt,middlenameSt,lastnameSt,dateofbirthSt,occupatioSt,aboutmeSt,maritalstatusSt,
            firstgotraSt,secondgotraSt,emailidSt,resiaddSt,permaddSt,contactnumberSt,heightSt,bloodgroupSt,complexionSt,
            educationSt, annualincomeSt,mothertonugeSt,birthnameSt,birthtimeSt,birthplaceSt,fathersnameSt,occupation1St,mothersnameSt,
            occupation2St, noofbrothersSt,brotherdetailsSt,noofsistersSt,sisterdetailsSt,qualificationSt,annualincome1St,
            agerangeSt,height1St,occupation3St,preferredcitySt;
    EditText usernameEt,passwordEt,firstnameEt,middlenameEt,lastnameEt,dateofbirthEt,occupatioEt,aboutmeEt,maritalstatusEt,
            firstgotraEt,secondgotraEt,emailidEt,resiaddEt,permaddEt,contactnumberEt,heightEt,bloodgroupEt,complexionEt,educationEt,
            annualincomeEt,mothertonugeEt,birthnameEt,birthtimeEt,birthplaceEt,fathersnameEt,occupation1Et,mothersnameEt,occupation2Et,
            noofbrothersEt,brotherdetailsEt,noofsistersEt,sisterdetailsEt,qualificationEt,annualincome1Et,
            agerangeEt,height1Et,occupation3Et,preferredcityEt;

    JSONObject jsonObject;

    Button submitbt;

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
        dateofbirthEt=(EditText)findViewById(R.id.dateofbirth);
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


        ///




submitbt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        usernameSt=usernameEt.getText().toString();
        passwordSt=passwordEt.getText().toString();
        firstnameSt=firstnameEt.getText().toString();
        middlenameSt=middlenameEt.getText().toString();
        lastnameSt=lastnameEt.getText().toString();
        dateofbirthSt=dateofbirthEt.getText().toString();
        occupatioSt=occupatioEt.getText().toString();
        aboutmeSt=aboutmeEt.getText().toString();
        maritalstatusSt=maritalstatusEt.getText().toString();
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

        } catch (JSONException e) {
            e.printStackTrace();
        }






    }
});







    }
}
