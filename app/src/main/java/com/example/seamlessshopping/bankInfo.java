package com.example.seamlessshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

public class bankInfo extends AppCompatActivity  implements  BottomNavigationView.OnNavigationItemSelectedListener {
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
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        submitbutton=findViewById(R.id.submit);



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                finish();

                Intent home =new Intent(bankInfo.this,NewllyAdded.class);
                startActivity(home) ;
                break;

            case R.id.navigation_Categories:
                finish();

                Intent categorie=new Intent(bankInfo.this,Categories_Activity.class);
                startActivity(categorie) ;


                break;
            case R.id.navigation_notifications:
                break;
            case R.id.navigation_profile:
                finish();

                Intent profile=new Intent(bankInfo.this,profilecategory.class);
                startActivity(profile) ;
                break;
            case R.id.navigation_search:
                finish();

                Intent search=new Intent(bankInfo.this,searching.class);
                startActivity(search) ;

                break;
        }
        return false;    }
}
