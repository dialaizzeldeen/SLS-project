package com.example.seamlessshopping;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
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

public class NewllyAdded extends AppCompatActivity {
    GridView gridView;
    private static final String NEW_LINE = "\n\n";

    newllyAddedObject newllyAddedObject1;
    String url;
    newllyAddedAdapter newllyAddedAdapter1;
    ArrayList<newllyAddedObject> newllyAddedObjectArrayList = new ArrayList<newllyAddedObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlly_added);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);



        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        break;
                    case R.id.navigation_Categories:

                    case R.id.navigation_notifications:break;
                    case R.id.navigation_profile:
                        break;                    case R.id.navigation_search:
                        break;
                }
                return false;
            }
        };


        gridView = (GridView) findViewById(R.id.gridViewNew);
        dataSaving(url = "http://192.168.1.9/newllyAdded.php");
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

                            for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject response = responseArray.getJSONObject(i);
                                Integer id=response.getInt("id");
                                String name = response.getString("name");
                                Integer quantity = response.getInt("quantity");
                                String imageurl = response.getString("imageurl");
                                String price = response.getString("price");
                                newllyAddedObject newllyAddedObject2 = new newllyAddedObject( name,quantity, price ,imageurl);
                                ;

                                newllyAddedObjectArrayList.add(newllyAddedObject2);
                                newllyAddedAdapter newllyAddedAdapter = new newllyAddedAdapter(NewllyAdded.this, newllyAddedObjectArrayList);
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
    }
