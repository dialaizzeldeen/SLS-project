package com.example.seamlessshopping;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class profileAdapter extends BaseAdapter {
    ArrayList<ProfileObject> profileObjectArrayList;
    ProfileObject profileObject;
    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    private String id="0";
    Context mContext;
    int positionitem;

    public profileAdapter( Context mContext,ArrayList<ProfileObject> profileObjectArrayList) {
        this.profileObjectArrayList = profileObjectArrayList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return profileObjectArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        positionitem = position;
        Log.d("hey", "i clicked you" + position);
        return profileObjectArrayList.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ProfileObject profileObject = profileObjectArrayList.get(position);
        Button save=(Button) convertView.findViewById(R.id.save);
        EditText gender=(EditText)convertView.findViewById(R.id.genderP);
        EditText email=(EditText)convertView.findViewById(R.id.personalemailP);
        EditText bday=(EditText)convertView.findViewById(R.id.bdayP);
        EditText mobile=(EditText)convertView.findViewById(R.id.mobileP);

        gender.setText(profileObject.getGenderP().toString());
        email.setText(profileObject.getPersonalemailP().toString());
        bday.setText(profileObject.getBdayP().toString());
        mobile.setText(profileObject.getMobileP().toString());




        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(mContext, id, Toast.LENGTH_SHORT).show();
                String urll = "http://192.168.1.9/update.php";
                RequestQueue queue = Volley.newRequestQueue(mContext);

                StringRequest postRequest = new StringRequest(Request.Method.POST, urll,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
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
                        params.put("gender", profileObject.getGenderP().toString());
                        params.put("userid",id.toString());
                        params.put("bday", profileObject.getBdayP().toString());
                        params.put("mobile", profileObject.getMobileP().toString());
                        params.put("personalemail", profileObject.getPersonalemailP().toString());


                        return params;
                    }
                };
                queue.add(postRequest);}});
        return null;

    }
    public void getData(){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(shared_pres,MODE_PRIVATE);
        id=sharedPreferences.getString(iduser,"0");
        Log.d("response  ",id);
    }
}
