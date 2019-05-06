package com.example.seamlessshopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;
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
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class NewllyAdded extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {
    GridView gridView;
    private static final String NEW_LINE = "\n\n";
    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    private String id;
    public static final String usernamedb="idusername";
    public static final String userpassworddb="iduserpassword";
    String idddd;
    String usernameshared;
    String passwordshared;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {
            R.drawable.markets,R.drawable.restuarant,R.drawable.boutiqueslide,R.drawable.banksldie};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    newllyAddedObject newllyAddedObject1;
    String url;
    newllyAddedAdapter newllyAddedAdapter1;
    ArrayList<productsObject> newllyAddedObjectArrayList = new ArrayList<productsObject>();
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlly_added);
        init();
        navigation= (BottomNavigationView) findViewById(R.id.navigation);
        getData();
        // Toast.makeText(this, idddd+usernameshared+passwordshared, Toast.LENGTH_SHORT).show();
        navigation.setOnNavigationItemSelectedListener(this);

        gridView = (GridView) findViewById(R.id.gridViewNew);
        dataSaving(url = "http://"+ippage.ip+"/newllyAdded.php");
        gridView.setAdapter(newllyAddedAdapter1);
    }
    private void dataSaving(String url) {


        RequestQueue queue = Volley.newRequestQueue(this);  //
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {

                            JSONArray responseArray= jsonObject.getJSONArray("newllyAdded");
                            Log.i("Response",responseArray+"");
                            Log.i("Response",jsonObject+"");
                            StringBuilder textViewData = new StringBuilder();
                            //Parse the JSON response array by iterating over it
                            productsObject newllyAddedObject2;
                            newllyAddedAdapter newllyAddedAdapter;
                            for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject response = responseArray.getJSONObject(i);

                                // newllyAddedObject newllyAddedObject2 = new newllyAddedObject( name,quantity, price ,imageurl,idmarket,marketfoodname);
                                //   ;



                                Integer productId=response.getInt("id");
                                String name = response.getString("name");
                                String marketName=response.getString("marketfoodname");
                                Integer marketID=response.getInt("idmarket");
                                Integer quantity = response.getInt("quantity");
                                String imageurl = response.getString("imageurl");
                                String price = response.getString("price");
                                textViewData.append("quantity: ")
                                        .append(quantity.toString()).append(NEW_LINE);
                                textViewData.append("Name: ").append(name).append(NEW_LINE);
                                textViewData.append("imageurl: ").append(imageurl).append(NEW_LINE);
                                textViewData.append("price: ").append(price).append(NEW_LINE);

                                newllyAddedObject2= new productsObject(productId,name,quantity,imageurl,price,marketName,marketID);
                                newllyAddedObjectArrayList.add(newllyAddedObject2);
                                newllyAddedAdapter= new newllyAddedAdapter(NewllyAdded.this, newllyAddedObjectArrayList);
                                newllyAddedAdapter.notifyDataSetChanged();
                                gridView.setAdapter(newllyAddedAdapter);






                            }
                            //   textView.setText(textViewData.toString());
                            Log.d("response","j"+textViewData);
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
    public void onBackPressed() {
        int seletedItemId = navigation.getSelectedItemId();
        if (0 == seletedItemId) {
            Intent home = new Intent(NewllyAdded.this, NewllyAdded.class);
            startActivity(home);
        } else if (2 == seletedItemId) {

            Intent categorie = new Intent(NewllyAdded.this, Categories_Activity.class);
            startActivity(categorie);
        } else if (3 == seletedItemId) {
            Intent profile = new Intent(NewllyAdded.this, profilecategory.class);
            startActivity(profile);
        }
        else if (1 == seletedItemId) {
            Intent Cart = new Intent(NewllyAdded.this, cart.class);
            startActivity(Cart);

        }
        else {
            super.onBackPressed();
        }

    }
    public void getData(){
        SharedPreferences sharedPreferences=getSharedPreferences(shared_pres,MODE_PRIVATE);
        idddd=sharedPreferences.getString(iduser,"0");
        usernameshared=sharedPreferences.getString(usernamedb,"0");
        passwordshared=sharedPreferences.getString(userpassworddb,"0");

    }
    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new slideshowAdapter(NewllyAdded.this,XMENArray));

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                finish();

                Intent home =new Intent(NewllyAdded.this,NewllyAdded.class);
                startActivity(home) ;
                break;

            case R.id.navigation_Categories:

                Intent categorie=new Intent(NewllyAdded.this,Categories_Activity.class);
                startActivity(categorie) ;


                break;
            case R.id.navigation_cart:
                Intent cart=new Intent(NewllyAdded.this,cart.class);
                startActivity(cart) ;


                break;
            case R.id.navigation_profile:

                    Intent profile = new Intent(NewllyAdded.this, profilecategory.class);
                    startActivity(profile);

                break;
            case R.id.navigation_search:
                Intent search=new Intent(NewllyAdded.this,searching.class);
                startActivity(search) ;

                break;
        }
        return false;
    }


}
