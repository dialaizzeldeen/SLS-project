package com.example.seamlessshopping;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class addtoCart extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog1;
    addtoCart(Context ctx){
        context=ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        String type =strings[0]; // gave you the type that you send which is login
        String login_url="http://192.168.1.9/"+"addToCart.php"; //to conect with local host
        if(type.equals("addtocart")){
            try {
                String name =strings[1]; //get the username value from the edittext
                String price =strings[2]; //get the password value from the edittext
                String quntity=strings[3];
                String imgUrl=strings[4];
                String productid=strings[5];

                URL url =new URL(login_url);
                HttpURLConnection httpURLConnection1= (HttpURLConnection) url.openConnection();
                httpURLConnection1.setRequestMethod("POST");
                httpURLConnection1.setDoInput(true);
                httpURLConnection1.setDoOutput(true);
                OutputStream outputStream1=httpURLConnection1.getOutputStream();
                BufferedWriter bufferedWriter1=new BufferedWriter(new OutputStreamWriter(outputStream1,"UTF-8"));
                String post_data= URLEncoder.encode("namekey","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("pricekey","UTF-8")+"="+URLEncoder.encode(price,"UTF-8")+"&"+
                URLEncoder.encode("quntitykey","UTF-8")+"="+URLEncoder.encode(quntity,"UTF-8")+"&"+
                        URLEncoder.encode("imagekey","UTF-8")+"="+URLEncoder.encode(imgUrl,"UTF-8")+"&"+
                        URLEncoder.encode("idkey","UTF-8")+"="+URLEncoder.encode(productid,"UTF-8");
                bufferedWriter1.write(post_data);
                bufferedWriter1.flush();
                bufferedWriter1.close();
                outputStream1.close();
                // to get the response
                InputStream inputStream1=httpURLConnection1.getInputStream();
                BufferedReader bufferedReader1= new BufferedReader(new InputStreamReader(inputStream1,"iso-8859-1"));
                String result="";
                String line="";
                while ((line=bufferedReader1.readLine())!=null){
                    result=result+line;
                }
                bufferedReader1.close();
                inputStream1.close();
                httpURLConnection1.disconnect();
                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;

}
    @Override
    // after finish the job we use this method to show the result
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    // to show the dialog on screen
    protected void onPostExecute(String aVoid) {
        //alertDialog1.setMessage(aVoid);
        if(aVoid.contentEquals("successfully added"))
        {
            Toast.makeText(context, aVoid, Toast.LENGTH_SHORT).show();

        }
        else
        {

            Toast.makeText(context, aVoid, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
