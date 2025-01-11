package com.example.pointofsalesapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class BrandEdit extends AppCompatActivity {


    EditText ed1, ed2,ed3;
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brandedit);

        ed1 = findViewById(R.id.brandid);
        ed2 = findViewById(R.id.brand);
        ed3 = findViewById(R.id.branddesc);

        b1= findViewById(R.id.btn1);
        b2= findViewById(R.id.btn2);
        b3= findViewById(R.id.btn3);

        Intent i = getIntent();

        String id = i.getStringExtra("id").toString();
        String brand = i.getStringExtra("brand").toString();
        String desc = i.getStringExtra("branddesc").toString();

        ed1.setText(id);
        ed2.setText(brand);
        ed3.setText(desc);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BrandEdit.this,BrandView.class);
                startActivity(i);

            }
        });

    }

    public void edit(){
        try
        {
            String brandid = ed1.getText().toString();
            String brandname = ed2.getText().toString();
            String branddescription = ed3.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);


            String sql = "update brand set brand_name = ?, brand_desc = ? where id =? ";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,brandname);
            statement.bindString(2,branddescription);
            statement.bindString(3,brandid);
            statement.execute();
            Toast.makeText(this,"Marca Actualizada",Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), BrandView.class);
            startActivity(i);


        } catch (Exception ex)
        {
            Toast.makeText(this,"Failed Write",Toast.LENGTH_LONG).show();
        }
    }

    public void delete(){
        try
        {
            String brandid = ed1.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);


            String sql = "delete from brand where id =? ";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,brandid);

            statement.execute();
            Toast.makeText(this,"Marca borrada",Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), CategoryView.class);
            startActivity(i);


        } catch (Exception ex)
        {
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
    }
}
