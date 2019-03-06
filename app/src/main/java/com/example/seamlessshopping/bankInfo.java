package com.example.seamlessshopping;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class bankInfo extends AppCompatActivity {
 EditText cardno,cardname,expdate,cvv;
 Button submitbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_info);
        cardno=findViewById(R.id.cardNo);
        cardname=findViewById(R.id.cardName);
        expdate=findViewById(R.id.expireDate);
        cvv=findViewById(R.id.cvv);
        submitbutton=findViewById(R.id.submit);



    }

}
