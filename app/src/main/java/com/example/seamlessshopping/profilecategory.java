package com.example.seamlessshopping;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class profilecategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilecategory);
        TextView openProfile=findViewById(R.id.profileid);
        TextView pymentPage=findViewById(R.id.paymentid);
        TextView historyPage=findViewById(R.id.historyid);

        openProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), profile.class);
                startActivity(i);
                  }});
        pymentPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),bankInfo.class);
                startActivity(i);
            }});
        historyPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),paymentHistory.class);
                startActivity(i);
            }});




    }


}
