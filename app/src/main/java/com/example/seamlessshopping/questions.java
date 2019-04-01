package com.example.seamlessshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class questions extends AppCompatActivity {
    EditText locQ,timeQ,dataQ;
    Button sumbitQ;
    String url="http://192.168.1.6:8978/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        locQ=findViewById(R.id.LocId);
        timeQ=findViewById(R.id.timeID);
        dataQ=findViewById(R.id.dateID);
        sumbitQ=findViewById(R.id.submitQuestion);

        sumbitQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendDataToServer();
            }
        });
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
}
