package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CommunityMode extends AppCompatActivity {
Button addcommdata,editcomdata,viewcomdata;
String getid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_mode);
        addcommdata=(Button)findViewById(R.id.addcomdata);
        editcomdata=(Button)findViewById(R.id.editcomdata);
        viewcomdata=(Button)findViewById(R.id.viewcommdata);
        getid=getIntent().getExtras().getString("id");


        addcommdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologinscreen=new Intent(CommunityMode.this,Createcommunity.class);
                gotologinscreen.putExtra("id",getid);
                startActivity(gotologinscreen);
            }
        });


        editcomdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologinscreen=new Intent(CommunityMode.this,Editcommunity.class);
                gotologinscreen.putExtra("id",getid);
                startActivity(gotologinscreen);
            }
        });


        viewcomdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologinscreen=new Intent(CommunityMode.this,showlist.class);
                gotologinscreen.putExtra("gender","X");
                startActivity(gotologinscreen);
            }
        });

    }
}
