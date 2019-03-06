package com.example.seamlessshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public class productMain extends AppCompatActivity {
    GridView gridView;
    private static final String NEW_LINE = "\n\n";

    TextView textView;
    productsObject productObject;
    productAdapter productAdapter;
    ArrayList<productsObject> productsObjectArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productActivity_layout);

        gridView = (GridView) findViewById(R.id.gridView);
        // textView = (TextView)findViewById(R.id.text);


        productsObjectArrayList = new ArrayList<productsObject>();
        dataSaving();
        productAdapter = new productAdapter(productMain.this, productsObjectArrayList);
        gridView.setAdapter(productAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub

                /* appending I Love with car brand names */
                String value = "I Love " + adapterView.getItemAtPosition(position);
                /* Display the Toast */
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void dataSaving() {

        final String url = "http://192.168.1.12/product.php";

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






                             //   productsObjectArrayList.add(productObject);
                                productObject = new productsObject(name,quantity,price,imageurl);

                                productsObjectArrayList.add(productObject);





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
                });

        // Access the RequestQueue through your singleton class.
        queue.add(jsObjRequest);
    }







}

