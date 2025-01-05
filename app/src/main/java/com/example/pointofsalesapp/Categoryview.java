package com.example.pointofsalesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Categoryview extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryview);

        lst1 = findViewById(R.id.lst1);
        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);
        final Cursor c = db.rawQuery( "select * from category", null);

        int id = c.getColumnIndex("id");
        int category = c.getColumnIndex("category");
        int catdesc = c.getColumnIndex("catdesc");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,titles);

        lst1.setAdapter(arrayAdapter);

        final ArrayList<cate> catee = new ArrayList<cate>();
        if(c.moveToFirst())
        {
            do{
                cate ca = new cate();
                ca.id = c.getString(id);
                ca.category = c.getString(category);
                ca.desc = c.getString(catdesc);
                catee.add(ca);

                titles.add(c.getString(id) + "\t" + c.getString(category) + "\t" + c.getString(catdesc));

            }while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }
    }
}
