package com.example.seamlessshopping;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.internal.Internal;


public class profile extends AppCompatActivity {

    TextView usernameP;
    EditText  genderP,locationP,bdayP,mobileP,personalemailP;
    private static final String NEW_LINE = "\n\n";
    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    private String id="0";
    Button saveP;
    ArrayList<ProfileObject> profileObjectArrayList;
    ProfileObject profileObject1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getData();
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        String url="http://192.168.1.9/profilePage.php";


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        usernameP=(TextView) findViewById(R.id.usernameP);
        genderP=(EditText) findViewById(R.id.genderP);
        saveP=findViewById(R.id.saveP);

        bdayP=(EditText) findViewById(R.id.bdayP);
        mobileP=(EditText)findViewById(R.id.mobileP);
        personalemailP=(EditText)findViewById(R.id.personalemailP);

        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent i=new Intent(getBaseContext(), NewllyAdded.class);
                        startActivity(i);
                        break;
                    case R.id.navigation_Categories:
                        Intent ii=new Intent( getBaseContext(),Categories_Activity.class);
                        startActivity(ii);
                        break;

                    case R.id.navigation_notifications:
                        break;
                    case R.id.navigation_profile:
                        Intent intent1=new Intent( getBaseContext(),profile.class);
                        startActivity(intent1);

                        break;
                    case R.id.navigation_search:
                        Intent intent=new Intent( getBaseContext(),productMain.class);
                        startActivity(intent);
                        break;
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
                                usernameP.setText(name.toString());
                                genderP.setText(gender.toString());
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

    public void getData(){
        SharedPreferences sharedPreferences=getSharedPreferences(shared_pres,MODE_PRIVATE);
        id=sharedPreferences.getString(iduser,"0");
        Log.d("response  ",id);
    }
    public void upDate(View v){
        profileObject1= new ProfileObject(genderP.toString(),bdayP.toString(),mobileP.toString(),personalemailP.toString());
        String emaill=profileObject1.getPersonalemailP();

        Log.i("Response",emaill);
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        String urll = "http://192.168.1.9/update.php";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest postRequest = new StringRequest(Request.Method.POST, urll,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        if(response=="true")
                            Toast.makeText(getApplicationContext(), "connection problem", Toast.LENGTH_SHORT).show();
                        else{
                            Toast.makeText(getApplicationContext(), "data updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("gender",profileObject1.getGenderP().toString());
                params.put("userid",id.toString());
                params.put("bday", profileObject1.getBdayP().toString());
                params.put("mobile",profileObject1.getMobileP().toString());
                params.put("personalemail",profileObject1.getPersonalemailP().toString());


                return params;
            }
        };
        queue.add(postRequest);

    }

   }
