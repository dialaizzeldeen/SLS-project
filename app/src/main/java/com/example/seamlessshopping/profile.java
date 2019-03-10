package com.example.seamlessshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class profile extends AppCompatActivity {
    TextView username;
    Button save;
    EditText  name,gender,location,bday,mobile,personalemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        username=findViewById(R.id.usernameID);
        gender=findViewById(R.id.gender);
        location=findViewById(R.id.location);
        bday=findViewById(R.id.bday);
        mobile=findViewById(R.id.mobile);
        personalemail=findViewById(R.id.personalemail);
        save=findViewById(R.id.save);
    }
    public void saveP(View v){
        String usernamep=username.getText().toString();
        String genderp=gender.getText().toString();
        String locationp=location.getText().toString();
        String bdayp=bday.getText().toString();
        String mobilep=mobile.getText().toString();
        String personalemailp=personalemail.getText().toString();
        String type ="save";

        loginBackground loginBackground1= new loginBackground(this);
        loginBackground1.execute(type,usernamep,genderp,locationp,bdayp,mobilep,personalemailp);
    }
}

