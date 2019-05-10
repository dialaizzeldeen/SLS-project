package com.example.seamlessshopping;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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


public class CartAdapter extends BaseAdapter {
    int totalsum=0;
    public static final String usersum="usersum";

    Button sumbitQ;
    public static final String shared_pres="sharedPres";
    public static final String total="Total";
    private String id="0";

    Context mContext;
    public static final String iduser="iduesr";

    int positionitem;
    private ArrayList<cartObject>cartObjectArrayList;
    cartObject cartObject1;
    String url="http://"+ippage.ip+"//deleteitem_cartPage.php";

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
         cartObject1=cartObjectArrayList.get(position);
        convertView= LayoutInflater.from(mContext).inflate(R.layout.cart_rows,null);
        ImageView cartImage=convertView.findViewById(R.id.cartImage);
        TextView pnameCart=convertView.findViewById(R.id.pnameCart);
        TextView marketnameCart=convertView.findViewById(R.id.marketnameCart);

        final TextView quantityCart=convertView.findViewById(R.id.quantityCart);
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


                           buyCart.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   int total=  cartObject1.getQuantityCart()*
                                           Integer.parseInt(cartObject1.getPriceCart());


                                      Toast.makeText(mContext,"total"+total,Toast.LENGTH_LONG).show();
                                   Toast.makeText(mContext,"totalorders"+cartObject1.getQuantityCart(),Toast.LENGTH_LONG).show();


                                   Intent i= new Intent(mContext,questions.class);
                                   i.putExtra("totalsum",total);
                                   i.putExtra("totalorders",cartObject1.getQuantityCart());

                                   mContext.startActivity(i);







                               }
                           }


);

        deleteCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getData();
                Toast.makeText(mContext, cartObject1.getTransactionId().toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(mContext, id.toString(), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Confirm delete");
                builder.setMessage("Are you sure you want to delete this product");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cartObjectArrayList.remove(position);
                        notifyDataSetChanged();
                        RequestQueue queue = Volley.newRequestQueue(mContext);
                        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // response
                                      Log.d(response ,response);
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
                                //params.put("actionKey","deleteItem");
                                params.put("userId",id.toString());
                                params.put("transactionId",cartObject1.getTransactionId().toString());



                                return params;
                            }
                        };
                        queue.add(postRequest);

                    }
                });
                builder.show();

                }});

        quantityCart.setText(cartObject1.getQuantityCart().toString());
        priceCart.setText(cartObject1.getPriceCart()+"NIS");
        pnameCart.setText(cartObject1.getPnameCart().toString());

marketnameCart.setText(cartObject1.getMarketName());

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
    public void getData(){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(shared_pres,MODE_PRIVATE);
        id=sharedPreferences.getString(iduser,"0");
        Log.d("response  ",id);
    }

}