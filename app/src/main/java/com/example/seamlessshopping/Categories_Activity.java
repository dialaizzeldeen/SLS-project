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
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Categories_Activity extends AppCompatActivity  implements AdapterView.OnItemClickListener , BottomNavigationView.OnNavigationItemSelectedListener  {
    ListView categoriesListView;
    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    private String id="0";
    categoriesObject categoriesObjects;  BottomNavigationView navigation;
    ArrayList<categoriesObject> categoriesObjectArrayList = new ArrayList<categoriesObject>();
    categoriesAdapter categoriesAdapters;
    String url="http://"+ippage.ip+"//categoriesInfo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_);
        getData();
      navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(this);

        categoriesAdapter categoriesAdapters;
        dataSaving(url);


        categoriesListView = (ListView) findViewById(R.id.listViewcategories);

 categoriesListView.setOnItemClickListener(this);





    }



    public void onBackPressed() {
        int seletedItemId =navigation.getSelectedItemId();
        if (0 == seletedItemId) {
            Intent home =new Intent(Categories_Activity.this,NewllyAdded.class);
            startActivity(home) ;
        }else if(2== seletedItemId){

        Intent categorie=new Intent(Categories_Activity.this,Categories_Activity.class);
        startActivity(categorie) ;}
        else if( 3 == seletedItemId){
        Intent profile=new Intent(Categories_Activity.this,profilecategory.class);
        startActivity(profile) ;}

        else {
            super.onBackPressed();
        }



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int itemposition, long id) {
        categoriesObject objectView = categoriesObjectArrayList.get(itemposition);
        Log.d("hhh","hh"+itemposition);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                Intent home =new Intent(Categories_Activity.this,NewllyAdded.class);
                startActivity(home) ;
                break;

            case R.id.navigation_Categories:

                Intent categorie=new Intent(Categories_Activity.this,Categories_Activity.class);
                startActivity(categorie) ;


                break;
            case R.id.navigation_notifications:
                break;
            case R.id.navigation_profile:

                if(id.equals("0")){
                    Intent login=new Intent(Categories_Activity.this,loginPage.class);
                    startActivity(login);

                }else {
                    Intent profile = new Intent(Categories_Activity.this, profilecategory.class);
                    startActivity(profile);
                }
                break;
            case R.id.navigation_search:
                Intent search=new Intent(Categories_Activity.this,searching.class);
                startActivity(search) ;

                break;
        }
        return false;
    }

    private void dataSaving(String url) {

        RequestQueue queue = Volley.newRequestQueue(this);  //
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {

                            JSONArray responseArray= jsonObject.getJSONArray("categories");
                            Log.i("Response",responseArray+"");
                            Log.i("Response",jsonObject+"");

                            //Parse the JSON response array by iterating over it
                            //categoriesObjectArrayList.clear();

                            for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject response = responseArray.getJSONObject(i);
                               String categoryID=response.getString("categoryID");
                               String categoryName=response.getString("categoryName");
                               String categoryImage = response.getString("categoryImage");

                                categoriesObjects=new categoriesObject(categoryID,categoryName,categoryImage);
                               categoriesObjectArrayList.add(categoriesObjects);

                                categoriesAdapters = new categoriesAdapter(Categories_Activity.this, categoriesObjectArrayList);
                                categoriesAdapters.notifyDataSetChanged();
                                categoriesListView.setAdapter(categoriesAdapters);



                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Display error message whenever an error occurs
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("Error",  error.getMessage());

                    }
                });

        // Access the RequestQueue through your singleton class.
        queue.add(jsObjRequest);
    }
    public void getData(){
        SharedPreferences sharedPreferences=getSharedPreferences(shared_pres,MODE_PRIVATE);
        id=sharedPreferences.getString(iduser,"0");
        Log.d("response  ",id);
    }
}
