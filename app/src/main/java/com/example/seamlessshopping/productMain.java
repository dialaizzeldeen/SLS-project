package com.example.seamlessshopping;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class productMain extends AppCompatActivity {
    GridView gridView;
    private static final String NEW_LINE = "\n\n";
    EditText searchtext;
    TextView textView;
    String urllink = "http://192.168.137.1/search.php";
    productsObject productObject;
    productAdapter productAdapter;
    ArrayList<productsObject> productsObjectArrayList = new ArrayList<productsObject>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        searchtext=findViewById(R.id.searchtext);



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);



        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        break;
                    case R.id.navigation_Categories:
                        Intent categorie=new Intent(getBaseContext(),Categories_Activity.class);
                        startActivity(categorie);

                    case R.id.navigation_notifications:break;
                    case R.id.navigation_profile:
                        break;                    case R.id.navigation_search:
                        break;
                }
                return false;
            }
        };



        gridView = (GridView) findViewById(R.id.gridView);
        dataSaving();
        productAdapter = new productAdapter(productMain.this, productsObjectArrayList);
        gridView.setAdapter(productAdapter);



    }


    private void dataSaving() {



        RequestQueue queue = Volley.newRequestQueue(this);  //
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, urllink, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {

                            JSONArray responseArray= jsonObject.getJSONArray("products");
                            Log.i("Response",responseArray+"");
                            Log.i("Response",jsonObject+"");
                            StringBuilder textViewData = new StringBuilder();
                            //Parse the JSON response array by iterating over it
                            for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject response = responseArray.getJSONObject(i);
                                String name = response.getString("name");
                                Integer quantity = response.getInt("quantity");
                                String imageurl = response.getString("imageurl");
                                String price = response.getString("price");
                                textViewData.append("quantity: ")
                                        .append(quantity.toString()).append(NEW_LINE);
                                textViewData.append("Name: ").append(name).append(NEW_LINE);
                                textViewData.append("imageurl: ").append(imageurl).append(NEW_LINE);
                                textViewData.append("price: ").append(price).append(NEW_LINE);






                                productObject = new productsObject(name,quantity,imageurl,price);
                                ;
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
                })
        {
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params=new HashMap<String, String>();
                String searchname=searchtext.getText().toString();
                params.put("key",searchname);

                return params;
            }
        }
                ;

        // Access the RequestQueue through your singleton class.
        queue.add(jsObjRequest);
    }
    private void onrequest(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,urllink, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(productMain.this, response, Toast.LENGTH_SHORT).show();

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params=new HashMap<String, String>();
                String searchname=searchtext.getText().toString();
                params.put("namesearch",searchname);

                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void onsearch(View v){
        dataSaving();




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
