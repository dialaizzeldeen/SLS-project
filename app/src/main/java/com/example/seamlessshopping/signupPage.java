package com.example.seamlessshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class signupPage extends AppCompatActivity {

    Button signup;
    EditText email , username, Fname, Lname, phoneNo,password;
    String signup_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        signup=findViewById(R.id.signup);
        email =findViewById(R.id.addedmail);
        username=findViewById(R.id.username);
        Fname=findViewById(R.id.addedFirstName);
        Lname=findViewById(R.id.addedLastName);
        phoneNo=findViewById(R.id.addedphone);
        password=findViewById(R.id.addedPassword);
        signup_url="http://"+ippage.ip+"/signup.php";


    }
    public void onSginup(View v){
        Log.i("Response",email.toString());
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest postRequest = new StringRequest(Request.Method.POST, signup_url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        if(response=="true") {
                            Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent i= new Intent(signupPage.this,loginPage.class);
                            startActivity(i);
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
                params.put("fnameKey",Fname.getText().toString());
                params.put("lnameKey",Lname.getText().toString());
                params.put("usernamekey",username.getText().toString());
                params.put("emailKey",email.getText().toString());
                params.put("passwordKey", password.getText().toString());
                params.put("phoneKey",phoneNo.getText().toString());


                return params;
            }
        };
        queue.add(postRequest);
    }


}
//192.168.1.4