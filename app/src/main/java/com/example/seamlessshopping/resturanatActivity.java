package com.example.seamlessshopping;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class resturanatActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

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
        String ImageurlAatoutpizza= "https://i.postimg.cc/T32mZRG2/15036277-1423421534354241-3339396250817945020-n.png";
        String ImageurlwardResturant ="https://i.postimg.cc/0NFVk620/19756324-1408085675934992-3924642961369307931-n.png";
        String ImageurlKFc = "https://i.postimg.cc/BvB2yXPv/kfc.png";
        String ImageurlResturantdominoz = "https://i.postimg.cc/fRZFpq8j/26195460-2068440073413878-1996396889198920258-n.jpg";

        categoriesObjects = new categoriesObject(ImageurlKFc);
        categoriesObjectArrayList.add(categoriesObjects);
        categoriesObjects = new categoriesObject(ImageurlResturantdominoz);
        categoriesObjectArrayList.add(categoriesObjects);

        categoriesObjects = new categoriesObject(ImageurlAatoutpizza);

        categoriesObjectArrayList.add(categoriesObjects);

        categoriesObjects = new categoriesObject(ImageurlwardResturant);

        categoriesObjectArrayList.add(categoriesObjects);

        categoriesAdapter categoriesAdapters = new categoriesAdapter(resturanatActivity.this, categoriesObjectArrayList);
        categoriesAdapters.notifyDataSetChanged();
        categoriesListView.setAdapter(categoriesAdapters);


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

                Intent profile=new Intent(resturanatActivity.this,profilecategory.class);
                startActivity(profile) ;
                break;
            case R.id.navigation_search:
                finish();

                Intent search=new Intent(resturanatActivity.this,searching.class);
                startActivity(search) ;

                break;
        }
        return false;
    }
}
