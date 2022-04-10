package com.example.weatherforecastapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherforecastapp.Models.WeatherRV;
import com.example.weatherforecastapp.Models.WeatherRVAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout homeRL;
    private ProgressBar loadingPB;
    private TextView cityName,temperatureTV,conditionTV;
    private TextInputEditText cityEdit;
    private ImageView backIV,iconIV,searchIV;
    private RecyclerView weatherRV;
    private ArrayList<WeatherRV> weatherRVArrayList;
    private WeatherRVAdapter weatherRVAdapter;
    private LocationManager locationManager;
    private int PERMISSION_CODE=1;
    private String nameOfCity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //make app full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        setContentView(R.layout.activity_main);
        homeRL= findViewById(R.id.idRLHome);
        loadingPB= findViewById(R.id.idPBLoading);
        cityName= findViewById(R.id.idTVCityName);
        temperatureTV= findViewById(R.id.idTVTemperature);
        conditionTV= findViewById(R.id.idTVCondition);
        cityEdit= findViewById(R.id.idEdtCity);
        backIV= findViewById(R.id.idIVBack);
        iconIV= findViewById(R.id.idIVIcon);
        searchIV= findViewById(R.id.idIVSearch);
        weatherRV= findViewById(R.id.idRVWeather);

        //16.50

        weatherRVArrayList= new ArrayList<>();
        weatherRVAdapter =  new WeatherRVAdapter(this,weatherRVArrayList);
        weatherRV.setAdapter(weatherRVAdapter);
        //

        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION )!= PackageManager.PERMISSION_GRANTED ){
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_CODE);
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        nameOfCity= getCityName(location.getLongitude(),location.getLatitude());
        getWeatherInfo(nameOfCity);

        //53.01


    }
    private String getCityName(double lon,double lat){
        String cityName= "NOT FOUND";
        Geocoder gcd= new Geocoder(getBaseContext(), Locale.getDefault());
        try{
            List<Address> addresses = gcd.getFromLocation(lat,lon,10);
            for(Address adr: addresses){
                if(adr!=null){
                    String city= adr.getLocality();
                    if(city!=null && !city.equals("")){
                        cityName= city;
                    }
                }
            }
        }
        catch (Exception e){
            Log.d("TAG","CITY NOT FOUND");
            Toast.makeText(this,"User City NOT FOUND",Toast.LENGTH_SHORT ).show();
        }
        return cityName;

    }
    private void getWeatherInfo(String location){
        String url="https://api.weatherapi.com/v1/current.json?key=3c64997a6a154b138af182025221004&q="+location+"&aqi=no";

    }
}