package com.example.seamlessshopping;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.Collections;
import java.util.Comparator;


public class searching extends AppCompatActivity implements  View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
  ListView gridView;
    private static final String NEW_LINE = "\n\n";

    TextView textView;
    productsObject productObject;
    String url;
    searchAdapter searchAdapter;
    String search;
    ArrayList<productsObject> searchObjectArrayList = new ArrayList<productsObject>();
    ImageView imagecategories;
    String categories="";
    BottomNavigationView navigation;
    TextView filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);


        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        final EditText textsearch = findViewById(R.id.search);

        imagecategories=findViewById(R.id.imagecate);

        filter=findViewById(R.id.filterprice);




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
                    if(categories.equals("")){   url = "http://"+ippage.ip+"/searchactivity2.php?namesearch=" + search;
                        Log.d("hhh","pleeeeeeee"+url);
                        dataSaving(url);   }
                    else
                    {   imagecategories.setVisibility(View.INVISIBLE);
                        url = "http://"+ippage.ip+"/searchactivity.php?namesearch=" + search+"&categories="+categories;
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


navigation.setOnNavigationItemSelectedListener(this);



        gridView = (ListView) findViewById(R.id.gridView);
        // dataSaving(url = "http://192.168.1.12/joinsmarketproducts.php");
        searchAdapter = new searchAdapter(searching.this, searchObjectArrayList);
        searchAdapter.notifyDataSetChanged();
        gridView.setAdapter(searchAdapter);


    }

    public void onBackPressed() {
        int seletedItemId = navigation.getSelectedItemId();
        if (0 == seletedItemId) {
            Intent home = new Intent(searching.this, NewllyAdded.class);
            startActivity(home);
        } else if (2 == seletedItemId) {

            Intent categorie = new Intent(searching.this, Categories_Activity.class);
            startActivity(categorie);
        } else if (3 == seletedItemId) {
            Intent profile = new Intent(searching.this, profilecategory.class);
            startActivity(profile);
        } else {
            super.onBackPressed();
        }

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
                                JSONObject   response = responseArray.getJSONObject(i);

                                String name = response.getString("productname");
                                Integer quantity = response.getInt("quantity");
                                String imageurl = response.getString("imageurl");
                                String price = response.getString("price");
                                String marketfoodname = response.getString("marketname");
                                productObject = new productsObject(name, quantity, imageurl, price, marketfoodname);
                                searchObjectArrayList.add(productObject);

                            }

                            searchAdapter = new searchAdapter(searching.this, searchObjectArrayList);
                            searchAdapter.notifyDataSetChanged();
                            gridView.setAdapter(searchAdapter);

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


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void pricelowest(View v) {
        Collections.sort(searchObjectArrayList, new Comparator<productsObject>() {
            @Override
            public int compare(productsObject t1, productsObject t2) {
                return Double.compare(Double.valueOf(t1.getPrice()), Double.valueOf(t2.getPrice()));
            }
        });

//         searchObjectArrayList.sort(Comparator.comparing(productsObject::getPrice).reversed());

        //  searchAdapter = new searchAdapter(searching.this, searchObjectArrayList);
        searchAdapter.notifyDataSetChanged();
        //gridView.setAdapter(searchAdapter);



    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void pricehighest(View v) {
        Collections.sort(searchObjectArrayList, new Comparator<productsObject>() {
            @Override
            public int compare(productsObject t1, productsObject t2) {
                return Double.compare(Double.valueOf(t2.getPrice()), Double.valueOf(t1.getPrice()));
            }
        });

//         searchObjectArrayList.sort(Comparator.comparing(productsObject::getPrice).reversed());

        //  searchAdapter = new searchAdapter(searching.this, searchObjectArrayList);
        searchAdapter.notifyDataSetChanged();
        //gridView.setAdapter(searchAdapter);



    }


    public void showmenu(final View view)
    {
        PopupMenu popup = new PopupMenu(searching.this, view);


        popup.getMenuInflater().inflate(R.menu.filtermenu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.lowtohigh){
                    pricelowest(view);
                }
                else if(item.getItemId()==R.id.hightolow){pricehighest(view);}

                return true;
            }
        });
        popup.show();}

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
                    searchObjectArrayList.clear();
                    searchAdapter.notifyDataSetChanged();

                    imagecategories.setImageResource(R.drawable.restuarant);

                }
                else if(item.getItemId()==R.id.Boutique){categories="boutieque";
                    imagecategories.setVisibility(View.VISIBLE);
                    searchObjectArrayList.clear();

                    searchAdapter.notifyDataSetChanged();
                    imagecategories.setImageResource(R.drawable.boutiquelogo);
                }
                else if (item.getItemId()==   R.id.Bank){
                    categories="bank";
                    searchObjectArrayList.clear();
                    searchAdapter.notifyDataSetChanged();
                    imagecategories.setVisibility(View.VISIBLE);

                    imagecategories.setImageResource(R.drawable.bank);

                }
                else if(item.getItemId() == R.id.marketfood) {
                    searchObjectArrayList.clear();
                    searchAdapter.notifyDataSetChanged();
                    categories = "market";
                    imagecategories.setVisibility(View.VISIBLE);

                    imagecategories.setImageResource(R.drawable.markets);
                }
                else if(item.getItemId()==R.id.allcategories){
                    imagecategories.setVisibility(View.VISIBLE);
                    searchObjectArrayList.clear();
                    searchAdapter.notifyDataSetChanged();
                    imagecategories.setImageResource(R.drawable.search);
                    categories="";
                    ;}
                return true;
            }

        });
        popup.show();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.navigation_home:

                    Intent home =new Intent(searching.this,NewllyAdded.class);
                    startActivity(home) ;
                    break;

                case R.id.navigation_Categories:

                    Intent categorie=new Intent(searching.this,Categories_Activity.class);
                    startActivity(categorie) ;


                    break;
                case R.id.navigation_notifications:
                    break;
                case R.id.navigation_profile:



                    Intent profile=new Intent(searching.this,profilecategory.class);
                    startActivity(profile) ;
                    break;
                case R.id.navigation_search:

            }
            return false;
        }
    }

