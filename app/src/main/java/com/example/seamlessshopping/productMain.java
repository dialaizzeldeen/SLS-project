package com.example.seamlessshopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
public class productMain extends AppCompatActivity {
    GridView gridView;
    private static final String NEW_LINE = "\n\n";
    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    private String id="0";
    TextView textView;
    productsObject productObject;
    String url;
    productAdapter productAdapter;
    String search;
    ArrayList<productsObject> productsObjectArrayList = new ArrayList<productsObject>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);




        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);



        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        break;
                    case R.id.navigation_Categories:
                      //   Intent categorie=new Intent(productMain.this,Categories_Activity.class);
                        //startActivity(categorie);
                    case R.id.navigation_notifications:break;
                    case R.id.navigation_profile:
                        break;                    case R.id.navigation_search:
                        break;
                }
                return false;
            }
        };



        gridView = (GridView) findViewById(R.id.gridView);
        dataSaving(url = "http://192.168.1.12/product.php");
        productAdapter = new productAdapter(productMain.this, productsObjectArrayList);
        productAdapter.notifyDataSetChanged();
        gridView.setAdapter(productAdapter);



        final EditText text = findViewById(R.id.search);
        text.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                {  productsObjectArrayList.clear();


                    search=text.getText().toString();
                    url = "http://192.168.1.12/search.php?namesearch=" + search;
                    Log.d("hhh","j"+url);
                    dataSaving(url);

                }
                else {              dataSaving("http://192.168.1.12/product.php");
                }

            }
        });




    }


    private void dataSaving(String url) {


        RequestQueue queue = Volley.newRequestQueue(this);  //
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {

                            JSONArray responseArray= jsonObject.getJSONArray("products");
                            Log.i("Response",responseArray+"");
                            Log.i("Response",jsonObject+"");
                            StringBuilder textViewData = new StringBuilder();
                            //Parse the JSON response array by iterating over it
                            productsObjectArrayList.clear();

                            for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject response = responseArray.getJSONObject(i);
                                Integer productId=response.getInt("id");
                                String name = response.getString("name");
                                Integer quantity = response.getInt("quantity");
                                String imageurl = response.getString("imageurl");
                                String price = response.getString("price");
                                textViewData.append("quantity: ")
                                        .append(quantity.toString()).append(NEW_LINE);
                                textViewData.append("Name: ").append(name).append(NEW_LINE);
                                textViewData.append("imageurl: ").append(imageurl).append(NEW_LINE);
                                textViewData.append("price: ").append(price).append(NEW_LINE);

                                productObject = new productsObject(productId,name,quantity,imageurl,price);
                                productsObjectArrayList.add(productObject);
                                productAdapter = new productAdapter(productMain.this, productsObjectArrayList);
                                productAdapter.notifyDataSetChanged();
                                gridView.setAdapter(productAdapter);



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

    public void refresh()
    {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                productAdapter.notifyDataSetChanged();
                gridView.invalidate();
            }
        });

    }
    public void getData(){
        SharedPreferences sharedPreferences=getSharedPreferences(shared_pres,MODE_PRIVATE);
        id=sharedPreferences.getString(iduser,"0");
        Log.d("response  ",id);
    }



}


/**
 *
 *
 *
 *     gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
 *             @Override
 *             public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
 *                 // TODO Auto-generated method stub
 *
 *                 /* appending I Love with car brand names */
/** *String value="I Love "+adapterView.getItemAtPosition(position);
 *                 /* Display the Toast */
       /*  Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
         }
         });**/
