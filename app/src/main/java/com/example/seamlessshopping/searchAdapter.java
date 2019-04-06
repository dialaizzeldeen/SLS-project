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

public class searchAdapter extends BaseAdapter {
    ArrayList<productsObject> searchObjectArrayList;
    Context mContext;
    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    private String id="0";
    final String url ="http://"+ippage.ip+"/addToCart.php";


    public searchAdapter(Context context, ArrayList<productsObject> searchObjectArrayList) {
        this.mContext = context;
        this.searchObjectArrayList = searchObjectArrayList;
    }

    @Override
    public int getCount() {
        return searchObjectArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final productsObject productsObj = searchObjectArrayList.get(position);
        convertView = LayoutInflater.from(mContext).inflate(R.layout.searchingrrows, null);
        ImageButton addQuantity = (ImageButton) convertView.findViewById(R.id.addQuantity);
        ImageButton minusQuantity = (ImageButton) convertView.findViewById(R.id.minusQuantity);
        TextView marketName = (TextView) convertView.findViewById(R.id.marketName);
getData();
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
        TextView name = (TextView) convertView.findViewById(R.id.Name);
        ImageView imageUrls = (ImageView) convertView.findViewById(R.id.imageurl);
        Button addSearch=(Button)convertView.findViewById(R.id.checkboxProduct);
        getData();
        addSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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
                else {
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
                            params.put("namekey", productsObj.getName().toString());
                            params.put("pricekey", productsObj.getPrice().toString());
                            params.put("quntitykey",productsObj.getQuantity().toString());
                            params.put("imagekey",productsObj.getImageurl().toString());
                            params.put("productId",productsObj.getProductId().toString());
                            params.put("CustomerID",id);
                            params.put("marketId",productsObj.getMarketID().toString());
                            params.put("marketName",productsObj.getMarketfoodname().toString());

                            return params;
                        }
                    };
                    queue.add(postRequest);}}});


        addQuantity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                productsObj.setQuantity(productsObj.getQuantity() + 1);
                notifyDataSetChanged();
            }
        });


        minusQuantity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (productsObj.getQuantity() > 1) {
                    productsObj.setQuantity(productsObj.getQuantity() - 1);
                }
                notifyDataSetChanged();
            }
        });


        quantity.setText(productsObj.getQuantity().toString());
        price.setText(productsObj.getPrice() + " NIS");
        name.setText(productsObj.getName());
marketName.setText(productsObj.getMarketfoodname());
        String productImage = productsObj.getImageurl();

        try {
            RequestManager requestManager = Glide.with(mContext);
// Create request builder and load image.
            RequestBuilder requestBuilder = requestManager.load(new URL(productImage));
// Show image into target imageview.
            requestBuilder.into(imageUrls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  convertView;
    }

    public void getData(){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(shared_pres,MODE_PRIVATE);
        id=sharedPreferences.getString(iduser,"0");
        Log.d("response  ",id);
    }
}


