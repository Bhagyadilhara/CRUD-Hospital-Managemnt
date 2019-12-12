package com.abc.drugs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DrugsMain extends AppCompatActivity {

    private Button insert,show,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs_main);

        insert = (Button) findViewById(R.id.insert_btn1);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrugsMain.this, DrugsInsert.class);
                startActivity(intent);
            }
        });

        show = (Button) findViewById(R.id.view_btn1);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrugsMain.this, DrugsView.class);
                startActivity(intent);
            }
        });

        exit = (Button) findViewById(R.id.exitBtn);




    }
}
