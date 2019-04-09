package com.example.seamlessshopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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


public class profile extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    TextView usernameP;
    EditText  fnameP,locationP,bdayP,mobileP,personalemailP,lnameP;
    private static final String NEW_LINE = "\n\n";
    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    private String id="0";
    Button saveP;
    TextView errormail , errorlastname,errorfirstname,errorphoneno,errorpassword,errorusername;

    ArrayList<ProfileObject> profileObjectArrayList;
    ProfileObject profileObject1;
    Context mContext;
    int positionitem;

    BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getData();
        errorfirstname=findViewById(R.id.errorfname);
        errorlastname =findViewById(R.id.errorLastanme);
        errormail=findViewById(R.id.errormail);
        errorphoneno=findViewById(R.id.errorphone);

        errorfirstname.setVisibility(View.INVISIBLE);
        errorlastname.setVisibility(View.INVISIBLE);
        errorphoneno.setVisibility(View.INVISIBLE);
        errormail.setVisibility(View.INVISIBLE);
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        String url="http://"+ippage.ip+"/profilePage.php?userid="+id;


        dataSaving(url);

   navigation = (BottomNavigationView) findViewById(R.id.navigation);
        usernameP=(TextView) findViewById(R.id.usernameP);
      fnameP=(EditText) findViewById(R.id.fnameP);
        lnameP=findViewById(R.id.lnameP);
        bdayP=(EditText) findViewById(R.id.bdayP);
        mobileP=(EditText)findViewById(R.id.mobileP);
        personalemailP=(EditText)findViewById(R.id.personalemailP);
        saveP=findViewById(R.id.saveP);
saveP.setOnClickListener(this);
        navigation.setOnNavigationItemSelectedListener(this);



    }

    @Override
    public void onClick(View v) {
        boolean isValidate = true;

        if (fnameP.getText().toString().equals("")) {
            isValidate = false;
            errorfirstname.setVisibility(View.VISIBLE);
            //Fname.setError("Error");
        }
        if (lnameP.getText().toString().equals("")) {
            isValidate = false;
            errorlastname.setVisibility(View.VISIBLE);
            // Lname.setError("Error");
        }


        if (personalemailP.getText().toString().equals("")) {
            isValidate = false;
            errormail.setVisibility(View.VISIBLE);
            // email.setError("Error");
        }


        if (mobileP.getText().toString().equals("")) {
            isValidate = false;
            errorphoneno.setVisibility(View.VISIBLE);
            // phoneNo.setError("Error");
        }


        if (ippage.isRegexValidate(personalemailP.getText().toString()) != true) {
            isValidate = false;
            errormail.setVisibility(View.VISIBLE);
            //email.setError("Error");
        }
        int phoneId = 5;
        int fisrtnameId = 2;
        int lastnameId = 3;

        int mailId = 6;



        fnameP.addTextChangedListener(new textwatcher(fisrtnameId));
        lnameP.addTextChangedListener(new textwatcher(lastnameId));
        mobileP.addTextChangedListener(new textwatcher(phoneId));
        personalemailP.addTextChangedListener(new textwatcher(mailId));


        if (isValidate == true) {
            upDate();
            Intent profile = new Intent(profile.this, profilecategory.class);
            startActivity(profile);
        }

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




    public void upDate(){

        profileObject1= new ProfileObject(fnameP.getText().toString(),lnameP.getText().toString(),bdayP.getText().toString(),mobileP.getText().toString(),personalemailP.getText().toString());
        String emaill=profileObject1.getPersonalemailP();

        Log.i("Response",emaill);
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        String urll = "http://"+ippage.ip+"/update.php";
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
                params.put("Lname",profileObject1.getLname().toString());
                params.put("userid",id.toString());
                params.put("Fname",profileObject1.getFname().toString());
                params.put("bday", profileObject1.getBdayP().toString());
                params.put("mobile",profileObject1.getMobileP().toString());
                params.put("personalemail",profileObject1.getPersonalemailP().toString());


                return params;
            }
        };
        queue.add(postRequest);

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
                                String Lname = response.getString("Lname");
                                String Fname= response.getString("Fname");

                                String bday = response.getString("bday");
                                Integer mobile = response.getInt("mobile");
                                usernameP.setText(name.toString());
                             fnameP.setText(Fname.toString());
                                lnameP.setText(Lname.toString());

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





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:

                Intent home =new Intent(profile.this,NewllyAdded.class);
                startActivity(home) ;
                break;

            case R.id.navigation_Categories:

                Intent categorie=new Intent(profile.this,Categories_Activity.class);
                startActivity(categorie) ;


                break;
            case R.id.navigation_notifications:
                break;
            case R.id.navigation_profile:
                if(id.equals("0")){
                    Intent login=new Intent(profile.this,loginPage.class);
                    startActivity(login);

                }else {
                    Intent profile = new Intent(profile.this, profilecategory.class);
                    startActivity(profile);
                }
                break;
            case R.id.navigation_search:


                Intent search=new Intent(profile.this,searching.class);
                startActivity(search) ;
                break;

        }
        return false;
    }


   }
