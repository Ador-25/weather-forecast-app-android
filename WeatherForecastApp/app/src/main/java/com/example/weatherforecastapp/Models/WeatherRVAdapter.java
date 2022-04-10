package com.example.weatherforecastapp.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherforecastapp.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherRVAdapter extends RecyclerView.Adapter<WeatherRVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WeatherRV> weatherRVArrayList;

    public WeatherRVAdapter(Context context, ArrayList<WeatherRV> weatherRVArrayList) {
        this.context = context;
        this.weatherRVArrayList = weatherRVArrayList;
    }

    @NonNull
    @Override
    public WeatherRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.weather_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherRVAdapter.ViewHolder holder, int position) {
        WeatherRV weatherRV= weatherRVArrayList.get(position);
        SimpleDateFormat in= new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat out= new SimpleDateFormat("hh:mm aa");
        try {
            Date t=in.parse(weatherRV.getTime()); //check here
            holder.timeTV.setText(out.format(t));
        }
        catch (Exception e){

        }

        holder.temperatureTV.setText(weatherRV.getTemperature()+"°C");
        holder.windTV.setText(weatherRV.getWindSpeed()+"km/h");
        Picasso.get().load("http:".concat(weatherRV.getIcon())).into(holder.conditionIV);
    }

    @Override
    public int getItemCount() {
        return weatherRVArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView windTV,temperatureTV,timeTV;
        private ImageView conditionIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            windTV=itemView.findViewById(R.id.idTVwindSpeed);
            temperatureTV=itemView.findViewById(R.id.idTVTemperature);
            timeTV=itemView.findViewById(R.id.idTVTime);
            conditionIV=itemView.findViewById(R.id.idIVCondition);
        }
    }
}
