package com.example.seamlessshopping;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;

import okhttp3.internal.Internal;


public class profile extends AppCompatActivity {

    String url="http://192.168.1.9/profilePage.php";
    Button save;
    EditText usernameP, genderP,locationP,bdayP,mobileP,personalemailP;
    private static final String NEW_LINE = "\n\n";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        usernameP=findViewById(R.id.username);
        genderP=findViewById(R.id.gender);
        locationP=findViewById(R.id.location);
        bdayP=findViewById(R.id.bday);
        mobileP=findViewById(R.id.mobile);
        personalemailP=findViewById(R.id.personalemail);

        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        return true;
                    case R.id.navigation_Categories:
                        // Intent categorie=new Intent(this,Categories_Activity.class);
                        //startActivity(categorie);
                        return true;
                    case R.id.navigation_notifications:
                        return true;
                    case R.id.navigation_profile:
                        return true;
                    case R.id.navigation_search:
                        return true;
                }
                return false;
            }
        };

        dataSaving(url);


    }
    private void dataSaving(String url) {


        RequestQueue queue = Volley.newRequestQueue(this);  //
        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {

                            JSONArray responseArray= jsonObject.getJSONArray("profileData");
                            Log.i("Response",responseArray+"");
                            Log.i("Response",jsonObject+"");
                            StringBuilder textViewData = new StringBuilder();
                            //Parse the JSON response array by iterating over it

                            for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject response = responseArray.getJSONObject(i);
                                String name = response.getString("username");
                                String personE=response.getString("personalemail");
                                String gender = response.getString("gender");
                                String bday = response.getString("bday");
                                Integer mobile = response.getInt("mobile");
                                String loc=response.getString("location");
                                usernameP.setText(name.toString());
                                genderP.setText(gender.toString());
                                locationP.setText(loc.toString());
                                bdayP.setText(bday.toString());
                                mobileP.setText(mobile.toString());
                                personalemailP.setText(personE.toString());


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

    public void saveP(View v){
        String type ="save";

    }}