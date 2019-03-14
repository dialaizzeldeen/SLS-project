package com.example.seamlessshopping;

import android.content.Context;
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

public class newllyAddedAdapter extends BaseAdapter {
    ArrayList<newllyAddedObject> newllyAddedObjectArrayList;
    newllyAddedObject newllyAddedObject1;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        final newllyAddedObject newllyAddedObject1 = newllyAddedObjectArrayList.get(position);
        convertView = LayoutInflater.from(mContext).inflate(R.layout.newllyadded_rows, null);
        ImageView imageurlNew= (ImageView)convertView.findViewById(R.id.imageurlNew);
        TextView productnameNew=(TextView)convertView.findViewById(R.id.productnameNew);
        TextView priceNew=(TextView)convertView.findViewById(R.id.priceNew);


        priceNew.setText(newllyAddedObject1.getPrice()+" NIS");
        productnameNew.setText(newllyAddedObject1.getName());

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

}