package com.example.seamlessshopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.ListView;
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

public class paymentHistory extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
   ListView paymentHistoryListView;
    paymentHistoryAdapter paymentHistoryAdapters;
    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    private String id="0";
    BottomNavigationView navigation;

    ArrayList<paymentHistoryObject> paymentHistoryObjectArrayList = new ArrayList<paymentHistoryObject>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        navigation= (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
       paymentHistoryListView = (ListView) findViewById(R.id.listViewpaymenthistory);
      //  dataSaving();

   //  paymentHistoryAdapters  = new paymentHistoryAdapter(paymentHistory.this, paymentHistoryObjectArrayList);
     // paymentHistoryListView.setAdapter(paymentHistoryAdapters);

        getData();

        final String url ="http://"+ippage.ip+"/joinsmarketpayment.php?CustomerID="+id;

        RequestQueue queue = Volley.newRequestQueue(this);  //192.168.1.12
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray responseArray= jsonObject.getJSONArray("marketpayment");
                            Log.i("Response",responseArray+"");
                            Log.i("Response",jsonObject+"");
                            StringBuilder textViewData = new StringBuilder();
                            //Parse the JSON response array by iterating over it
                            for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject response = responseArray.getJSONObject(i);
                             Integer marketid = response.getInt("id");

                                String marketfoodname = response.getString("marketfoodname");
                              String dateofpurchase= response.getString("dateofpurchase");
                                String recieptnumber = response.getString("recieptnumber");
                                String totalprice = response.getString("totalprice");
Log.d("thisssss",marketfoodname+dateofpurchase+recieptnumber+totalprice);

paymentHistoryObject paymentHistoryObjects=new paymentHistoryObject(dateofpurchase,totalprice,recieptnumber,marketfoodname);

                                paymentHistoryObjectArrayList.add(paymentHistoryObjects);
                                paymentHistoryAdapters = new paymentHistoryAdapter(paymentHistory.this, paymentHistoryObjectArrayList);
                              paymentHistoryAdapters.notifyDataSetChanged();
                              paymentHistoryListView.setAdapter(paymentHistoryAdapters);



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

    public void getData(){
        SharedPreferences sharedPreferences=getSharedPreferences(shared_pres,MODE_PRIVATE);
        id=sharedPreferences.getString(iduser,"0");
        Log.d("response  ",id);
    }





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:

                Intent home =new Intent(paymentHistory.this,NewllyAdded.class);
                startActivity(home) ;
                break;

            case R.id.navigation_Categories:

                Intent categorie=new Intent(paymentHistory.this,Categories_Activity.class);
                startActivity(categorie) ;


                break;
            case R.id.navigation_cart:

                Intent Cart=new Intent(paymentHistory.this,cart.class);
                startActivity(Cart) ;

                break;
            case R.id.navigation_profile:
                if(id.equals("0")){
                    Intent login=new Intent(paymentHistory.this,loginvolley.class);
                    startActivity(login);

                }else {
                    Intent profile = new Intent(paymentHistory.this, profilecategory.class);
                    startActivity(profile);
                }
                break;
            case R.id.navigation_search:

                break;
        }
        return false;    }



    public void onBackPressed() {
        int seletedItemId = navigation.getSelectedItemId();
        if (0 == seletedItemId) {
            Intent home = new Intent(paymentHistory.this, NewllyAdded.class);
            startActivity(home);
        } else if (2 == seletedItemId) {

            Intent categorie = new Intent(paymentHistory.this, Categories_Activity.class);
            startActivity(categorie);
        } else if (3 == seletedItemId) {
            Intent profile = new Intent(paymentHistory.this, profilecategory.class);
            startActivity(profile);
        } else if (1 == seletedItemId) {
            Intent Cart = new Intent(paymentHistory.this, cart.class);
            startActivity(Cart);


        } else {
            super.onBackPressed();
        }
    }
}