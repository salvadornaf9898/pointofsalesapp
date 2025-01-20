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

public class Category extends AppCompatActivity {

    EditText ed1, ed2;
    Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ed1=findViewById(R.id.category);
        ed2=findViewById(R.id.categorydesc);

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
                Intent i = new Intent(Category.this,Main.class);
                startActivity(i);

            }
        });


    }


    public void insert(){
        try
        {
            String category = ed1.getText().toString();
            String description = ed2.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS category(id INTEGER PRIMARY KEY AUTOINCREMENT, category VARCHAR, catdesc VARCHAR)");

            String sql = "insert into category (category,catdesc)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,category);
            statement.bindString(2,description);
            statement.execute();
            Toast.makeText(this,"Cateogria Creada",Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();



        } catch (Exception ex)
        {
            Toast.makeText(this,"Failed Write",Toast.LENGTH_LONG).show();
        }
    }
}
