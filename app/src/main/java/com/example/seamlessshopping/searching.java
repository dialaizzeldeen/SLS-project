package com.example.seamlessshopping;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuBuilder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class searching extends AppCompatActivity implements View.OnClickListener {
    GridView gridView;
    private static final String NEW_LINE = "\n\n";

    TextView textView;
    productsObject productObject;
    String url;
    searchAdapter searchAdapter;
    String search;
    ArrayList<productsObject> searchObjectArrayList = new ArrayList<productsObject>();
ImageView imagecategories;
    String categories="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        final EditText textsearch = findViewById(R.id.search);

imagecategories=findViewById(R.id.imagecate);






imagecategories.setVisibility(View.INVISIBLE);


        textsearch.addTextChangedListener(new TextWatcher() {

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
                {imagecategories.setVisibility(View.INVISIBLE);
                    searchObjectArrayList.clear();
                    String  search=textsearch.getText().toString();
                    if(categories.equals("")){   url = "http://192.168.1.12/searchactivity2.php?namesearch=" + search;
                        Log.d("hhh","pleeeeeeee"+url);
                        dataSaving(url);   }
                    else
                    {   imagecategories.setVisibility(View.INVISIBLE);
                        url = "http://192.168.1.12/searchactivity.php?namesearch=" + search+"&categories="+categories;
                        Log.d("hhh","j"+url);
                        dataSaving(url);  }

                }
                else {
                    imagecategories.setVisibility(View.INVISIBLE);
                    searchObjectArrayList.clear();
                    searchAdapter.notifyDataSetChanged();

                }

            }
        });

textsearch.setOnClickListener(this);




        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        break;

                    case R.id.navigation_Categories:

                        break;
                    case R.id.navigation_notifications:
                        break;
                    case R.id.navigation_profile:
                        break;
                    case R.id.navigation_search:
                        break;
                }
                return false;
            }
        };


        gridView = (GridView) findViewById(R.id.gridView);
        // dataSaving(url = "http://192.168.1.12/joinsmarketproducts.php");
        searchAdapter = new searchAdapter(searching.this, searchObjectArrayList);
        searchAdapter.notifyDataSetChanged();
        gridView.setAdapter(searchAdapter);


    }


    private void dataSaving(String url) {


        RequestQueue queue = Volley.newRequestQueue(this);  //
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {

                            JSONArray responseArray = jsonObject.getJSONArray("products");
                            Log.i("Response", responseArray + "");
                            Log.i("Response", jsonObject + "");
                            StringBuilder textViewData = new StringBuilder();
                            //Parse the JSON response array by iterating over it
                            searchObjectArrayList.clear();

                            for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject response = responseArray.getJSONObject(i);
                                String name = response.getString("productname");
                                Integer quantity = response.getInt("quantity");
                                String imageurl = response.getString("imageurl");
                                String price = response.getString("price");
                                String marketfoodname = response.getString("marketname");

                                productObject = new productsObject(name, quantity, imageurl, price, marketfoodname);
                                searchObjectArrayList.add(productObject);
                                searchAdapter = new searchAdapter(searching.this, searchObjectArrayList);
                                searchAdapter.notifyDataSetChanged();
                                gridView.setAdapter(searchAdapter);


                            }
                            //   textView.setText(textViewData.toString());
                            Log.d("response", "j" + textViewData);
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
                        Log.e("Error", error.getMessage());

                    }
                });

        // Access the RequestQueue through your singleton class.
        queue.add(jsObjRequest);
    }


    @Override
    public void onClick(View v) {


showPopupWindow(v);


    }
    void showPopupWindow(View view) {
        PopupMenu popup = new PopupMenu(searching.this, view);
        try {
            Field[] fields = popup.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popup);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.restaurant){categories="restaurants";
                    imagecategories.setVisibility(View.VISIBLE);

                    imagecategories.setImageResource(R.drawable.restuarant);

                }
                else if(item.getItemId()==R.id.Boutique){categories="boutieque";
                  imagecategories.setVisibility(View.VISIBLE);
                  imagecategories.setImageResource(R.drawable.boutiquelogo);
                }
                else if (item.getItemId()==   R.id.Bank){
                    categories="bank";
                  imagecategories.setVisibility(View.VISIBLE);

                  imagecategories.setImageResource(R.drawable.bank);

                }
                else if(item.getItemId() == R.id.marketfood) {
                    categories = "market";
                   imagecategories.setVisibility(View.VISIBLE);

                    imagecategories.setImageResource(R.drawable.markets);
                }
                return true;
            }
        });
        popup.show();
    }


}
/**  @Override
public void onBackPressed() {

//   Toast.makeText (this,"onbackPressd",Toast.LENGTH_SHORT ).show();
EditText t=findViewById ( R.id.editText );
String S=t.getText ().toString ();
Intent hh = getIntent();

hh.putExtra(MainActivity.neededName,S);

setResult(RESULT_OK,hh);
super.onBackPressed ();
}
 **/



/**   @Override
protected void onCreate(Bundle savedInstanceState) {

super.onCreate ( savedInstanceState );
setContentView ( R.layout.activity_sec );
BottomNavigationView bar=getSupportActionBar ();

bar.setDisplayHomeAsUpEnabled ( true );

bar.setHomeAsUpIndicator ( R.drawable.exit );
EditText text=findViewById ( R.id.editText );

Intent i=getIntent ();
text.setText ( i.getStringExtra ( MainActivity.neededName ) );
int n=i.getIntExtra ( "prime" ,8);
System.out.println("i got this value for the paaasas------->"+n);


/**
}
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
