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
    String url="http://192.168.1.6/update.php";

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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.newllyadded_rows, null);
        ImageButton imageurlNew= (ImageButton)convertView.findViewById(R.id.imageurlNew);
        TextView productnameNew=(TextView)convertView.findViewById(R.id.NamePNew);
        TextView pmNew=(TextView)convertView.findViewById(R.id.PMNew);
        TextView priceNew=(TextView)convertView.findViewById(R.id.priceNew);


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