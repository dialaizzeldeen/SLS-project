package com.example.seamlessshopping;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class loginBackground extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog1;
    loginBackground(Context ctx){
        context=ctx;
    }
    public static final String shared_pres="sharedPres";
    public static final String iduser="iduesr";


    @Override
    protected String doInBackground(String... voids) {
        String type =voids[0]; // gave you the type that you send which is login
        String login_url="http://"+ippage.ip+"/loginPage.php"; //to conect with local host
        if(type.equals("login")){
            try {
                String username =voids[1]; //get the username value from the edittext
                String password =voids[2]; //get the password value from the edittext

                URL url =new URL(login_url);
                HttpURLConnection httpURLConnection1= (HttpURLConnection) url.openConnection();
                httpURLConnection1.setRequestMethod("POST");
                httpURLConnection1.setDoInput(true);
                httpURLConnection1.setDoOutput(true);
                OutputStream outputStream1=httpURLConnection1.getOutputStream();
                BufferedWriter bufferedWriter1=new BufferedWriter(new OutputStreamWriter(outputStream1,"UTF-8"));
                String post_data= URLEncoder.encode("usernamekey","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("passwordkey","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
        if(aVoid.contentEquals("true"))
        {
            Toast.makeText(context, "username or password not correct try again", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Intent i =new Intent(context,NewllyAdded.class);
            String USERIDD=aVoid.toString();
            Savedata(USERIDD);
            context.startActivity(i);




        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
    }
    public void Savedata(String s){
        SharedPreferences sharedPreferences=context.getApplicationContext().getSharedPreferences(shared_pres,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(iduser,s.toString());
        editor.apply();
    }
}
