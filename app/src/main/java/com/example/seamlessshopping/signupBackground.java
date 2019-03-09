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

public class signupBackground extends AsyncTask<Object,Void,String> {

    Context context;
    AlertDialog alertDialog1;
    signupBackground(Context ctx){ context=ctx; }
    String val="";

    @Override

    protected String doInBackground(Object... strings) {
        Object type = (String) strings[0].toString(); // gave you the type that you send which is login
        val=(String) type;
        String signup_url="http://192.168.1.4/signup.php";
        if(type.equals("signup")){
            try {
                String fname= strings[1].toString();
                String lname=strings[2].toString();
                String username=strings[3].toString();
                String email=strings[4].toString();
                String password=strings[5].toString();
                String phone=strings[6].toString();
                URL url =new URL(signup_url);

                HttpURLConnection httpURLConnection1= (HttpURLConnection) url.openConnection();
                httpURLConnection1.setRequestMethod("POST");
                httpURLConnection1.setDoInput(true);
                httpURLConnection1.setDoOutput(true);
                OutputStream outputStream1=httpURLConnection1.getOutputStream();
                BufferedWriter bufferedWriter1=new BufferedWriter(new OutputStreamWriter(outputStream1,"UTF-8"));
                String post_data= URLEncoder.encode("fnameKey","UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")+"&"
                        +URLEncoder.encode("lnameKey","UTF-8")+"="+URLEncoder.encode(lname,"UTF-8")+"&"+
                URLEncoder.encode("usernamekey","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                URLEncoder.encode("emailKey","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                URLEncoder.encode("passwordKey","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                URLEncoder.encode("phoneKey","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8");
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

       /* alertDialog1= new AlertDialog.Builder(context).create();
        alertDialog1.setTitle( " signUp status");*/
        super.onPreExecute();
    }

    @Override
    // to show the dialog on screen
    protected void onPostExecute(String aVoid) {
        /*alertDialog1.setMessage(aVoid);
        alertDialog1.show();*/

        if(aVoid.contentEquals("successfully signUp")){
            Intent i= new Intent(context,loginPage.class);
            context.startActivity(i);
        }
        else {
            Toast.makeText(context, aVoid, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}


