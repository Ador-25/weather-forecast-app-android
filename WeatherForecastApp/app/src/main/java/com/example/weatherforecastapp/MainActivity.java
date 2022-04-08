package com.example.weatherforecastapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout homeRL;
    private ProgressBar loadingPB;
    private TextView cityName,temperatureTV,conditionTV;
    private TextInputEditText cityEdit;
    private ImageView backIV,iconIV,searchIV;
    private RecyclerView weatherRV;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeRL= findViewById(R.id.idRLHome);
        loadingPB= findViewById(R.id.idPBLoading);
        cityName= findViewById(R.id.idTVCityName);
        temperatureTV= findViewById(R.id.idTVTemperature);
        conditionTV= findViewById(R.id.idTVCondition);
        //16.50

    }
}