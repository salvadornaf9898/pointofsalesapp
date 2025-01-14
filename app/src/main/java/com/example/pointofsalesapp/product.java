package com.example.pointofsalesapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class product extends AppCompatActivity {

    EditText ed1, ed2, ed3,ed4;
    Spinner spinner, spinner1;

    ArrayList<String> titles = new ArrayList<String>();
    ArrayList<String> titles1 = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    ArrayAdapter arrayAdapter1;

    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ed1 = findViewById(R.id.product);
        ed2 = findViewById(R.id.productdesc);

        spinner = findViewById(R.id.catid);
        spinner1 = findViewById(R.id.brandid);

        ed3 = findViewById(R.id.qty);
        ed4 = findViewById(R.id.price);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(product.this,Main.class);
                startActivity(i);

            }
        });

        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);
        final Cursor c = db.rawQuery( "select * from category", null);

        int category = c.getColumnIndex("category");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,titles);

        spinner.setAdapter(arrayAdapter);

        final ArrayList<cate> catee = new ArrayList<cate>();
        if(c.moveToFirst())
        {
            do{
                cate ca = new cate();
                ca.category = c.getString(category);
                catee.add(ca);

                titles.add(c.getString(category));
            }while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
        }





        SQLiteDatabase db1 = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);
        final Cursor c1 = db1.rawQuery( "select * from brand", null);

        int marca = c1.getColumnIndex("brand_name");

        titles1.clear();
        arrayAdapter1 = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,titles1);
        spinner1.setAdapter(arrayAdapter1);

        final ArrayList<brande> brandee = new ArrayList<>();
        if(c1.moveToFirst())
        {
            do{
                brande ba = new brande();
                ba.brand_name = c1.getString(marca);
                brandee.add(ba);

                titles1.add(c1.getString(marca));
            }while (c1.moveToNext());

            arrayAdapter1.notifyDataSetChanged();

        }





    }
}
