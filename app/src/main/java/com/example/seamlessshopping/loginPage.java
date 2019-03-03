package com.example.seamlessshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class loginPage extends AppCompatActivity {
    TextView createAccount;
    Button singin;
    EditText username , password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username=findViewById(R.id.usernameID);
        password=findViewById(R.id.passwordID);
        singin=findViewById(R.id.signin);
        createAccount =(TextView)findViewById(R.id.createAccount);
        SpannableString content = new SpannableString( createAccount.getText().toString() );
        content.setSpan(new UnderlineSpan(), 0, createAccount.getText().toString().length(), 0);
    }
    public void onLogin(View v){
        String usernameVal=username.getText().toString();
        String passwordVal=password.getText().toString();
        String type ="login";

        loginBackground loginBackground1= new loginBackground(this);
        loginBackground1.execute(type,usernameVal,passwordVal);
    }
}
