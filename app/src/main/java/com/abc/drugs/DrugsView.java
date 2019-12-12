package com.abc.drugs;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.support.v7.widget.SearchView;


import java.util.ArrayList;

public class DrugsView extends AppCompatActivity {


    private ListView listView;
    private ArrayList<DrugsModel> drugsModelArrayList;
    private DrugAdapter drugAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drugs);

        initView();
        loadData();


        listView = (ListView) findViewById(R.id.listView);

        databaseHelper = new DatabaseHelper(this);

        drugsModelArrayList = databaseHelper.getAllDrugs();

        drugAdapter = new DrugAdapter(this,drugsModelArrayList);
        listView.setAdapter(drugAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DrugsView.this, DrugsUpdateRemove.class);
                intent.putExtra("drugs",drugsModelArrayList.get(position));
                startActivity(intent);
            }
        });



    }

    private void initView() {
        listView = findViewById(R.id.listView);
        registerForContextMenu(listView);
    }

    private void loadData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        ArrayList<DrugsModel> drugsModels = databaseHelper.getAllDrugs();
        if (drugsModels != null) {
            listView.setAdapter(new DrugAdapter(getApplicationContext(), drugsModels));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchView).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContact(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchContact(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void searchContact(String keyword) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        ArrayList<DrugsModel> drugsModels = databaseHelper.searchDrugs(keyword);
        if (drugsModels != null) {
            listView.setAdapter(new DrugAdapter(getApplicationContext(), drugsModels));
        }
    }
}
