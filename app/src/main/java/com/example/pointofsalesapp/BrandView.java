package com.example.pointofsalesapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class BrandView extends AppCompatActivity {

    ListView lst1;
    Button b1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryview);

        lst1 = findViewById(R.id.lst1);
        b1= findViewById(R.id.btn1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BrandView.this,Main.class);
                startActivity(i);

            }
        });

        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);
        final Cursor c = db.rawQuery( "select * from brand", null);

        int id = c.getColumnIndex("id");
        int marca = c.getColumnIndex("brand_name");
        int marcadesc = c.getColumnIndex("brand_desc");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,titles);

        lst1.setAdapter(arrayAdapter);

        final ArrayList<brande> catee = new ArrayList<>();
        if(c.moveToFirst())
        {
            do{
                brande ca = new brande();
                ca.id = c.getString(id);
                ca.brand_name = c.getString(marca);
                ca.brand_desc = c.getString(marcadesc);
                catee.add(ca);

                titles.add(c.getString(id) + "\t" + c.getString(marca) + "\t" + c.getString(marcadesc));

            }while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }

//        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                String aaa = titles.get(position).toString();
//                cate ca = catee.get(position);
//                Intent i = new Intent(getApplicationContext(), CategoryEdit.class);
//                i.putExtra("id",ca.id);
//                i.putExtra("category",ca.category);
//                i.putExtra("catdesc",ca.desc);
//                startActivity(i);
//
//
//            }
//        });
    }
}
