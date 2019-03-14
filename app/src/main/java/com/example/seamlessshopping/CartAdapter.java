package com.example.seamlessshopping;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    Context mContext;int positionitem;
    private ArrayList<cartObject>cartObjectArrayList;
    cartObject cartObject1;

    public CartAdapter(Context mContext, ArrayList<cartObject> cartObjectArrayList) {
        this.mContext = mContext;
        this.cartObjectArrayList = cartObjectArrayList;
    }

    @Override
    public int getCount() {
        return  cartObjectArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        positionitem=position;
        Log.d("hey", "i clicked you" + position);
        return cartObjectArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        cartObject1= cartObjectArrayList.get(position);
        convertView= LayoutInflater.from(mContext).inflate(R.layout.cart_rows,null);
        ImageView cartImage=convertView.findViewById(R.id.cartImage);
        TextView pnameCart=convertView.findViewById(R.id.pnameCart);
        TextView quantityCart=convertView.findViewById(R.id.quantityCart);
        TextView priceCart=convertView.findViewById(R.id.priceCart);
        ImageButton addQuantityCart = convertView.findViewById(R.id.addQuantityCart);
        ImageButton minusQuantityCart = convertView.findViewById(R.id.minusQuantityCart);
        ImageButton deleteCard =convertView.findViewById(R.id.deleteCart);
        Button buyCart=convertView.findViewById(R.id.buyCart);


        addQuantityCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cartObject1.setQuantityCart( cartObject1.getQuantityCart()+1);
                notifyDataSetChanged();     }});






        minusQuantityCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(cartObject1.getQuantityCart() > 1){
                    cartObject1.setQuantityCart( cartObject1.getQuantityCart()-1);}
                notifyDataSetChanged();     }});




        deleteCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Confirm delete");
                builder.setMessage("Are you sure you want to delete this product");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cartObjectArrayList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.show();

                }});

        quantityCart.setText(cartObject1.getQuantityCart().toString());
        priceCart.setText(cartObject1.getPriceCart()+"NIS");
        pnameCart.setText(cartObject1.getPnameCart().toString());



        String productImageCart=cartObject1.getCartImage();
        try {
            RequestManager requestManager = Glide.with(mContext);
// Create request builder and load image.
            RequestBuilder requestBuilder = requestManager.load(new URL( productImageCart));
// Show image into target imageview.
            requestBuilder.into(cartImage);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return convertView;
    }

}