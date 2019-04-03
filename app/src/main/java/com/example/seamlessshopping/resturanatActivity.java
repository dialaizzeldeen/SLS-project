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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                finish();

                Intent home =new Intent(resturanatActivity.this,NewllyAdded.class);
                startActivity(home) ;
                break;

            case R.id.navigation_Categories:
                finish();

                Intent categorie=new Intent(resturanatActivity.this,Categories_Activity.class);
                startActivity(categorie) ;


                break;
            case R.id.navigation_notifications:
                break;
            case R.id.navigation_profile:
                finish();
                if(id.equals("0")){
                    Intent login=new Intent(resturanatActivity.this,loginPage.class);
                    startActivity(login);

                }else {
                    Intent profile = new Intent(resturanatActivity.this, profilecategory.class);
                    startActivity(profile);
                }
                break;
            case R.id.navigation_search:
                finish();

                Intent search=new Intent(resturanatActivity.this,searching.class);
                startActivity(search) ;

                break;
        }
        return false;
    }

    public void getData(){
        SharedPreferences sharedPreferences=getSharedPreferences(shared_pres,MODE_PRIVATE);
        id=sharedPreferences.getString(iduser,"0");
        Log.d("response  ",id);
    }}
