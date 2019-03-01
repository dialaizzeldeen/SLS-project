package com.example.seamlessshopping;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    Button save;
    EditText pass,username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save=findViewById(R.id.buttonID);
        pass=findViewById(R.id.pass);
        username=findViewById(R.id.email);

    }


public void onLogin(View v){
        String usernameVal=username.getText().toString();
        String passwordVal=pass.getText().toString();
        String type ="login";

        loginBackground loginBackground1= new loginBackground(this);
        loginBackground1.execute(type,usernameVal,passwordVal);
}
}
