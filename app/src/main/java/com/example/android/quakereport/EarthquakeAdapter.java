package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.graphics.drawable.GradientDrawable;

import androidx.core.content.ContextCompat;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPARATOR = " of ";
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0,earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);

        }
        Earthquake currentEarthquake = getItem(position);
        TextView magnitudeView = listItemView.findViewById(R.id.magnitude);
        //format magnitude to sho 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());

        magnitudeView.setText(formattedMagnitude);

        //Set the proper background color on the magnitude circle
        //Fetch the background from the TextView, which is a GradientDrawalbe.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        //Get the appropriate background color based on the current earthquake magnitude
        int magnitudecolor = getMagnitudeColor(currentEarthquake.getMagnitude());
        //Set the color of the magnitude color
        magnitudeCircle.setColor(magnitudecolor);

        String originalLocation = currentEarthquake.getLocation();
        String primaryLocation;
        String locationOffset;

        if(originalLocation.contains(LOCATION_SEPARATOR))
        {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }else{
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }
        TextView primaryLocationView = listItemView.findViewById(R.id.location_primary);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        Date dateObject = new Date(currentEarthquake.getTimeInMillisecond());
        TextView dateView = listItemView.findViewById(R.id.date);
        //Format the date string
        String formattedDate = formatDate(dateObject);
        //Display the date of the current earthquake in that textView
        dateView.setText(formattedDate);

        TextView timeView = listItemView.findViewById(R.id.time);
        //Format the time string
        String formattedTime = formatTime(dateObject);
        //Display the time
        timeView.setText(formattedTime);

        return listItemView;
    }
    private int getMagnitudeColor(double magnitude)
    {
        int magnitudecolorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor)
        {
            case 0:
            case 1:
                magnitudecolorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudecolorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudecolorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudecolorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudecolorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudecolorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudecolorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudecolorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudecolorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudecolorResourceId = R.color.magnitude10;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudecolorResourceId);
    }
    private  String formatDate(Date dateObject)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd,yyyy");
        return dateFormat.format(dateObject);
    }
    private  String formatTime(Date dateObject)
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    private String formatMagnitude(double magnitude)
    {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}
