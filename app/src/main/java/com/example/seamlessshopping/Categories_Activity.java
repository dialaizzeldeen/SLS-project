package com.example.seamlessshopping;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class Categories_Activity extends AppCompatActivity  implements AdapterView.OnItemClickListener {
    ListView categoriesListView;
    categoriesObject categoriesObjects;
    ArrayList<categoriesObject> categoriesObjectArrayList = new ArrayList<categoriesObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        categoriesAdapter categoriesAdapters;


        categoriesListView = (ListView) findViewById(R.id.listViewcategories);

 categoriesListView.setOnItemClickListener(this);
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent i=new Intent(getBaseContext(), NewllyAdded.class);
                        startActivity(i);
                        break;
                    case R.id.navigation_Categories:
                        Intent ii=new Intent( getBaseContext(),Categories_Activity.class);
                        startActivity(ii);
                        break;

                    case R.id.navigation_notifications:
                        break;
                    case R.id.navigation_profile:
                        Intent intent1=new Intent( getBaseContext(),profile.class);
                        startActivity(intent1);

                        break;
                    case R.id.navigation_search:
                        Intent intent=new Intent( getBaseContext(),productMain.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        };

















        String ImageurlBoutique = "https://i.postimg.cc/rF25vLyd/boutique-logo-1-vector-18631407.jpg";
        String ImageurlBank =" https://i.postimg.cc/dtQV7W4H/bank.jpg";
        String ImageurlMarket = "https://i.postimg.cc/7PGNFVgR/markets.png";
        String ImageurlResturant = "https://i.postimg.cc/DwM03qQC/depositphotos-118355644-stock-illustration-restaurant-logo-desig.jpg";

        categoriesObjects = new categoriesObject(ImageurlMarket);
        categoriesObjectArrayList.add(categoriesObjects);
        categoriesObjects = new categoriesObject(ImageurlResturant);
        categoriesObjectArrayList.add(categoriesObjects);

        categoriesObjects = new categoriesObject(ImageurlBoutique);

        categoriesObjectArrayList.add(categoriesObjects);

        categoriesObjects = new categoriesObject(ImageurlBank);

        categoriesObjectArrayList.add(categoriesObjects);

    categoriesAdapters   = new categoriesAdapter(Categories_Activity.this, categoriesObjectArrayList);
        categoriesAdapters.notifyDataSetChanged();
        categoriesListView.setAdapter(categoriesAdapters);

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int itemposition, long id) {
        categoriesObject objectView = categoriesObjectArrayList.get(itemposition);
        Log.d("hhh","hh"+itemposition);

    }
}
