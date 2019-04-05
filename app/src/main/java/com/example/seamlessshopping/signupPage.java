package com.example.seamlessshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
TextView errormail , errorlastname,errorfirstname,errorphoneno,errorpassword,errorusername;
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

errorfirstname=findViewById(R.id.errorfname);
errorlastname =findViewById(R.id.errorLastanme);
errorusername=findViewById(R.id.errorusername);
errormail=findViewById(R.id.errormail);
errorphoneno=findViewById(R.id.errorphone);
errorpassword=findViewById(R.id.errorpassword);
        errorpassword.setVisibility(View.INVISIBLE);
        errorusername.setVisibility(View.INVISIBLE);
        errorfirstname.setVisibility(View.INVISIBLE);
        errorlastname.setVisibility(View.INVISIBLE);
        errorphoneno.setVisibility(View.INVISIBLE);
        errormail.setVisibility(View.INVISIBLE);
        signup_url="http://"+ippage.ip+"/signup.php";

    }


    class textwatcher implements TextWatcher {
        int id;

        public textwatcher(int id) {
            this.id = id;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (id == 1) {
                errorusername.setVisibility(View.INVISIBLE);
            }

            if (id == 2) {
                errorfirstname.setVisibility(View.INVISIBLE);
            }
            if (id == 3) {
                errorlastname.setVisibility(View.INVISIBLE);
            }
            if (id == 4) {
                errorpassword.setVisibility(View.INVISIBLE);
            }

            if (id == 5) {
                errorphoneno.setVisibility(View.INVISIBLE);
            }

            if (id == 6) {
                errormail.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }

    }

    public void onSginup(View v){

        boolean isValidate = true;

        if (Fname.getText().toString().equals("")) {
            isValidate = false;
            errorfirstname.setVisibility(View.VISIBLE);
            //Fname.setError("Error");
        }
        if (Lname.getText().toString().equals("")) {
            isValidate = false;
            errorlastname.setVisibility(View.VISIBLE);
            // Lname.setError("Error");
        }
        if (username.getText().toString().equals("")) {
            errorusername.setVisibility(View.VISIBLE);
            //  username.setError("Error");
        }

        isValidate = false;
        if (email.getText().toString().equals("")) {
            isValidate = false;
            errormail.setVisibility(View.VISIBLE);
            // email.setError("Error");
        }


        if (password.getText().toString().equals("")) {
            isValidate = false;
            errorpassword.setVisibility(View.VISIBLE);
            //  password.setError("Error");
        }

        if (phoneNo.getText().toString().equals("")) {
            isValidate = false;
            errorphoneno.setVisibility(View.VISIBLE);
            // phoneNo.setError("Error");
        }


        if (ippage.isRegexValidate(email.getText().toString()) != true) {
            isValidate = false;
            errormail.setVisibility(View.VISIBLE);
            //email.setError("Error");
        }
        int phoneId = 5;
        int usernameId = 1;
        int fisrtnameId = 2;
        int lastnameId = 3;

        int passwordid= 4;
        int mailId = 6;
      email.addTextChangedListener(new textwatcher(mailId));


        username.addTextChangedListener(new textwatcher(usernameId));

        Fname.addTextChangedListener(new textwatcher(fisrtnameId));
        Lname.addTextChangedListener(new textwatcher(lastnameId));
       phoneNo.addTextChangedListener(new textwatcher(phoneId));
     password.addTextChangedListener(new textwatcher(passwordid));





        if (isValidate == true) {
            onSgin(signup_url);
        }
    }
    public void onSgin(String signup_url){
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