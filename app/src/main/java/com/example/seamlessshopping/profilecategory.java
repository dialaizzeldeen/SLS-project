package com.example.seamlessshopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class profilecategory extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener{

    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    public static final String usernamedb="idusername";
    public static final String userpassworddb="iduserpassword";
    BottomNavigationView navigation;
    private String id="0";
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
        getData();
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        openProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), profile.class);
                i.putExtra("view",0);
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

                Intent home =new Intent(profilecategory.this,NewllyAdded.class);
                startActivity(home) ;
                break;

            case R.id.navigation_Categories:

                Intent categorie=new Intent(profilecategory.this,Categories_Activity.class);
                startActivity(categorie) ;


                break;
            case R.id.navigation_cart:

                Intent Cart=new Intent(profilecategory.this,cart.class);
                startActivity(Cart) ;

                break;
            case R.id.navigation_profile:

                Intent profile=new Intent(profilecategory.this,profilecategory.class);
                startActivity(profile) ;
                break;
            case R.id.navigation_search:

                Intent search=new Intent(profilecategory.this,searching.class);
                startActivity(search) ;

                break;
        }
        return false;    }




    public void onBackPressed() {
        int seletedItemId = navigation.getSelectedItemId();
        if (0 == seletedItemId) {
            Intent home = new Intent(profilecategory.this, NewllyAdded.class);
            startActivity(home);
        } else if (2 == seletedItemId) {

            Intent categorie = new Intent(profilecategory.this, Categories_Activity.class);
            startActivity(categorie);
        } else if (3 == seletedItemId) {
            Intent profile = new Intent(profilecategory.this,profilecategory.class);
            startActivity(profile);
        } else if (1 == seletedItemId) {
            Intent Cart = new Intent(profilecategory.this, cart.class);
            startActivity(Cart);


        } else {
            super.onBackPressed();
        }
    }


    public void Savedata(String s){
        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(shared_pres,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        sharedPreferences.edit().remove(iduser).apply();
        sharedPreferences.edit().remove(usernamedb).apply();
        sharedPreferences.edit().remove(userpassworddb).apply();

        editor.apply();
    }
    public void getData(){
        SharedPreferences sharedPreferences=getSharedPreferences(shared_pres,MODE_PRIVATE);
        id=sharedPreferences.getString(iduser,"0");
        Log.d("response  ",id);
    }





}
