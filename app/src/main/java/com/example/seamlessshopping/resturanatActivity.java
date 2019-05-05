package com.example.seamlessshopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class resturanatActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    ListView categoriesListView;
    categoriesObject categoriesObjects;
    ArrayList<categoriesObject> categoriesObjectArrayList = new ArrayList<categoriesObject>();
    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    private String id="0";
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
navigation.setOnNavigationItemSelectedListener(this);


        categoriesListView = (ListView) findViewById(R.id.listViewcategories);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int itemposition, long id) {
        Intent viewIntent = new Intent(this,productMain.class);
       categoriesObject objectView = categoriesObjectArrayList.get(itemposition);
        viewIntent.putExtra("viewPosition", itemposition);
     //   viewIntent.putExtra("resturantID",objectView);

        startActivity(viewIntent);

    }
    public void onBackPressed() {
        int seletedItemId =navigation.getSelectedItemId();
        if (0 == seletedItemId) {
            Intent home =new Intent(resturanatActivity.this,NewllyAdded.class);
            startActivity(home) ;
        }else if(2== seletedItemId){

            Intent categorie=new Intent(resturanatActivity.this,Categories_Activity.class);
            startActivity(categorie) ;}
        else if( 3 == seletedItemId){
            Intent profile=new Intent(resturanatActivity.this,profilecategory.class);
            startActivity(profile) ;}

        else if(1== seletedItemId){
            Intent Cart=new Intent(resturanatActivity.this,cart.class);
            startActivity(Cart) ;




        }

        else {
            super.onBackPressed();
        }



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:

                Intent home =new Intent(resturanatActivity.this,NewllyAdded.class);
                startActivity(home) ;
                break;

            case R.id.navigation_Categories:


                break;
            case R.id.navigation_cart:

                Intent Cart=new Intent(resturanatActivity.this,cart.class);
                startActivity(Cart) ;

                break;
            case R.id.navigation_profile:
                if(id.equals("0")){
                    Intent login=new Intent(resturanatActivity.this,loginvolley.class);
                    startActivity(login);

                }else {
                    Intent profile = new Intent(resturanatActivity.this, profilecategory.class);
                    startActivity(profile);
                }
                break;
            case R.id.navigation_search:
                Intent search = new Intent(resturanatActivity.this, searching.class);
                startActivity(search);
                break;
        }
        return false;    }



    public void getData(){
        SharedPreferences sharedPreferences=getSharedPreferences(shared_pres,MODE_PRIVATE);
        id=sharedPreferences.getString(iduser,"0");
        Log.d("response  ",id);
    }}
