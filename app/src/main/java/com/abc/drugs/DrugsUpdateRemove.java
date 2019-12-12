package com.abc.drugs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DrugsUpdateRemove extends AppCompatActivity {



    private Button edit,delete;
    private DrugsModel drugsModel;
    private DatabaseHelper databaseHelper;
    private EditText upname,upmanufacturer,upquantity,upprice,updescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_remove_drugs);

        Intent intent = getIntent();
        drugsModel = (DrugsModel) intent.getSerializableExtra("drugs");

        databaseHelper = new DatabaseHelper(this);

        edit = (Button) findViewById(R.id.update_btn1);
        delete = (Button) findViewById(R.id.delete_btn1);
        upname= (EditText) findViewById(R.id.nameUpdate);
        upmanufacturer= (EditText) findViewById(R.id.manufacturerUpdate);
        upquantity= (EditText) findViewById(R.id.quantityUpdate);
        upprice= (EditText) findViewById(R.id.priceUpdate);
        updescription= (EditText) findViewById(R.id.descriptionUpdate);


        upname.setText(drugsModel.getName());
        upmanufacturer.setText(drugsModel.getManufacturer());
        upquantity.setText(drugsModel.getQuantity());
        upprice.setText(drugsModel.getPrice());
        updescription.setText(drugsModel.getDescription());



        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(upname.getText().toString().equals("")||upmanufacturer.getText().toString().equals("")||
                        upquantity.getText().toString().equals("")||upprice.getText().toString().equals("")||
                        updescription.getText().toString().equals("")){
                    Toast.makeText(DrugsUpdateRemove.this, "Fill out all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseHelper.updateDrugs(drugsModel.getId(),upname.getText().toString(),upmanufacturer.getText().toString(),
                        upquantity.getText().toString(),upprice.getText().toString(),updescription.getText().toString());
                Toast.makeText(DrugsUpdateRemove.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DrugsUpdateRemove.this, DrugsView.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });




        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteDrug(drugsModel.getId());
                Toast.makeText(DrugsUpdateRemove.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DrugsUpdateRemove.this, DrugsView.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });



    }


}
