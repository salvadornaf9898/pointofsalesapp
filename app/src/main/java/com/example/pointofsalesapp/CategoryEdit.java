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


public class CategoryEdit extends AppCompatActivity {

    EditText ed1, ed2,ed3;
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryedit);

        ed1 = findViewById(R.id.categoryid);
        ed2 = findViewById(R.id.category);
        ed3 = findViewById(R.id.categorydesc);

        b1= findViewById(R.id.btn1);
        b2= findViewById(R.id.btn2);
        b3= findViewById(R.id.btn3);

        Intent i = getIntent();

        String id = i.getStringExtra("id").toString();
        String category = i.getStringExtra("category").toString();
        String desc = i.getStringExtra("catdesc").toString();

        ed1.setText(id);
        ed2.setText(category);
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
                Intent i = new Intent(CategoryEdit.this,CategoryView.class);
                startActivity(i);

            }
        });

    }

    public void edit(){
        try
        {
            String catid = ed1.getText().toString();
            String categoryname = ed2.getText().toString();
            String catdescription = ed3.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);


            String sql = "update category set category = ?, catdesc = ? where id =? ";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,categoryname);
            statement.bindString(2,catdescription);
            statement.bindString(3,catid);
            statement.execute();
            Toast.makeText(this,"Categoria Actualizada",Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), CategoryView.class);
            startActivity(i);


        } catch (Exception ex)
        {
            Toast.makeText(this,"Failed Write",Toast.LENGTH_LONG).show();
        }
    }

    public void delete(){
        try
        {
            String catid = ed1.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);


            String sql = "delete from category where id =? ";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,catid);

            statement.execute();
            Toast.makeText(this,"Categoria borrada",Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), CategoryView.class);
            startActivity(i);


        } catch (Exception ex)
        {
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
    }
}
