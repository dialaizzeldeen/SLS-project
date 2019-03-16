package com.example.seamlessshopping;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    TextView createAccount;
    EditText username , password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username=findViewById(R.id.usernameID);
        password=findViewById(R.id.passwordID);
        createAccount =(TextView)findViewById(R.id.createAccount);
        SpannableString content = new SpannableString( createAccount.getText().toString() );
        content.setSpan(new UnderlineSpan(), 0, createAccount.getText().toString().length(), 0);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.categorymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.pagesignup) {
            Intent i = new Intent(this, signupPage.class);
            startActivity(i);
        }
        else if (item.getItemId()==R.id.loginpage) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        else if (item.getItemId()==R.id.searchpage) {
            Intent i = new Intent(this, productMain.class);
            startActivity(i);
        }
        else if (item.getItemId()==R.id.profile) {
            Intent i = new Intent(this, loginPage.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }




    public void onLogin(View v){
        String usernameVal=username.getText().toString();
        String passwordVal=password.getText().toString();
        String type ="login";

        loginBackground loginBackground1= new loginBackground(this);
        loginBackground1.execute(type,usernameVal,passwordVal);
    }
}
