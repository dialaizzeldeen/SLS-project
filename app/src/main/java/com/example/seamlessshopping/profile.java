package com.example.seamlessshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;


public class profile extends AppCompatActivity {
   RequestQueue rq;
   String url=" 10.0.0.3";
    TextView username;
    Button save;
    EditText  gender,location,bday,mobile,personalemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        rq= Volley.newRequestQueue(this);
        username=findViewById(R.id.usernameID);
        gender=findViewById(R.id.gender);
        location=findViewById(R.id.location);
        bday=findViewById(R.id.bday);
        mobile=findViewById(R.id.mobile);
        personalemail=findViewById(R.id.personalemail);
        save=findViewById(R.id.save);

        sendjsonrequest();
    }
    public void sendjsonrequest (){
        JsonObjectRequest JsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
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

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rq.add(JsonObjectRequest);
    }
    public void saveP(View v){
        String usernamep=username.getText().toString();
        String genderp=gender.getText().toString();
        String locationp=location.getText().toString();
        String bdayp=bday.getText().toString();
        String mobilep=mobile.getText().toString();
        String personalemailp=personalemail.getText().toString();
        String type ="save";

        loginBackground loginBackground1= new loginBackground(this);
        loginBackground1.execute(type,usernamep,genderp,locationp,bdayp,mobilep,personalemailp);
    }
}

