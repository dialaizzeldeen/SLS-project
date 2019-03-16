package com.example.seamlessshopping;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;

import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class paymentHistoryAdapter extends BaseAdapter {
    Context mContext;int positionitem;
    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";
    private String id="0";
ArrayList<paymentHistoryObject>paymentHistoryObjectArrayList;
    public paymentHistoryAdapter(paymentHistory paymentHistory, ArrayList<paymentHistoryObject> paymentHistoryObjectArrayList) {
this.paymentHistoryObjectArrayList=paymentHistoryObjectArrayList;
this.mContext=paymentHistory;

    }

    @Override
    public int getCount() {
        return paymentHistoryObjectArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        positionitem = position;
        Log.d("hey", "i clicked you" + position);
        return paymentHistoryObjectArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            paymentHistoryObject paymentHistoryObjects = paymentHistoryObjectArrayList.get(position);
            convertView = LayoutInflater.from(mContext).inflate(R.layout.paymenthistory_rows, null);
        TextView paymentDate = (TextView)convertView.findViewById(R.id.paymentdate);
        TextView paymentPrice = (TextView)convertView.findViewById(R.id.paymentPrice);

        TextView paymentRecipetnumber = (TextView) convertView.findViewById(R.id.paymentRecipetnumber);
        TextView marketName= (TextView) convertView.findViewById(R.id.marketName);
        Log.d("dhdhhd","gg"+paymentHistoryObjects.getDateOfPurchase());

        paymentDate.setText(paymentHistoryObjects.getDateOfPurchase());
        paymentPrice.setText("$ "+paymentHistoryObjects.getTotalPrice());
        paymentRecipetnumber.setText("Recipet No "+paymentHistoryObjects.getRecieptNumber());

        marketName.setText(paymentHistoryObjects.getMarketname());

            return convertView;
    }
    public void getData(){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(shared_pres,MODE_PRIVATE);
        id=sharedPreferences.getString(iduser,"0");
        Log.d("response  ",id);
    }
}
