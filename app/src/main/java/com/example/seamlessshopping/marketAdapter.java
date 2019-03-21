package com.example.seamlessshopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class marketAdapter extends BaseAdapter {
    ArrayList<marketObject> marketObjectArrayList;
    marketObject marketObject1;
    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";

    private String id="0";
    Context mContext;
    int positionitem;
    //Intent editIntent;
    // ImageButton btnDelete;
    //ImageButton btnCall;
    //ImageButton btnEdit;
    TextView text;

    public marketAdapter(ArrayList<marketObject> marketObjectArrayList, Context mContext) {
        this.marketObjectArrayList = marketObjectArrayList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return marketObjectArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        positionitem = position;
        Log.d("hey", "i clicked you" + position);
        return marketObjectArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
         marketObject1 = marketObjectArrayList.get(position);
        convertView = LayoutInflater.from(mContext).inflate(R.layout.categories_rows, null);

        ImageView imageUrls = (ImageView) convertView.findViewById(R.id.imageurl);

        imageUrls.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               marketObject1=marketObjectArrayList.get(position);

                Intent i= new Intent(v.getContext(),productMain.class);
                String idmarket =marketObject1.getIdmarket();
                i.putExtra("idmarket",idmarket);
                v.getContext().startActivity(i);
                Log.d("Response",idmarket);
            }});







        String markerImage = marketObject1.getMarketImage();

        try {
            RequestManager requestManager = Glide.with(mContext);
// Create request builder and load image.
            RequestBuilder requestBuilder = requestManager.load(new URL( markerImage));
// Show image into target imageview.
            requestBuilder.into(imageUrls);
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
