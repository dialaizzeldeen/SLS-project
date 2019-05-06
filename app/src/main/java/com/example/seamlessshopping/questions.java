package com.example.seamlessshopping;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
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

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class questions extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    EditText locQ,timeQ;
    TextView dateQ;
    int totalsum=0;
    public static final String usersum="usersum";
    String urlbalance="http://"+ippage.ip+"/updatebank.php";

    Button sumbitQ;
    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    private String id="0";
   public static int balances;
    String url="http://"+ippage.ip+":8978/";


BottomNavigationView navigation;
    private int year, month, day;
    private DatePicker datePicker;
    private Calendar calendar;
    String cardNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();

        setContentView(R.layout.activity_questions);
        locQ=findViewById(R.id.LocId);
        timeQ=findViewById(R.id.timeID);
        dateQ=findViewById(R.id.dateID);
        sumbitQ=findViewById(R.id.submitQuestion);

        navigation= (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);


        sumbitQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ss= getIntent().getIntExtra("totalsum",0);
                Toast.makeText(getApplicationContext(),"totalsum"+ss,Toast.LENGTH_LONG).show();
Intent i =new Intent(questions.this , bankInfo.class);
i.putExtra("totalsum",ss);
startActivity(i);
                sendDataToServer();
            }
        });

        //setBalance(urlbalance);
    }


    public void setDate(View view) {
        showDialog(999);
    //    Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {

        String monthName = (String) android.text.format.DateFormat.format("MMMM", month);
     dateQ.setText(new StringBuilder().append(day)
                .append(monthName).append(year));
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
                js.put("dateQ",dateQ.getText().toString());
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
            case R.id.navigation_cart:

                Intent Cart=new Intent(questions.this,cart.class);
                startActivity(Cart) ;

                break;
            case R.id.navigation_profile:

                Intent profile=new Intent(questions.this,profilecategory.class);
                startActivity(profile) ;
                break;
            case R.id.navigation_search:

                Intent search=new Intent(questions.this,searching.class);
                startActivity(search) ;

                break;
        }
        return false;    }













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
        } else if (1 == seletedItemId) {
            Intent Cart = new Intent(questions.this, cart.class);
            startActivity(Cart);


        } else {
            super.onBackPressed();
        }
    }
    public void getData(){
        SharedPreferences sharedPreferences=getSharedPreferences(shared_pres,MODE_PRIVATE);
        id=sharedPreferences.getString(iduser,"0");
    totalsum=sharedPreferences.getInt(usersum,0);

Toast.makeText(this,""+id,Toast.LENGTH_LONG).show();
    }



          }