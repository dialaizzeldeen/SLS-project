package com.example.seamlessshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class profile extends AppCompatActivity {
    RequestQueue rq;
    TextView username;
    Button save;
    EditText gender, location, bday, mobile, personalemail;
    String url = "http://10.10.10.7/profilePage.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        rq = Volley.newRequestQueue(this);
        username = findViewById(R.id.usernameProfile);
        gender = findViewById(R.id.genderP);
        location = findViewById(R.id.locationP);
        bday = findViewById(R.id.bdayP);
        mobile = findViewById(R.id.mobileP);
        personalemail = findViewById(R.id.personalemailP);
        save = findViewById(R.id.saveP);
        dataSaving(url);
        //  sendjsonrequest();
    }

    private void dataSaving(String url) {


        RequestQueue queue = Volley.newRequestQueue(this);  //
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {

                            JSONArray responseArray = jsonObject.getJSONArray("profile");
                            Log.i("Response", responseArray + "");
                            Log.i("Response", jsonObject + "");
                            //StringBuilder textViewData = new StringBuilder();
                            //Parse the JSON response array by iterating over it
                            // productsObjectArrayList.clear();

                           /** for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject response = responseArray.getJSONObject(i);
                                String usernamee = response.getString("username");
                                String Gender = response.getString("gender");
                                String Location = response.getString("location");
                                String Bday = response.getString("bday");
                                String Mobile = response.getString("mobile");
                                String Personalemail = response.getString("personalemail");

                                Log.d("response", "j" + response);


                            }**/
                            //   textView.setText(textViewData.toString());
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














    public void sendjsonrequest () {}}
       /* JsonObjectRequest JsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                  String  usernamee=response.getString("username");
                    String  Gender=response.getString("gender");
                    String  Location=response.getString("location");
                    String  Bday=response.getString("bday");
                    String  Mobile=response.getString("mobile");
                    String  Personalemail=response.getString("personalemail");

                    username.setText(usernamee);
                    gender.setText(Gender);
                    location.setText(Location);
                    bday.setText(Bday);
                    mobile.setText(Mobile);
                    personalemail.setText(Personalemail);
                    Log.d("response",response.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rq.add(JsonObjectRequest);*/

