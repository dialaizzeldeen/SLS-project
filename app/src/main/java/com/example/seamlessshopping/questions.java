package com.example.seamlessshopping;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class questions extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    EditText locQ,timeQ,dataQ;
    Button sumbitQ;
    String url="http://"+ippage.ip+":8978/";
BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        locQ=findViewById(R.id.LocId);
        timeQ=findViewById(R.id.timeID);
        dataQ=findViewById(R.id.dateID);
        sumbitQ=findViewById(R.id.submitQuestion);
        navigation= (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);


        sumbitQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendDataToServer();
            }
        });
    }
    public void onBackPressed() {
        int seletedItemId = navigation.getSelectedItemId();
        if (0 == seletedItemId) {
            Intent home = new Intent(questions.this, NewllyAdded.class);
            startActivity(home);
        } else if (2 == seletedItemId) {

            Intent categorie = new Intent(questions.this, Categories_Activity.class);
            startActivity(categorie);
        } else if (3 == seletedItemId) {
            Intent profile = new Intent(questions.this, profilecategory.class);
            startActivity(profile);
        } else {
            super.onBackPressed();
        }

    }






    public void sendDataToServer(){
        RequestQueue queue = Volley.newRequestQueue(this);  //

        try {

            JSONArray jsonArray = new JSONArray();

            JSONObject js = new JSONObject();
            JSONObject js2 = new JSONObject();

            try {
                js.put("locQ",locQ.getText().toString());
                js.put("timeQ",timeQ.getText().toString());
                js.put("dateQ",dataQ.getText().toString());
                jsonArray.put(js);
                js2.put("information",jsonArray);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, js2,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(questions.this, response.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }, new Response.ErrorListener() {
                @Override

                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(questions.this, error.getMessage(), Toast.LENGTH_LONG).show();

                }
            })
                    ;

            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            Toast.makeText(questions.this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:

                Intent home =new Intent(questions.this,NewllyAdded.class);
                startActivity(home) ;
                break;

            case R.id.navigation_Categories:

                Intent categorie=new Intent(questions.this,Categories_Activity.class);
                startActivity(categorie) ;


                break;
            case R.id.navigation_notifications:
                break;
            case R.id.navigation_profile:



                Intent profile=new Intent(questions.this,profilecategory.class);
                startActivity(profile) ;
                break;
            case R.id.navigation_search:

        }
        return false;
    }
}
