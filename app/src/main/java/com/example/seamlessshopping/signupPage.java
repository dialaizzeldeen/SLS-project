package com.example.seamlessshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signupPage extends AppCompatActivity {

    Button signup;
    EditText email , username, Fname, Lname, phoneNo,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        signup=findViewById(R.id.signup);
        email =findViewById(R.id.addedmail);
        username=findViewById(R.id.username);
        Fname=findViewById(R.id.addedFirstName);
        Lname=findViewById(R.id.addedLastName);
        phoneNo=findViewById(R.id.addedphone);
        password=findViewById(R.id.addedPassword);


    }
    public void onSginup(View v){
        String emailVal=email.getText().toString();
        String usernameVal=username.getText().toString();
        String passwordVal=password.getText().toString();
        String FnameVal=Fname.getText().toString();
        String LnameVal=Lname.getText().toString();
        String phoneNoVal=phoneNo.getText().toString();
        String type ="signup";

        signupBackground signupBackground1=new signupBackground(this);
        Object[]myVeriable={type,FnameVal,LnameVal,usernameVal,emailVal,passwordVal,phoneNoVal};
        signupBackground1.execute(myVeriable);
    }


}
//192.168.1.4