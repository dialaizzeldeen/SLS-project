package com.example.seamlessshopping;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class questions extends AppCompatActivity implements View.OnClickListener,BottomNavigationView.OnNavigationItemSelectedListener , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
protected LocationManager locationManager;
private Button btnRequestDirection;
private GoogleMap googleMap;
private String serverKey = "AIzaSyA6yPhzcOM3ho3F39trzG-1bXFp1t29R6U";
public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
private Location lastLocation;
private LocationRequest locationRequest;

        /**
         * permissions request code
         Double myLongitude = loc.getLongitude();
         Double myLatitude ,,myLongitude;
         */
        Double myLatitude ,myLongitude;
    EditText  timeQ;
    TextView locQ;
    TextView dateQ;
    Button sumbitQ;
    String timeval1,timeval2;
    public static final String shared_pres = "sharedPres";
    public static final String iduser = "iduesr";
    private String id = "0";
    String url = "http://" + ippage.ip + ":8978/";
    BottomNavigationView navigation;
    private int year, month, day;
    private DatePicker datePicker;
    private Calendar calendar;


    //Double myLatitude ,myLongitude;


    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;

    /**
     * Permissions that need to be explicitly requested from end user.
     */

    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CALL_PHONE
    };


    private LatLng origin ;//32.22111 35.25444
    private LatLng destination;// = new LatLng(32.22111 , 35.25444);////32.22111 35.25444

    private String Latitude,Longitude;
    double dLatitude,dLongitude;
    private GoogleApiClient mGoogleApiClient;
    private GoogleApiClient getGoogleApiClient(){

        return new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        locQ = findViewById(R.id.LocId);
        timeQ = findViewById(R.id.timeID);
        dateQ = findViewById(R.id.dateID);
        sumbitQ = findViewById(R.id.submitQuestion);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        timeQ.setOnClickListener(this);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);


locQ.setText(Coordinates.AddressCoordinates);



        locQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_LONG).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkPermissions();
                }
                if (mGoogleApiClient != null){

                    mGoogleApiClient.connect();

                } else {
                    mGoogleApiClient = getGoogleApiClient();
                    mGoogleApiClient.connect();

                }
            }
        });






        sumbitQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(questions.this, bankInfo.class);
                int totalsum = getIntent().getIntExtra("totalsum", 0);
                Toast.makeText(getApplicationContext(), "eee" + totalsum, Toast.LENGTH_LONG).show();
                i.putExtra("totalsum", totalsum);
                startActivity(i);
                sendDataToServer();
            }
        });
    }

    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {

        String monthName = (String) android.text.format.DateFormat.format("MMMM", month);
        dateQ.setText(new StringBuilder().append(day)
                .append(monthName).append(year));
    }


    public void sendDataToServer() {
        RequestQueue queue = Volley.newRequestQueue(this);  //

        try {

            JSONArray jsonArray = new JSONArray();

            JSONObject js = new JSONObject();
            JSONObject js2 = new JSONObject();
            String day          = (String) DateFormat.format("dd", Long.parseLong(dateQ.getText().toString())); // 20
Toast.makeText(getApplicationContext(),"daayyyyyyy"+day,Toast.LENGTH_LONG).show();

            try {
                js.put("locQ", Coordinates.distance);
                js.put("timeval1", timeval1);//timeQ.getText().toString()
                js.put("dateQ",day);
                js.put("timeval2",timeval2);
                jsonArray.put(js);

                js2.put("information", jsonArray);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, js2,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(questions.this, response.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }, new Response.ErrorListener() {
                @Override

                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(questions.this, error.getMessage(), Toast.LENGTH_LONG).show();

                }
            });

            queue.add(jsonObjectRequest);
        } catch (Exception e) {
            Toast.makeText(questions.this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:

                Intent home = new Intent(questions.this, NewllyAdded.class);
                startActivity(home);
                break;

            case R.id.navigation_Categories:

                Intent categorie = new Intent(questions.this, Categories_Activity.class);
                startActivity(categorie);


                break;
            case R.id.navigation_cart:

                Intent Cart = new Intent(questions.this, cart.class);
                startActivity(Cart);

                break;
            case R.id.navigation_profile:

                Intent profile = new Intent(questions.this, profilecategory.class);
                startActivity(profile);
                break;
            case R.id.navigation_search:

                Intent search = new Intent(questions.this, searching.class);
                startActivity(search);

                break;
        }
        return false;
    }


    public void onBackPressed() {
        int seletedItemId = navigation.getSelectedItemId();
        if (0 == seletedItemId) {
            Intent home = new Intent(questions.this, NewllyAdded.class);
            startActivity(home);
        } else if (2 == seletedItemId) {

            Intent categorie = new Intent(questions.this, Categories_Activity.class);
            startActivity(categorie);
        } else if (3 == seletedItemId) {
            Intent profile = new Intent(questions.this, profilecategory.class);
            startActivity(profile);
        } else if (1 == seletedItemId) {
            Intent Cart = new Intent(questions.this, cart.class);
            startActivity(Cart);


        } else {
            super.onBackPressed();
        }
    }

    public void getData() {
        SharedPreferences sharedPreferences = getSharedPreferences(shared_pres, MODE_PRIVATE);
        id = sharedPreferences.getString(iduser, "0");
        Log.d("response  ", id);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void checkPermissions() {
        final List<String> missingPermissions = new ArrayList<String>();
// check all required dynamic permissions
        for (final String permission : REQUIRED_SDK_PERMISSIONS) {
            final int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
        if (!missingPermissions.isEmpty()) {
// request all missing permissions
            final String[] permissions = missingPermissions
                    .toArray(new String[missingPermissions.size()]);
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
            final int[] grantResults = new int[REQUIRED_SDK_PERMISSIONS.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_ASK_PERMISSIONS, REQUIRED_SDK_PERMISSIONS,
                    grantResults);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
// exit the app if one permission is not granted
                        Toast.makeText(this, "Required permission '" + permissions[index]
                                + "' not granted, exiting", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                }

                //startService(new Intent(this,LocationService.class));

// all permissions were granted
                break;
        }
    }




    @Override
    public void onConnected(@Nullable Bundle bundle) {

        final Context context = getApplicationContext();
        LocationManager lm = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER) == false) {
            Toast.makeText(this, "Please make sure that Gps is open", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "onConnected", Toast.LENGTH_SHORT).show();
            try {
                Toast.makeText(this, "onConnected"+"", Toast.LENGTH_SHORT).show();

                Toast.makeText(this, "onConnected", Toast.LENGTH_SHORT).show();
                double lat, lng;
                locationRequest = new LocationRequest();
                locationRequest.setInterval(1000);
                locationRequest.setFastestInterval(1000);

                locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                // Location loc = GPS.getLastLocation(this.getApplicationContext());
                Location LastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                //  Location  LastLocation = LocationServices.getFusedLocationProviderClient(this);

                FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

                mFusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations, this can be null.
                                if (location != null) {
                                    Toast.makeText(getApplicationContext(),location+"" , Toast.LENGTH_SHORT).show();
                                    Log.d("myLatitude", "d" + location.getLatitude() + location.getLongitude());
                                    Coordinates.FirstlatCoordinates=location.getLatitude();
                                    Coordinates.FirstlongCoordinates=location.getLongitude();
                                    Log.d("dddddd",""+Coordinates.FirstAddressCoordinates);
                                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                                    List<Address> addresses;

                                    try {
                                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                                        String address = addresses.get(0).getAddressLine(0);

                                        String city = addresses.get(0).getLocality();
                                        String add=addresses.get(0)+"";
                                        // Log.d("city", "d" + city +"addresses" +add);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }


                                    Intent myIntent = new Intent(getApplicationContext(), MapActivity.class);
                                    getApplicationContext().startActivity(myIntent);
                                    // LocationAddress locationAddress = new LocationAddress();

                                    //Toast.makeText(getAppl  LocationAddress locationAddress = new LocationAddress();
                                    //                    locationAddress.getAddressFromLocation(latitude, longitude,
                                    //                            getApplicationContext(), new GeocoderHandler());icationContext(),location.+"" , Toast.LENGTH_SHORT).show();

                                    // Logic to handle location object
                                }
                            }
                        });


            } catch (Exception e) {
                Log.e("d", e.toString());
            }
        }

    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View v) {

        showPopupWindow(v);


    }
    void showPopupWindow(View view) {
        PopupMenu popup = new PopupMenu(questions.this, view);
        try {
            Field[] fields = popup.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popup);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        popup.getMenuInflater().inflate(R.menu.timepopup, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.range1){
                    timeQ.setText(item.getTitle());
                    timeval1="9";
                    timeval2="12";
                }
                else if(item.getItemId()==R.id.range2){ timeQ.setText(item.getTitle());
                    timeval1="10";
                    timeval2="13";
                }
                else if (item.getItemId()==   R.id.range3){
                    timeQ.setText(item.getTitle());
                    timeval1="11";
                    timeval2="14";
                }
                else if(item.getItemId() == R.id.range4) {
                    timeQ.setText(item.getTitle());
                    timeval1="15";
                    timeval2="18";
                }
                else if(item.getItemId()==R.id.range5){
                    timeQ.setText(item.getTitle());
                    timeval1="16";
                    timeval2="19";
                    ;}
                else if(item.getItemId()==R.id.range6){
                    timeQ.setText(item.getTitle());
                    timeval1="18";
                    timeval2="21";
                    ;}
                else if(item.getItemId()==R.id.range7){
                    timeQ.setText(item.getTitle());
                    timeval1="21";
                    timeval2="23";
                    ;}
                return true;
            }

        });
        popup.show();
    }
}

