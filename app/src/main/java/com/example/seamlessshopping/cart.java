package com.example.seamlessshopping;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
//import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class cart extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener {
    ListView listViewCart;
    cartObject cartObject1;
   public ArrayList<cartObject> cartObjectArrayList= new ArrayList<cartObject>();
    FloatingActionButton fab;
    Context context;
    CartAdapter cartAdapters;
    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    private String id="0";
    int sum=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                for(int value=0;value< cartObjectArrayList.size();value++){

                    int q=  cartObjectArrayList.get(value).getQuantityCart()*
                            Integer.parseInt(cartObjectArrayList.get(value).getPriceCart());
                    sum= sum+q;}

                //   textView.setText(textViewData.toString());

                Toast.makeText(getApplicationContext(), "heeeelo"+sum, Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(v.getContext(),questions.class);
                intent.putExtra("totalsum",sum);
                Log.d("response","totalsum"+ sum);

                startActivity(intent);

            }});

navigation.setOnNavigationItemSelectedListener(this);


        listViewCart = (ListView) findViewById(R.id.listViewCart);
        getData();

  String url ="http://"+ippage.ip+"/cartPage.php?userid="+id;
      dataSaving(url);


    }
    private void dataSaving(String url) {


        RequestQueue queue = Volley.newRequestQueue(this);  //
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {

                            JSONArray responseArray= jsonObject.getJSONArray("cartProducts");
                            Log.i("Response",responseArray+"");
                            Log.i("Response",jsonObject+"");
                            StringBuilder textViewData = new StringBuilder();
                            //Parse the JSON response array by iterating over it

                            for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject response = responseArray.getJSONObject(i);
                                String marketName=response.getString("marketName");
                                int transactionId=response.getInt("transactionId");
                                int productId=response.getInt("productId");
                                int marketId=response.getInt("marketId");
                                String name = response.getString("name");
                                Integer quantity = response.getInt("quantity");
                                String imageurl = response.getString("imageurl");
                                String price = response.getString("price");
                                cartObject1 = new cartObject(imageurl, name, price, quantity,marketName,transactionId,marketId,productId);


                            cartObjectArrayList.add(cartObject1);
                                cartAdapters = new CartAdapter(cart.this, cartObjectArrayList);
                                cartAdapters.notifyDataSetChanged();
                                listViewCart.setAdapter(cartAdapters);



                            }

                            Log.d("response","j"+cartObjectArrayList.size());
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:

                Intent home =new Intent(cart.this,NewllyAdded.class);
                startActivity(home) ;
                break;

            case R.id.navigation_Categories:

                Intent categorie=new Intent(cart.this,Categories_Activity.class);
                startActivity(categorie) ;


                break;
            case R.id.navigation_cart:


                break;
            case R.id.navigation_profile:
                if(id.equals("0")){
                    Intent login=new Intent(cart.this,loginvolley.class);
                    startActivity(login);

                }else {
                    Intent profile = new Intent(cart.this, profilecategory.class);
                    startActivity(profile);
                }
                break;
            case R.id.navigation_search:
                Intent search=new Intent(cart.this,searching.class);
                startActivity(search) ;

                break;
        }
        return false;
    }
    public void getData(){
        SharedPreferences sharedPreferences=getSharedPreferences(shared_pres,MODE_PRIVATE);
        id=sharedPreferences.getString(iduser,"0");
        Toast.makeText(this,"heloo id"+id,Toast.LENGTH_LONG).show();
        Log.d("response  ",id);
    }

/**    public void Savedata() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(shared_pres, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(usersum,sum );

        editor.apply();
    }

**/
}
