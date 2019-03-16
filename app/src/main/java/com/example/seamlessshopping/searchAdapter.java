package com.example.seamlessshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
public class searchAdapter extends BaseAdapter {
    ArrayList<productsObject> searchObjectArrayList;
    Context mContext;

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

        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
        TextView name = (TextView) convertView.findViewById(R.id.Name);
        ImageView imageUrls = (ImageView) convertView.findViewById(R.id.imageurl);


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
}


