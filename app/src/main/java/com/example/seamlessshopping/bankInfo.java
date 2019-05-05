package com.example.seamlessshopping;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

import java.util.HashMap;
import java.util.Map;

public class bankInfo extends AppCompatActivity  implements  BottomNavigationView.OnNavigationItemSelectedListener {
 EditText cardno,customerName,expdate,cvv;
 Button submitbutton;
    public int NotificationID = 0;
    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    private String id;
    public static final String usernamedb="idusername";
    public static final String userpassworddb="iduserpassword";
    String idddd;
    String usernameshared;
    String passwordshared;
    String urlupdate="http://"+ippage.ip+"/bankInfoget.php?updatebank="+idddd;

    String urlinsert="http://"+ippage.ip+"/bankInfo.php";
    public static final String ChannelID = "Services_Channel_ID_9";
    NotificationCompat.Action call1 = null;
    NotificationCompat.Action ignore = null;
    NotificationCompat.Builder notify = null;

    NotificationManager mNotificationManager = null;
    Bitmap largeIcon = null;
    Bitmap Picture = null; BottomNavigationView navigation;
    PendingIntent pending = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_info);
        cardno=findViewById(R.id.cardNo);
        getData();

        customerName=findViewById(R.id.customerName);
        expdate=findViewById(R.id.expireDate);
        cvv=findViewById(R.id.cvv);
       navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        submitbutton=findViewById(R.id.submit);

        String geturl="http://"+ippage.ip+"/bankInfoget.php?CustomerID="+idddd;



dataget(geturl);





    }

public void onviewws(View v) {
   // dataSaving(urlinsert);
    dataSaving(urlupdate);
    createNotificationChannel(getBaseContext());

    notify = new NotificationCompat.Builder(getBaseContext(), ChannelID);

    notify.setAutoCancel(true);
    //    notify.setOngoing ( true );
    notify.setSubText("Personal");
    notify.setContentTitle("Seamless shopping app");
    notify.setContentText("Your order is ready");
    notify.setSmallIcon(R.drawable.heey);
    notify.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.heey));
    notify.setColor(Color.YELLOW);
    notify.setColorized(true);
    Intent i = new Intent(this, NewllyAdded.class);
    PendingIntent h = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
    notify.setContentIntent(h);
    notify.setPriority(NotificationManagerCompat.IMPORTANCE_HIGH);


    notify.setUsesChronometer(true);


    mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    mNotificationManager.notify(NotificationID++, notify.build());
}



    private void createNotificationChannel(Context context) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = "My Own Channel";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(ChannelID, name, importance);
            channel.setImportance(NotificationManager.IMPORTANCE_HIGH);

            NotificationManager Manager = null;
            Manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Manager.createNotificationChannel(channel);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:

                Intent home =new Intent(bankInfo.this,NewllyAdded.class);
                startActivity(home) ;
                break;

            case R.id.navigation_Categories:

                Intent categorie=new Intent(bankInfo.this,Categories_Activity.class);
                startActivity(categorie) ;


                break;
            case R.id.navigation_cart:

                Intent Cart=new Intent(bankInfo.this,cart.class);
                startActivity(Cart) ;

                break;
            case R.id.navigation_profile:

                Intent profile=new Intent(bankInfo.this,profilecategory.class);
                startActivity(profile) ;
                break;
            case R.id.navigation_search:

                Intent search=new Intent(bankInfo.this,searching.class);
                startActivity(search) ;

                break;
        }
        return false;    }






    public void getData(){
        SharedPreferences sharedPreferences=getSharedPreferences(shared_pres,MODE_PRIVATE);
        idddd=sharedPreferences.getString(iduser,"0");
        usernameshared=sharedPreferences.getString(usernamedb,"0");
        passwordshared=sharedPreferences.getString(userpassworddb,"0");

    }






    public void onBackPressed() {
        int seletedItemId = navigation.getSelectedItemId();
        if (0 == seletedItemId) {
            Intent home = new Intent(bankInfo.this, NewllyAdded.class);
            startActivity(home);
        } else if (2 == seletedItemId) {

            Intent categorie = new Intent(bankInfo.this, Categories_Activity.class);
            startActivity(categorie);
        } else if (3 == seletedItemId) {
            Intent profile = new Intent(bankInfo.this, profilecategory.class);
            startActivity(profile);
        } else if (1 == seletedItemId) {
            Intent Cart = new Intent(bankInfo.this, cart.class);
            startActivity(Cart);


        } else {
            super.onBackPressed();
        }
    }


    private void dataget(String url) {


        RequestQueue queue = Volley.newRequestQueue(this);  //
        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {

                            JSONArray responseArray= jsonObject.getJSONArray("bankInfo");
                            Log.i("Response",responseArray+"");
                            Log.i("Response",jsonObject+"");
                            StringBuilder textViewData = new StringBuilder();
                            //Parse the JSON response array by iterating over it

                            for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject response = responseArray.getJSONObject(i);
                                String cardNos = response.getString("cardNo");
                                String customernames=response.getString("customername");
                                String cvvs = response.getString("cvv");
                                String datecard= response.getString("datecard");
                                cardno.setText(cardNos);
                                customerName.setText(customernames);
                                cvv.setText(cvvs);
                                expdate.setText(datecard);


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

    private void dataSaving(String url) {


        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        if(response=="true")
                            Toast.makeText(getApplicationContext(), "connection problem", Toast.LENGTH_SHORT).show();
                        else{
                            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", error.toString());

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
           params.put("customername",customerName.getText().toString());
                params.put("customerid",idddd.toString() );
                params.put("CVV",cvv.getText().toString());
                params.put("cardno",cardno.getText().toString());
                params.put("datecard",expdate.getText().toString());
              /*  params.put("customername","gg");
                params.put("customerid","55" );
                params.put("CVV","33");
                params.put("cardno","333");
                params.put("datecard","23\3");     **/

                return params;
            }
        };
        queue.add(postRequest);

        }}
