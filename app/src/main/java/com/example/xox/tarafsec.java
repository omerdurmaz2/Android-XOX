package com.example.xox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tarafsec extends AppCompatActivity {


    Button btnx ,btno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taraf_sec);

        btno = (Button) findViewById(R.id.tarafo);
        btnx = (Button) findViewById(R.id.tarafx);

        btnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.taraf = "x";
                finish();
                if (MainActivity.mod=="cp") MainActivity.makine="o";

            }
        });
        btno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.taraf = "o";
                if (MainActivity.mod=="cp") MainActivity.makine="x";
                finish();

            }
        });
        Intent intent = new Intent(this,modSec.class);
        startActivity(intent);
    }


}
