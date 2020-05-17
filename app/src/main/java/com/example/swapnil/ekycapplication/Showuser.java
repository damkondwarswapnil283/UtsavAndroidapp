package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class Showuser extends AppCompatActivity {
    String usernameSt,passwordSt,firstnameSt,middlenameSt,lastnameSt,dateofbirthSt,occupatioSt,aboutmeSt,maritalstatusSt,
            firstgotraSt,secondgotraSt,emailidSt,resiaddSt,permaddSt,contactnumberSt,heightSt,bloodgroupSt,complexionSt,
            educationSt, annualincomeSt,mothertonugeSt,birthnameSt,birthtimeSt,birthplaceSt,fathersnameSt,occupation1St,mothersnameSt,
            occupation2St, noofbrothersSt,brotherdetailsSt,noofsistersSt,sisterdetailsSt,qualificationSt,annualincome1St,
            agerangeSt,height1St,occupation3St,preferredcitySt,individualData;
    TextView usernameEt,passwordEt,firstnameEt,middlenameEt,lastnameEt,dateofbirthEt,occupatioEt,aboutmeEt,maritalstatusEt,
            firstgotraEt,secondgotraEt,emailidEt,resiaddEt,permaddEt,contactnumberEt,heightEt,bloodgroupEt,complexionEt,educationEt,
            annualincomeEt,mothertonugeEt,birthnameEt,birthtimeEt,birthplaceEt,fathersnameEt,occupation1Et,mothersnameEt,occupation2Et,
            noofbrothersEt,brotherdetailsEt,noofsistersEt,sisterdetailsEt,qualificationEt,annualincome1Et,
            agerangeEt,height1Et,occupation3Et,preferredcityEt;
    JSONObject jsonObject;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showuser);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        usernameEt=(TextView)findViewById(R.id.inputemail);
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
        usernameEt=(TextView)findViewById(R.id.inputemail);
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



        try {
            usernameSt=jsonObject.getString("usernameSt");

            passwordSt=jsonObject.getString("passwordSt");

            firstnameSt=jsonObject.getString("firstnameSt");

            middlenameSt=jsonObject.getString("middlenameSt");

            lastnameSt=jsonObject.getString("lastnameSt");

            dateofbirthSt=jsonObject.getString("dateofbirthSt");

            occupatioSt=jsonObject.getString("occupatioSt");

            aboutmeSt=jsonObject.getString("aboutmeSt");

            maritalstatusSt=jsonObject.getString("maritalstatusSt");

            firstgotraSt=jsonObject.getString("firstgotraSt");

            secondgotraSt=jsonObject.getString("secondgotraSt");

            emailidSt=jsonObject.getString("emailidSt");

            resiaddSt=jsonObject.getString("resiaddSt");

            permaddSt=jsonObject.getString("permaddSt");

            contactnumberSt=jsonObject.getString("contactnumberSt");

            heightSt=jsonObject.getString("heightSt");

            bloodgroupSt=jsonObject.getString("bloodgroupSt");

            complexionSt=jsonObject.getString("complexionSt");

            educationSt=jsonObject.getString("educationSt");

            annualincomeSt=jsonObject.getString("annualincomeSt");

            mothertonugeSt=jsonObject.getString("mothertonugeSt");

            birthnameSt=jsonObject.getString("birthnameSt");

            birthtimeSt=jsonObject.getString("birthtimeSt");

            birthplaceSt=jsonObject.getString("birthplaceSt");

            fathersnameSt=jsonObject.getString("fathersnameSt");

            occupation1St=jsonObject.getString("occupation1St");

            mothersnameSt=jsonObject.getString("mothersnameSt");

            noofbrothersSt=jsonObject.getString("noofbrothersSt");

            brotherdetailsSt=jsonObject.getString("brotherdetailsSt");

            noofsistersSt=jsonObject.getString("noofsistersSt");

            sisterdetailsSt=jsonObject.getString("sisterdetailsSt");

            qualificationSt=jsonObject.getString("qualificationSt");

            annualincome1St=jsonObject.getString("annualincome1St");

            agerangeSt=jsonObject.getString("agerangeSt");

            height1St=jsonObject.getString("height1St");

            occupation3St=jsonObject.getString("occupation3St");

            preferredcitySt=jsonObject.getString("preferredcitySt");

            usernameEt.setText( usernameSt);
            passwordEt.setText(passwordSt);
            firstnameEt.setText(firstnameSt);
            middlenameEt.setText(middlenameSt);
            lastnameEt.setText(lastnameSt);
            dateofbirthEt.setText(dateofbirthSt);
            occupatioEt.setText(occupatioSt);
            aboutmeEt.setText(aboutmeSt);
            maritalstatusEt.setText(maritalstatusSt);
            firstgotraEt.setText(firstgotraSt);
            secondgotraEt.setText(secondgotraSt);
            emailidEt.setText(emailidSt);
            resiaddEt.setText(resiaddSt);
            permaddEt.setText(permaddSt);
            contactnumberEt.setText(contactnumberSt);
            heightEt.setText(heightSt);
            bloodgroupEt.setText(bloodgroupSt);
            complexionEt.setText(complexionSt);
            educationEt.setText(educationSt);
            annualincomeEt.setText(annualincomeSt);
            mothertonugeEt.setText(mothertonugeSt);
            birthnameEt.setText(birthnameSt);
            birthtimeEt.setText(birthtimeSt);
            birthplaceEt.setText(birthplaceSt);
            fathersnameEt.setText(fathersnameSt);
            occupation1Et.setText(occupation1St);
            mothersnameEt.setText(mothersnameSt);
            occupation2Et.setText(occupation2St);
            noofbrothersEt.setText(noofbrothersSt);
            brotherdetailsEt.setText(brotherdetailsSt);
            noofsistersEt.setText(noofsistersSt);
            sisterdetailsEt.setText(sisterdetailsSt);
            qualificationEt.setText(qualificationSt);
            annualincome1Et.setText(annualincome1St);
            agerangeEt.setText(agerangeSt);
            height1Et.setText(height1St);
            occupation3Et.setText(occupation3St);
            preferredcityEt.setText(preferredcitySt);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        ///





    }
}
