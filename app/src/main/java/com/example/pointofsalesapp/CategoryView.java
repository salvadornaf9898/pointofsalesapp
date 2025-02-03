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
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryView extends AppCompatActivity {

    ListView lst1;
    Button b1;
    TextView ed1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryview);

        lst1 = findViewById(R.id.lst1);
        b1= findViewById(R.id.btn1);

        ed1 = findViewById(R.id.count);
        ed1.setText("");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoryView.this,Main.class);
                startActivity(i);

            }
        });

        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);
        final Cursor c = db.rawQuery( "select * from category", null);
        int item_count = 0 ;
        String item_count_string = "";
        int id = c.getColumnIndex("id");
        int category = c.getColumnIndex("category");
        int catdesc = c.getColumnIndex("catdesc");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,titles);

        lst1.setAdapter(arrayAdapter);

        final ArrayList<cate> catee = new ArrayList<cate>();
        if(c.moveToFirst())
        {
            do{
                cate ca = new cate();
                ca.id = c.getString(id);
                ca.category = c.getString(category);
                ca.desc = c.getString(catdesc);
                catee.add(ca);

                titles.add(c.getString(id) + "\t" + c.getString(category) + "\t" + c.getString(catdesc));
                item_count ++;

            }while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }

        item_count_string = "  Total de Categorias: " + item_count ;
        ed1.setText(item_count_string );

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               String aaa = titles.get(position).toString();
               cate ca = catee.get(position);
               Intent i = new Intent(getApplicationContext(), CategoryEdit.class);
               i.putExtra("id",ca.id);
               i.putExtra("category",ca.category);
               i.putExtra("catdesc",ca.desc);
               startActivity(i);


            }
        });
    }
}
