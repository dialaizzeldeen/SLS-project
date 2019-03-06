package com.example.seamlessshopping;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;


public class productAdapter  extends BaseAdapter {
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
            productsObject productsObj = productsObjectsArrayList.get(position);
            convertView = LayoutInflater.from(mContext).inflate(R.layout.product_rows, null);

          TextView price = (TextView) convertView.findViewById(R.id.price);
          TextView quantity= (TextView) convertView.findViewById(R.id.quantity);
            TextView name = (TextView) convertView.findViewById(R.id.Name);
      ImageView imageUrl= (ImageView) convertView.findViewById(R.id.imageurl);

          //  ListView i = (ListView)convertView.findViewById(R.id.listview);


            price.setText(productsObj.getPrice());
          name.setText(productsObj.getName());
              String imageLink=productsObj.getImageurl();
            Bitmap b = BitmapFactory.decodeResource(convertView.getResources(),R.drawable.ic_launcher_background);
                    imageUrl.setImageBitmap(b);
                    quantity.setText(productsObj.getQuantity().toString());

            //    URI uri = new URI(imageLink);
              //  URL url = uri.toURL();
              ///  productphoto = BitmapFactory.decodeStream(url.openConnection() .getInputStream());
                //imageUrl.setImageBitmap(productphoto);



       /**    btnDelete.setTag(position); //important so we know which item to delete on button click
            btnDelete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int positionToRemove = (int) v.getTag();
                    //get the position of the view to delete stored in the tag
                    contactsInfoArrayList.remove(positionToRemove);
                    notifyDataSetChanged(); //remove the item
                }
            });**/

            return convertView;
        }

    }


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

