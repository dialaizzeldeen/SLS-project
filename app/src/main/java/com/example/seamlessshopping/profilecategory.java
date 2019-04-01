package com.example.seamlessshopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class profilecategory extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener{

    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";

    private String id="0";
    Context Context;
    int positionitem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilecategory);
        TextView openProfile=findViewById(R.id.profileid);
        TextView pymentPage=findViewById(R.id.paymentid);
        TextView historyPage=findViewById(R.id.historyid);
        TextView carticon=findViewById(R.id.cartprofilecat);
        TextView signout=findViewById(R.id.signout);

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

       signout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Savedata("0");
                Intent i=new Intent(getApplicationContext(),NewllyAdded.class);
                startActivity(i);
            }});


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(this);



    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                finish();

                Intent home =new Intent(profilecategory.this,NewllyAdded.class);
                startActivity(home) ;
                break;

            case R.id.navigation_Categories:
                finish();

                Intent categorie=new Intent(profilecategory.this,Categories_Activity.class);
                startActivity(categorie) ;


                break;
            case R.id.navigation_notifications:
                break;
            case R.id.navigation_profile:

            case R.id.navigation_search:
                finish();

                Intent search=new Intent(profilecategory.this,searching.class);
                startActivity(search) ;

                break;
        }
        return false;
    }



    public void Savedata(String s){
        SharedPreferences sharedPreferences=Context.getApplicationContext().getSharedPreferences(shared_pres,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(iduser,s.toString());
        editor.apply();
    }





}
