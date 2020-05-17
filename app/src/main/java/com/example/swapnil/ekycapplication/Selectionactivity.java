package com.example.swapnil.ekycapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Selectionactivity extends Activity {
    LinearLayout OldCustomerbtn,NewcustomerBtn;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectionactivity);

        OldCustomerbtn = (LinearLayout) findViewById(R.id.oldcustomerll);
        NewcustomerBtn = (LinearLayout) findViewById(R.id.newcustomerll);


        OldCustomerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GotoSelectionActivity = new Intent(Selectionactivity.this, showlist.class);
                startActivity(GotoSelectionActivity);
                finish();
            }
        });

        NewcustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GotoSelectionActivity=new Intent(Selectionactivity.this,showlist.class);
                startActivity(GotoSelectionActivity);
                finish();
            }
        });

    }
}
