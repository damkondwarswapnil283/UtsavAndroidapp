package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class selectmode extends AppCompatActivity {
    Button matrimonyBtn;
    Button communityBtn;
    String getid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectmode);
        matrimonyBtn=(Button)findViewById(R.id.matrimony);
        communityBtn=(Button)findViewById(R.id.community);

        getid=getIntent().getExtras().getString("userid");
        //Toast.makeText(selectmode.this, "Selectmode ID 2:-"+getid, Toast.LENGTH_SHORT).show();

        matrimonyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologinscreen=new Intent(selectmode.this,Selectionactivity.class);
                gotologinscreen.putExtra("id",getid);
                startActivity(gotologinscreen);
                finish();
            }
        });

        communityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologinscreen=new Intent(selectmode.this,CommunityMode.class);
                gotologinscreen.putExtra("id",getid);
               startActivity(gotologinscreen);
            }
        });
    }
}
