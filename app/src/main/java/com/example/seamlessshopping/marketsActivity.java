package com.example.seamlessshopping;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AdapterView;
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

public class marketsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    ListView categoriesListView;
    marketObject marketObject1;
    marketAdapter marketAdapter1;
    ArrayList<marketObject> marketObjectArrayList = new ArrayList<marketObject>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_);
        Intent intent=getIntent();
        String cat =intent.getStringExtra("categoryID");
        Toast.makeText(this, cat, Toast.LENGTH_SHORT).show();
        String url="http://192.168.137.1//marketPage.php?categoryid="+cat;
        dataSaving(url);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        categoriesListView = (ListView) findViewById(R.id.listViewcategories);


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
    private void dataSaving(String url) {

        RequestQueue queue = Volley.newRequestQueue(this);  //
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {

                            JSONArray responseArray= jsonObject.getJSONArray("marketList");
                            Log.i("Response",responseArray+"");
                            Log.i("Response",jsonObject+"");

                            //Parse the JSON response array by iterating over it
                            //categoriesObjectArrayList.clear();

                            for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject response = responseArray.getJSONObject(i);
                                String marketImage=response.getString("marketImage");
                                String marketfoodname=response.getString("marketfoodname");
                                String idmarket = response.getString("idmarket");
                                String categoryid=response.getString("categoryID");

                                marketObject1=new marketObject(marketImage,idmarket,marketfoodname,categoryid);
                                marketObjectArrayList.add(marketObject1);

                                marketAdapter1 = new marketAdapter( marketObjectArrayList,marketsActivity.this);
                                marketAdapter1.notifyDataSetChanged();
                                categoriesListView.setAdapter(marketAdapter1);



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
}
