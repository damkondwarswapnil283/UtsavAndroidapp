package com.example.swapnil.ekycapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selectmode extends AppCompatActivity {
Button matrimonyBtn,communityBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectmode);
        matrimonyBtn=(Button)findViewById(R.id.matrimony);
        communityBtn=(Button)findViewById(R.id.community);

        matrimonyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologinscreen=new Intent(selectmode.this,Selectionactivity.class);
                startActivity(gotologinscreen);
            }
        });

        communityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotologinscreen=new Intent(selectmode.this,Communitymainapp.class);
                startActivity(gotologinscreen);
            }
        });
    }
}
