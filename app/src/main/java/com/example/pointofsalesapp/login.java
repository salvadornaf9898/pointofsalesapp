package com.example.pointofsalesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText ed1,ed2;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        ed1 = findViewById(R.id.user);
        ed2 = findViewById(R.id.password);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }

        });

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            Login();
            }

        });
    }


    public void Login(){
        String username = ed1.getText().toString();
        String password = ed2.getText().toString();

        if(username.equals("") || password.equals(""))
        {
            Toast.makeText( this, "Username or Password Blank",Toast.LENGTH_LONG).show();

        }
        else if(username.equals("john") && password.equals("123")){
            Toast.makeText( this, "Se ha iniciado sesion",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText( this, "Error en contraseña",Toast.LENGTH_LONG).show();
        }


    }

}
