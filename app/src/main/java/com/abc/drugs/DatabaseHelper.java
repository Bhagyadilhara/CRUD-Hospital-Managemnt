package com.abc.drugs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "user_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_DRUGS = "drugs";
    private static final String KEY_DRUGID = "id";
    private static final String KEY_DRUGNAME = "name";
    private static final String KEY_MANUFACTURER = "manufacturer";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_PRICE = "price";
    private static final String KEY_DESCRIPTION = "description";



    private static final String CREATE_TABLE_DRUGS = "CREATE TABLE "
            + TABLE_DRUGS + "(" + KEY_DRUGID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DRUGNAME + " TEXT, "+ KEY_MANUFACTURER + " TEXT, "+ KEY_QUANTITY + " TEXT, "+ KEY_PRICE + " TEXT, "+ KEY_DESCRIPTION + " TEXT );";


    public DatabaseHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_DRUGS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_DRUGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_DRUGS + "'");
        onCreate(db);
    }

    public long addDrugsDetail(String name, String manufacturer,String quantity,String price,String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_DRUGNAME, name);
        values.put(KEY_MANUFACTURER,manufacturer);
        values.put(KEY_QUANTITY,quantity);
        values.put(KEY_PRICE,price);
        values.put(KEY_DESCRIPTION,description);
        // insert row in drugs table
        long insert = db.insert(TABLE_DRUGS, null, values);

        return insert;
    }


    public ArrayList<DrugsModel> getAllDrugs() {
       // ArrayList<DrugsModel> drugsModelArrayList = new ArrayList<DrugsModel>();
        ArrayList<DrugsModel> drugsModelArrayList = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_DRUGS,null);



//        String selectQuery = "SELECT  * FROM " + TABLE_DRUGS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            drugsModelArrayList = new ArrayList<DrugsModel>();
            do {
                DrugsModel drugsModel = new DrugsModel();
                drugsModel.setId(c.getInt(c.getColumnIndex(KEY_DRUGID)));
                drugsModel.setName(c.getString(c.getColumnIndex(KEY_DRUGNAME)));
                drugsModel.setManufacturer(c.getString(c.getColumnIndex(KEY_MANUFACTURER)));
                drugsModel.setQuantity(c.getString(c.getColumnIndex(KEY_QUANTITY)));
                drugsModel.setPrice(c.getString(c.getColumnIndex(KEY_PRICE)));
                drugsModel.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
                // adding to drugs list
                drugsModelArrayList.add(drugsModel);
            } while (c.moveToNext());
        }
        return drugsModelArrayList;
    }


    public int updateDrugs(int id,String name, String manufacturer,String quantity,String price,String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_DRUGNAME, name);
        values.put(KEY_MANUFACTURER,manufacturer);
        values.put(KEY_QUANTITY,quantity);
        values.put(KEY_PRICE,price);
        values.put(KEY_DESCRIPTION,description);
        // update row in drugs table base on drugs.is value
        return db.update(TABLE_DRUGS, values, KEY_DRUGID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteDrug(int id) {

        // delete row in drugs table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DRUGS, KEY_DRUGID + " = ?",
                new String[]{String.valueOf(id)});
    }


    public ArrayList<DrugsModel> searchDrugs(String drug) {
        // ArrayList<DrugsModel> drugsModelArrayList = new ArrayList<DrugsModel>();
        ArrayList<DrugsModel> drugsModels = null;

        try {

            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + TABLE_DRUGS + " WHERE " + KEY_DRUGNAME + " LIKE ?", new String[] { "%" + drug + "%" });


//        String selectQuery = "SELECT  * FROM " + TABLE_DRUGS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (c.moveToFirst()) {
                drugsModels = new ArrayList<DrugsModel>();
                do {
                    DrugsModel drugsModel = new DrugsModel();
                    drugsModel.setId(c.getInt(c.getColumnIndex(KEY_DRUGID)));
                    drugsModel.setName(c.getString(c.getColumnIndex(KEY_DRUGNAME)));
                    drugsModel.setManufacturer(c.getString(c.getColumnIndex(KEY_MANUFACTURER)));
                    drugsModel.setQuantity(c.getString(c.getColumnIndex(KEY_QUANTITY)));
                    drugsModel.setPrice(c.getString(c.getColumnIndex(KEY_PRICE)));
                    drugsModel.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
                    // adding to drugs list
                    drugsModels.add(drugsModel);
                } while (c.moveToNext());
            }
        }catch(Exception e) {
            drugsModels = null;
        }
        return drugsModels;
    }


}

