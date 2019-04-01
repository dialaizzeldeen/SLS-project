package com.example.seamlessshopping;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class profilecategory extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilecategory);
        TextView openProfile=findViewById(R.id.profileid);
        TextView pymentPage=findViewById(R.id.paymentid);
        TextView historyPage=findViewById(R.id.historyid);
        TextView carticon=findViewById(R.id.cartprofilecat);





        openProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), profile.class);
                startActivity(i);
                  }});
        carticon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), cart.class);
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
     navigation= (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(this);



    }


    public void onBackPressed() {
        int seletedItemId = navigation.getSelectedItemId();
        if (0 == seletedItemId) {
            Intent home = new Intent(profilecategory.this, NewllyAdded.class);
            startActivity(home);
        } else if (2 == seletedItemId) {

            Intent categorie = new Intent(profilecategory.this, Categories_Activity.class);
            startActivity(categorie);
        } else if (3 == seletedItemId) {
            Intent profile = new Intent(profilecategory.this, profilecategory.class);
            startActivity(profile);
        } else {
            super.onBackPressed();
        }

    }
        @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:

                Intent home =new Intent(profilecategory.this,NewllyAdded.class);
                startActivity(home) ;
                break;

            case R.id.navigation_Categories:

                Intent categorie=new Intent(profilecategory.this,Categories_Activity.class);
                startActivity(categorie) ;


                break;
            case R.id.navigation_notifications:
                break;
            case R.id.navigation_profile:

            case R.id.navigation_search:

                Intent search=new Intent(profilecategory.this,searching.class);
                startActivity(search) ;

                break;
        }
        return false;
    }







}
