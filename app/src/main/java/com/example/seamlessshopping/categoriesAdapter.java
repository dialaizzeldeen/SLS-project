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
    public View getView(int position, View convertView, ViewGroup parent) {
        final categoriesObject productsObj = categoriesObjectsArrayList.get(position);
        convertView = LayoutInflater.from(mContext).inflate(R.layout.categories_rows, null);

        ImageView imageUrls = (ImageView) convertView.findViewById(R.id.imageurl);

        /*imageurlNew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newllyAddedObject1=newllyAddedObjectArrayList.get(position);

                Intent i= new Intent(v.getContext(),productMain.class);
                String idmarket =newllyAddedObject1.getIdmarket();
                i.putExtra("idmarket",idmarket);
                v.getContext().startActivity(i);
                Log.d("Response",idmarket);
            }});*/







        String productImage = productsObj.getImageurl();

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
/**
 * btnDelete.setTag(position); //important so we know which item to delete on button click
 * btnDelete.setOnClickListener(new View.OnClickListener() {
 * public void onClick(View v) {
 * int positionToRemove = (int) v.getTag();
 * //get the position of the view to delete stored in the tag
 * contactsInfoArrayList.remove(positionToRemove);
 * notifyDataSetChanged(); //remove the item
 * }
 * });
 * editIntent = new Intent(this,editContact.class);
 * editIntent.putExtra("editMail",editMail);
 * editIntent.putExtra("editName",editName);
 * editIntent.putExtra("editDate",editDate);
 * editIntent.putExtra("editPhone",editPhone);
 * startActivityForResult(editIntent, 3);btnDelete.setOnClickListener(new View.OnClickListener() {
 * public void onClick(View v) {
 * <p>
 * contactsInfoArrayList.remove(positionitem);
 * <p>
 * adapter.notifyDataSetChanged();
 * <p>
 * <p>
 * }
 * }}
 * btnDelete.setOnClickListener(new View.OnClickListener() {
 * public void onClick(View v) {
 * <p>
 * contactsInfoArrayList.remove(positionitem);
 * <p>
 * adapter.notifyDataSetChanged();
 * <p>
 * <p>
 * }
 * }}
 * btnDelete.setOnClickListener(new View.OnClickListener() {
 * public void onClick(View v) {
 * <p>
 * contactsInfoArrayList.remove(positionitem);
 * <p>
 * adapter.notifyDataSetChanged();
 * <p>
 * <p>
 * }
 * }}
 * <p>
 * editIntent = new Intent(this,editContact.class);
 * editIntent.putExtra("editMail",editMail);
 * editIntent.putExtra("editName",editName);
 * editIntent.putExtra("editDate",editDate);
 * editIntent.putExtra("editPhone",editPhone);
 * startActivityForResult(editIntent, 3);btnDelete.setOnClickListener(new View.OnClickListener() {
 * public void onClick(View v) {
 * <p>
 * contactsInfoArrayList.remove(positionitem);
 * <p>
 * adapter.notifyDataSetChanged();
 * <p>
 * <p>
 * }
 * }}
 * btnDelete.setOnClickListener(new View.OnClickListener() {
 * public void onClick(View v) {
 * <p>
 * contactsInfoArrayList.remove(positionitem);
 * <p>
 * adapter.notifyDataSetChanged();
 * <p>
 * <p>
 * }
 * }}
 * btnDelete.setOnClickListener(new View.OnClickListener() {
 * public void onClick(View v) {
 * <p>
 * contactsInfoArrayList.remove(positionitem);
 * <p>
 * adapter.notifyDataSetChanged();
 * <p>
 * <p>
 * }
 * }}
 **/


//  Button   btnDelete =(Button)findViewById(R.id.deletecontact);

/**
 * editIntent = new Intent(this,editContact.class);
 * editIntent.putExtra("editMail",editMail);
 * editIntent.putExtra("editName",editName);
 * editIntent.putExtra("editDate",editDate);
 * editIntent.putExtra("editPhone",editPhone);
 * startActivityForResult(editIntent, 3);btnDelete.setOnClickListener(new View.OnClickListener() {
 * public void onClick(View v) {
 * <p>
 * contactsInfoArrayList.remove(positionitem);
 * <p>
 * adapter.notifyDataSetChanged();
 * <p>
 * <p>
 * }
 * }}
 * btnDelete.setOnClickListener(new View.OnClickListener() {
 * public void onClick(View v) {
 * <p>
 * contactsInfoArrayList.remove(positionitem);
 * <p>
 * adapter.notifyDataSetChanged();
 * <p>
 * <p>
 * }
 * }}
 **/
/**btnDelete.setOnClickListener(new View.OnClickListener() {
 public void onClick(View v) {

 contactsInfoArrayList.remove(positionitem);

 adapter.notifyDataSetChanged();


 }
 }}**/

