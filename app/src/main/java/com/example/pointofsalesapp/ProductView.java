package com.example.pointofsalesapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ProductView extends AppCompatActivity {

    ListView lst1;
    Button b1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        lst1 = findViewById(R.id.lst1);
        b1= findViewById(R.id.btn1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductView.this,Main.class);
                startActivity(i);

            }
        });

        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);
        final Cursor c = db.rawQuery( "select * from product", null);
        //product_name, product_desc, category_name, brand_name, quantity, price
        int id = c.getColumnIndex("id");
        int product_name = c.getColumnIndex("product_name");
        int product_desc = c.getColumnIndex("product_desc");
        int category_name = c.getColumnIndex("category_name");
        int brand_name = c.getColumnIndex("brand_name");
        int quantity = c.getColumnIndex("quantity");
        int price = c.getColumnIndex("price");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,titles);

        lst1.setAdapter(arrayAdapter);

        final ArrayList<producte> productee = new ArrayList<producte>();
        if(c.moveToFirst())
        {
            do{
                producte po = new producte();
                po.id = c.getString(id);
                po.product_name = c.getString(product_name);
                po.product_desc = c.getString(product_desc);
                po.category_name = c.getString(category_name);
                po.brand_name = c.getString(brand_name);
                po.quantity = c.getString(quantity);
                po.price = c.getString(price);
                productee.add(po);

                titles.add(c.getString(id) + "\t" + c.getString(product_name) + "\t" + c.getString(product_desc) + "\t" + c.getString(category_name) + "\t" + c.getString(brand_name) + "\t" + c.getString(quantity) + "\t" + c.getString(price));

            }while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String aaa = titles.get(position).toString();
                producte po = productee.get(position);
                Intent i = new Intent(getApplicationContext(), CategoryEdit.class);
                i.putExtra("id",po.id);
                i.putExtra("product_name",po.product_name);
                i.putExtra("product_desc",po.product_desc);
                i.putExtra("category_name",po.category_name);
                i.putExtra("brand_name",po.brand_name);
                i.putExtra("quantity",po.quantity);
                i.putExtra("price",po.price);
                startActivity(i);


            }
        });


    }
}
