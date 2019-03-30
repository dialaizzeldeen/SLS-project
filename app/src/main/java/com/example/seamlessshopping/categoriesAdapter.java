package com.example.seamlessshopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class categoriesAdapter extends BaseAdapter {
    ArrayList<categoriesObject> categoriesObjectsArrayList;
    productsObject productsObj;
    categoriesObject categoriesObject1;
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

    public categoriesAdapter(Context context, ArrayList<categoriesObject> categoriesObjectsArrayList) {
        this.mContext = context;
        this.categoriesObjectsArrayList=categoriesObjectsArrayList;

    }


    @Override
    public int getCount() {
        return categoriesObjectsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        positionitem = position;
        Log.d("hey", "i clicked you" + position);
        return categoriesObjectsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
         categoriesObject productsObj = categoriesObjectsArrayList.get(position);
        convertView = LayoutInflater.from(mContext).inflate(R.layout.categories_rows, null);

        ImageView imageUrls = (ImageView) convertView.findViewById(R.id.imageurl);

        imageUrls.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                categoriesObject1=categoriesObjectsArrayList.get(position);



                Intent i= new Intent(v.getContext(),marketsActivity.class);
                String categoryID =categoriesObject1.getCategoryID();
                i.putExtra("categoryID",categoryID);
                v.getContext().startActivity(i);
                Log.d("Response",categoryID);
            }});







        String productImage = productsObj.getCategoryImage();

        try {
            RequestManager requestManager = Glide.with(mContext);
// Create request builder and load image.
            RequestBuilder requestBuilder = requestManager.load(new URL( productImage));
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
