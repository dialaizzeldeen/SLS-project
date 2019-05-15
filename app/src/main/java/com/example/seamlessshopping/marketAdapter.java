package com.example.seamlessshopping;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class marketAdapter extends BaseAdapter {
    ArrayList<marketObject> marketObjectArrayList;
    marketObject marketObject1;

    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    String url = "http://" + ippage.ip + ":8887/";
    private String id="0";
    View dialogView;
    public String lowvalue , highvalue,peakvalue;
TextView lowtimeview,hightimeview,peaktimeview;
    Context mContext;
    int positionitem;
    public String getday;
    private Calendar calendar;
    private int year, month, day;

    //Intent editIntent;
    // ImageButton btnDelete;
    //ImageButton btnCall;
    //ImageButton btnEdit;
    TextView text;

    private void showDate(int year, int month, int day) {

        String monthName = (String) android.text.format.DateFormat.format("MMMM", month);

        String h= String.valueOf(day+"/"+month+"/"+year);
        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
        Date dt1= null;
        try {
            dt1 = format1.parse(h);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format2=new SimpleDateFormat("EEEE");
        String finalDay=format2.format(dt1);
        getday=finalDay;
    }

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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.market_rows, null);

        dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_input, null);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
        lowtimeview=dialogView.findViewById(R.id.lowtime);
        hightimeview=dialogView.findViewById(R.id.hightime);

        peaktimeview=dialogView.findViewById(R.id.peaktime);

        ImageView imageUrls = (ImageView) convertView.findViewById(R.id.imageurl);
        ImageView daytime =(ImageView)convertView.findViewById(R.id.daytime);

        daytime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,getday,Toast.LENGTH_LONG).show();
                sendDataToServer();
            }
        });

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

    public void sendDataToServer() {
        RequestQueue queue = Volley.newRequestQueue(mContext);  //

        try {

            JSONArray jsonArray = new JSONArray();

            JSONObject js = new JSONObject();
            JSONObject js2 = new JSONObject();


            try {
                js.put("day",getday);

                jsonArray.put(js);

                js2.put("information", jsonArray);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, js2,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray responseArray = response.getJSONArray("Result");
                                Log.i("Response", responseArray + "");
                               // String peakvalue ="peak Hours : "+ responseArray.get(0).toString();

                                //String highvalue = "high Hours : "+responseArray.get(1).toString();
                                //String lowvalue ="low Hours : "+ responseArray.get(2).toString();

 peakvalue ="peak Hours : "+ responseArray.get(0).toString();

                               highvalue = "high Hours : "+responseArray.get(1).toString();
                             lowvalue ="low Hours : "+ responseArray.get(2).toString();
Log.d("ffff",""+lowvalue+highvalue+peakvalue);
                                setDialog(lowvalue,highvalue,peakvalue);

                               // AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                //builder.setTitle("day");
                                //builder.setMessage(peakvalue +"\n"+highvalue+"\n"+lowvalue);

                                //builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                  //  public void onClick(DialogInterface dialog, int id) {
                                  ///      dialog.cancel();
                                 //   }
                               // });
                               // builder.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }




                        }
                    }, new Response.ErrorListener() {
                @Override

                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(mContext, error.getMessage(), Toast.LENGTH_LONG).show();

                }
            });

            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }


    public void setDialog(final String lowtime, final String hightime, final String peaktime) {
        final AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setView(dialogView)
                .setTitle("Best Time ")
                .setNegativeButton("Cancel", null)
                .create();
        dialog.setCancelable(false);


        Log.d("llllllo",""+lowtime+hightime+peaktime);
        lowtimeview.setText(lowtime);
        hightimeview.setText(hightime);
        peaktimeview.setText(peaktime);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(final DialogInterface dialogInterface) {
                final Button noButton = (dialog).getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }

        });
        dialog.show();
    }

}



