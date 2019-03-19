package com.example.seamlessshopping;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class marketsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    ListView categoriesListView;
    categoriesObject categoriesObjects;
    ArrayList<categoriesObject> categoriesObjectArrayList = new ArrayList<categoriesObject>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        categoriesListView = (ListView) findViewById(R.id.listViewcategories);
        String Imageurldaylight = "https://i.postimg.cc/7PB2WTbh/18446645-1449095245151165-4085136091615123560-n.png";
        String Imageurldreammall ="https://i.postimg.cc/FRrYP940/24301397-515337462174379-7992937270913746045-n.png";
        String ImageurlswalhiMarket = "https://i.postimg.cc/rwgsKBWm/swalhi.png";
        String ImageurlBravo = "https://i.postimg.cc/MHhR3htx/21430169-1731867953513191-8768882580645375130-n.png";

        categoriesObjects = new categoriesObject(ImageurlswalhiMarket);
        categoriesObjectArrayList.add(categoriesObjects);

        categoriesObjects = new categoriesObject(Imageurldreammall);
        categoriesObjectArrayList.add(categoriesObjects);

        categoriesObjects = new categoriesObject(Imageurldaylight);

        categoriesObjectArrayList.add(categoriesObjects);

        categoriesObjects = new categoriesObject(ImageurlBravo);

        categoriesObjectArrayList.add(categoriesObjects);

        categoriesAdapter categoriesAdapters = new categoriesAdapter(marketsActivity.this, categoriesObjectArrayList);
        categoriesAdapters.notifyDataSetChanged();
        categoriesListView.setAdapter(categoriesAdapters);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                finish();

                Intent home =new Intent(marketsActivity.this,NewllyAdded.class);
                startActivity(home) ;
                break;

            case R.id.navigation_Categories:
                finish();

                Intent categorie=new Intent(marketsActivity.this,Categories_Activity.class);
                startActivity(categorie) ;


                break;
            case R.id.navigation_notifications:
                break;
            case R.id.navigation_profile:
                finish();

                Intent profile=new Intent(marketsActivity.this,profilecategory.class);
                startActivity(profile) ;
                break;
            case R.id.navigation_search:
                finish();

                Intent search=new Intent(marketsActivity.this,searching.class);
                startActivity(search) ;

                break;
        }
        return false;
    }
}