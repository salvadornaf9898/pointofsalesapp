package com.example.pointofsalesapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Brand extends AppCompatActivity {

    EditText ed1, ed2;
    Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);

        ed1=findViewById(R.id.marca);
        ed2=findViewById(R.id.marcadesc);

        b1=findViewById(R.id.btn1);
        b2=findViewById(R.id.btn2);




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Brand.this,Main.class);
                startActivity(i);

            }
        });


    }


    public void insert(){
        try
        {
            String marca = ed1.getText().toString();
            String marcadesc = ed2.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS brand(id INTEGER PRIMARY KEY AUTOINCREMENT, brand_name VARCHAR, brand_desc VARCHAR)");

            String sql = "insert into brand (brand_name,brand_desc)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,marca);
            statement.bindString(2,marcadesc);
            statement.execute();
            Toast.makeText(this,"Marca Registrada",Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();



        } catch (Exception ex)
        {
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
    }
}
