package com.example.seamlessshopping;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class marketsActivity extends AppCompatActivity {
    ListView categoriesListView;
    categoriesObject categoriesObjects;
    ArrayList<categoriesObject> categoriesObjectArrayList = new ArrayList<categoriesObject>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);



        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        return true;
                    case R.id.navigation_Categories:
                        // Intent categorie=new Intent(this,Categories_Activity.class);
                        //startActivity(categorie);
                        return true;
                    case R.id.navigation_notifications:
                        return true;
                    case R.id.navigation_profile:
                        return true;
                    case R.id.navigation_search:
                        return true;
                }
                return false;
            }
        };

















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
}
