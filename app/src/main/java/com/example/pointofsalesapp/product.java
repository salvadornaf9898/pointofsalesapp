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

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();

            }
        });

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

    public void insert(){
        try
        {
            String product_name = ed1.getText().toString();
            String product_desc = ed2.getText().toString();
            String category_name = spinner.getSelectedItem().toString();
            String brand_name = spinner1.getSelectedItem().toString();
            String quantity =  ed3.getText().toString();
            String price = ed4.getText().toString();



            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS product(id INTEGER PRIMARY KEY AUTOINCREMENT, product_name VARCHAR, product_desc VARCHAR, category_name VARCHAR, brand_name VARCHAR, quantity VARCHAR, price VARCHAR)");

//            String sql = "insert into category ( product_name, product_desc, category_name, brand_name, quantity , price)values(?,?,?,?,?,?)";
  //          String sql = "insert into product ( product_name, product_desc, category_name, brand_name, quantity , price)values(?,?,?,?,?,?)";
            String sql = "insert into product ( product_name, product_desc, category_name, brand_name, quantity, price)values(?,?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,product_name);
            statement.bindString(2,product_desc);
            statement.bindString(3,category_name);
            statement.bindString(4,brand_name);
            statement.bindString(5,quantity);
            statement.bindString(6,price);

//            statement.bindString(1,product_name);
//            statement.bindString(2,"Una Coca cola 12 oz de botalla");
//            statement.bindString(3,"refresco");
//            statement.bindString(4,"Coca COla");
//            statement.bindString(5,"1");
//            statement.bindString(6,"12");

            statement.execute();
            Toast.makeText(this,"Producto Creado",Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed1.requestFocus();



        } catch (Exception ex)
        {
            Toast.makeText(this,"Failed Write",Toast.LENGTH_LONG).show();
        }
    }


}
