package com.example.seamlessshopping;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class newllyAddedAdapter extends BaseAdapter {
    ArrayList<newllyAddedObject> newllyAddedObjectArrayList;
    newllyAddedObject newllyAddedObject1;

    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    private String id="0";
    String url="http://"+ippage.ip+"/update.php";

    Context mContext;
    int positionitem;

    public newllyAddedAdapter( Context mContext,ArrayList<newllyAddedObject> newllyAddedObjectArrayList) {
        this.newllyAddedObjectArrayList = newllyAddedObjectArrayList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return newllyAddedObjectArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        positionitem = position;
        Log.d("hey", "i clicked you" + position);
        return newllyAddedObjectArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
           newllyAddedObject1 = newllyAddedObjectArrayList.get(position);
           getData();

        convertView = LayoutInflater.from(mContext).inflate(R.layout.newllyadded_rows, null);
        ImageButton imageurlNew= (ImageButton)convertView.findViewById(R.id.imageurlNew);
        TextView productnameNew=(TextView)convertView.findViewById(R.id.NamePNew);
        TextView pmNew=(TextView)convertView.findViewById(R.id.PMNew);
        TextView priceNew=(TextView)convertView.findViewById(R.id.priceNew);
        Button buttonNew =(Button)convertView.findViewById(R.id.checkboxNew);


        buttonNew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url ="http://"+ippage.ip+"/addToCart.php";
                if(id.equals("0")) {
                    AlertDialog.Builder builder= new AlertDialog.Builder(mContext);
                    builder.setMessage("please sign in");
                    builder.setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent i=new Intent(mContext,loginvolley.class);
                            mContext.startActivity(i);}
                    });

                    builder.setNegativeButton("Cancel", null);
                    builder.show();

                }
                {
                    Toast.makeText(mContext, id+"in", Toast.LENGTH_SHORT).show();
                    RequestQueue queue = Volley.newRequestQueue(mContext);
                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    if(response=="true")
                                        Toast.makeText(mContext, "connection problem", Toast.LENGTH_SHORT).show();
                                    else{
                                        Toast.makeText(mContext, "Done", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error.Response", error.toString());

                        }
                    }
                    ) {
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String>  params = new HashMap<String, String>();
                            params.put("namekey", newllyAddedObject1.getName().toString());
                            params.put("pricekey", newllyAddedObject1.getPrice().toString());
                            params.put("quntitykey",newllyAddedObject1.getQuantity().toString());
                            params.put("imagekey",newllyAddedObject1.getImageurl().toString());
                            params.put("productId",newllyAddedObject1.getId().toString());
                            params.put("CustomerID",id);
                            params.put("marketId",newllyAddedObject1.getIdmarket().toString());
                            params.put("marketName",newllyAddedObject1.getMarketfoodname().toString());

                            return params;
                        }
                    };
                    queue.add(postRequest);}}});


        imageurlNew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newllyAddedObject1=newllyAddedObjectArrayList.get(position);

                Intent i= new Intent(v.getContext(),productMain.class);
                String idmarket =newllyAddedObject1.getIdmarket();
                i.putExtra("idmarket",idmarket);
                v.getContext().startActivity(i);
                Log.d("Response",idmarket);
                }});


        priceNew.setText(newllyAddedObject1.getPrice()+" NIS");
        productnameNew.setText(newllyAddedObject1.getName());
        pmNew.setText(newllyAddedObject1.getMarketfoodname());



        String productImage = newllyAddedObject1.getImageurl();

        try {
            RequestManager requestManager = Glide.with(mContext);
// Create request builder and load image.
            RequestBuilder requestBuilder = requestManager.load(new URL( productImage));
// Show image into target imageview.
            requestBuilder.into(imageurlNew);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return convertView;
    }
    public void getData(){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(shared_pres,MODE_PRIVATE);
        id=sharedPreferences.getString(iduser,"0");
        Log.d("response  ",id);
    }

}