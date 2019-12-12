package com.abc.drugs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DrugsInsert extends AppCompatActivity {

    private Button add;
    private EditText etname, etmanufacturer,etquantity,etprice,etdescription;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_drugs);

        databaseHelper = new DatabaseHelper(this);

        add = (Button) findViewById(R.id.insert_btn2);
        etname = (EditText) findViewById(R.id.nameText);
        etmanufacturer = (EditText) findViewById(R.id.manufacturerText);
        etquantity = (EditText) findViewById(R.id.quantityText);
        etprice = (EditText) findViewById(R.id.priceText);
        etdescription = (EditText) findViewById(R.id.descriptionText);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //empty fields validate
                if(etname.getText().toString().equals("")||etmanufacturer.getText().toString().equals("")||
                        etquantity.getText().toString().equals("")||etprice.getText().toString().equals("")||
                        etdescription.getText().toString().equals("")){
                    Toast.makeText(DrugsInsert.this, "Fill out all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                databaseHelper.addDrugsDetail(etname.getText().toString(), etmanufacturer.getText().toString(),
                        etquantity.getText().toString(),  etprice.getText().toString(), etdescription.getText().toString());
                etname.setText("");    //clear fiels
                etmanufacturer.setText("");
                etquantity.setText("");
                etprice.setText("");
                etdescription.setText("");
                Toast.makeText(DrugsInsert.this, "Stored Successfully!", Toast.LENGTH_SHORT).show(); //toast message
                Intent intent = new Intent(DrugsInsert.this, DrugsView.class);
                startActivity(intent);
            }
        });


    }
}
