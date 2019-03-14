package com.example.seamlessshopping;

import android.content.Context;
import android.content.Intent;
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
import android.widget.CheckBox;
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


public class productAdapter extends BaseAdapter {
    ArrayList<productsObject> productsObjectsArrayList;
    productsObject productsObj;

    Context mContext;
    int positionitem;
    //Intent editIntent;
    // ImageButton btnDelete;
    //ImageButton btnCall;
    //ImageButton btnEdit;
    TextView text;

    public productAdapter(Context context, ArrayList<productsObject> productsObjectsArrayList) {
        this.mContext = context;
        this.productsObjectsArrayList = productsObjectsArrayList;

    }


    @Override
    public int getCount() {
        return productsObjectsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        positionitem = position;
        Log.d("hey", "i clicked you" + position);
        return productsObjectsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final productsObject productsObj = productsObjectsArrayList.get(position);
        convertView = LayoutInflater.from(mContext).inflate(R.layout.product_rows, null);
        ImageButton addQuantity = (ImageButton)convertView.findViewById(R.id.addQuantity);
        ImageButton minusQuantity = (ImageButton)convertView.findViewById(R.id.minusQuantity);

        final TextView price = (TextView) convertView.findViewById(R.id.price);
        final TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
        final TextView name = (TextView) convertView.findViewById(R.id.Name);
        final ImageView imageUrls = (ImageView) convertView.findViewById(R.id.imageurl);
        CheckBox checkBox=(CheckBox)convertView.findViewById(R.id.checkboxProduct);

        if(checkBox.isChecked()){
            String type="addtocart";
            addtoCart addtoCart1= new addtoCart(mContext);
            addtoCart1.execute(type,name.toString(),price.toString(),quantity.toString(),imageUrls.toString(),productsObj.getID().toString());
        }




        addQuantity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                productsObj.setQuantity( productsObj.getQuantity()+1);
                notifyDataSetChanged();     }});






        minusQuantity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(productsObj.getQuantity()>1){
                    productsObj.setQuantity( productsObj.getQuantity()-1);}
                notifyDataSetChanged();     }});





        quantity.setText(productsObj.getQuantity().toString());
        price.setText(productsObj.getPrice()+" NIS");
        name.setText(productsObj.getName());

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

