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

public class pos extends AppCompatActivity {

    EditText ed1, ed2, ed3,ed4;
    Button b1,b2, b3;
    ArrayList<String> titles = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pos);

        b1= findViewById(R.id.btn1);
        b2= findViewById(R.id.btn2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(pos.this,Main.class);
                startActivity(i);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();

            }
        });

    }

    public void search(){
        Toast.makeText(this,"HI",Toast.LENGTH_LONG).show();
//        try
//        {
//
//            String id = ed1.getText().toString();
//
//
//
//            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);
//            final Cursor c = db.rawQuery( "select * from product where id ='"+id+"' ", null);
//            //product_name, product_desc, category_name, brand_name, quantity, price
//
//            int product_name = c.getColumnIndex("product_name");
//            int quantity = c.getColumnIndex("quantity");
//            int price = c.getColumnIndex("price");
//
//            titles.clear();
//
//            final ArrayList<producte> productee = new ArrayList<producte>();
//            if(c.moveToFirst())
//            {
//                do{
//                    producte po = new producte();
//                    po.product_name = c.getString(product_name);
//                    po.quantity = c.getString(quantity);
//                    po.price = c.getString(price);
//                    productee.add(po);
//
//                    ed2.setText(c.getString(product_name));
//                    ed3.setText(c.getString(quantity));
//                    ed4.setText(c.getString(price));
//
//                }while (c.moveToNext());
//
//
//            }
//
//       } catch (Exception ex)
//        {
//            Toast.makeText(this,"Failed Write",Toast.LENGTH_LONG).show();
//        }
    }
}
