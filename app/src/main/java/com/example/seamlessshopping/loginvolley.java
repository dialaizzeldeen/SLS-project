package com.example.seamlessshopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class loginvolley extends AppCompatActivity {
    TextView createAccount;
    Button signin;
    EditText username, password;
    public static final String shared_pres = "sharedPres";
    public static final String iduser = "iduesr";
    public static final String usernamedb = "idusername";
    public static final String userpassworddb = "iduserpassword";
    String id = null;        String url;
    String usernameshared = "";
    String passwordshared = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);



        createAccount =(TextView)findViewById(R.id.createAccount);

    username = findViewById(R.id.usernameID);
    password = findViewById(R.id.passwordID);
    signin = findViewById(R.id.signin);

 getData();

   signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name=username.getText().toString();
                String passwordsss=password.getText().toString();
        url = "http://" + ippage.ip + "/loginpagevolley.php?username=" + name + "&password=" + passwordsss;

                dataSaving(url);


            }
        });

       createAccount.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {

            Intent i = new Intent(loginvolley.this, signupPage.class);
            startActivity(i);
        }
    });

}


    private void dataSaving(String url) {


        RequestQueue queue = Volley.newRequestQueue(this);  //
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {

                            JSONArray responseArray = jsonObject.getJSONArray("loginpage");
                            Log.i("Response", responseArray + "");
                            Log.i("Response", jsonObject + "");
                            StringBuilder textViewData = new StringBuilder();
                            //Parse the JSON response array by iterating over it

                          //  for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject response = responseArray.getJSONObject(0);
                            String userIdvolley = response.getString("CustomerID");
                                String usernamevolley = response.getString("userName");
                                String passwordvolley = response.getString("password");


                          //  textView.setText(textViewData.toString());
                            Savedata(userIdvolley, passwordvolley, usernamevolley);

                            Intent i = new Intent(loginvolley.this,NewllyAdded.class);
                          startActivity(i);

                            Log.d("response", "j" + textViewData);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Display error message whenever an error occurs
                        Toast.makeText(getApplicationContext(), "Incorrect userName or Password "+error.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("Error", error.getMessage());

                    }
                });

        // Access the RequestQueue through your singleton class.
        queue.add(jsObjRequest);

    }
    public void getData() {
        SharedPreferences sharedPreferences = getSharedPreferences(shared_pres, MODE_PRIVATE);
        id = sharedPreferences.getString(iduser, "0");
        usernameshared = sharedPreferences.getString(usernamedb, "0");
        passwordshared = sharedPreferences.getString(userpassworddb, "0");
        if (!usernameshared.equals("0") && !passwordshared.equals("0") && !id.equals("0")) {
            Intent i = new Intent(loginvolley.this,NewllyAdded.class);
            startActivity(i);
            Log.d("Dddd", "ddd" + usernameshared + passwordshared);
        } else {
            Log.d("Dddd", "nnnnnnn");
        }

    }


    public void Savedata(String USERIDD, String passwords, String usernames) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(shared_pres, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(iduser, USERIDD);
        editor.putString(userpassworddb, passwords);
        editor.putString(usernamedb, usernames);

        editor.apply();
    }
}