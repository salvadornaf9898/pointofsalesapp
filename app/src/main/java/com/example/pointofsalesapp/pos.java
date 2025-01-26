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

        ed1 = findViewById(R.id.pid);
        ed2 = findViewById(R.id.product);
        ed3 = findViewById(R.id.qty);
        ed4 = findViewById(R.id.price);

        b1= findViewById(R.id.btn1);
        b2= findViewById(R.id.btn2);
        b3= findViewById(R.id.btn3);

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

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();

            }
        });

    }

    public void search(){

        try
        {

            ed2.setText("");
            ed3.setText("");
            ed4.setText("");

            String pid = ed1.getText().toString();
//

            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);
            final Cursor c = db.rawQuery( "select * from product where id =" + pid +" ", null);
//            final Cursor c = db.rawQuery( "select * from product where id ='1' ", null);

            //            //product_name, product_desc, category_name, brand_name, quantity, price
//
            int product_name = c.getColumnIndex("product_name");

            int quantity = c.getColumnIndex("quantity");
            int price = c.getColumnIndex("price");


           titles.clear();

            final ArrayList<producte> productee = new ArrayList<producte>();
            if(c.moveToFirst())
            {
                do{
                    producte po = new producte();
                    po.product_name = c.getString(product_name);
                    po.quantity = c.getString(quantity);
                    po.price = c.getString(price);
                    productee.add(po);



                    ed2.setText(c.getString(product_name));
                    ed3.setText(c.getString(quantity));
                    ed4.setText(c.getString(price));

                }while (c.moveToNext());


            }

       } catch (Exception ex)
        {
            Toast.makeText(this,"No se encontro",Toast.LENGTH_LONG).show();
        }
    }


    public void insert(){
        try
        {
            String product_id = ed1.getText().toString();
            String product_name = ed2.getText().toString();
            String quantity =  ed3.getText().toString();
            String price = ed4.getText().toString();

            Toast.makeText(this,"Hi",Toast.LENGTH_LONG).show();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

            db.execSQL("CREATE TABLE IF NOT EXISTS pos_temp(id INTEGER PRIMARY KEY AUTOINCREMENT, product_id INTEGER, product_name VARCHAR, quantity VARCHAR, price VARCHAR)");

//            String sql = "insert into pos_temp ( product_id , product_name , quantity , price )values(?,?,?,?,?,?)";
//            //          String sql = "insert into product ( product_name, product_desc, category_name, brand_name, quantity , price)values(?,?,?,?,?,?)";
            String sql = "insert into pos_temp ( product_id, product_name, quantity, price)values(?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,product_id);
            statement.bindString(2,product_name);
            statement.bindString(3,quantity);
            statement.bindString(4,price);


            statement.execute();
            Toast.makeText(this,"Producto Creado",Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed1.requestFocus();



        } catch (Exception ex)
        {
            Toast.makeText(this,"Error al agregar a caja",Toast.LENGTH_LONG).show();
        }
    }
}
