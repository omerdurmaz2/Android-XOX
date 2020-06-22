package com.example.xox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class modSec extends AppCompatActivity {


    Button btncvsp,btnpvsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_sec);


        btncvsp = (Button) findViewById(R.id.mcvsp);
        btnpvsp = (Button) findViewById(R.id.mpvsp);

        btncvsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mod = "cp";
                finish();
            }
        });
        btnpvsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mod = "pp";
                finish();
            }
        });
    }
}
