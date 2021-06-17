package com.example.android.quakereport;

public class Earthquake {
    private Double mMagnitude;
    private String mLocation;
    private String mDate;
    private long mTimeInMillisecond;
    private String mUrl;

    public Earthquake(Double magnitude, String location, long timeInMillisecond,String url)
    {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMillisecond = timeInMillisecond;
        mUrl = url;
    }
    public Double getMagnitude(){
        return mMagnitude;
    }
    public String getLocation(){
        return mLocation;
    }
    public long getTimeInMillisecond(){
        return mTimeInMillisecond;
    }
    public String getUrl(){
        return mUrl;
    }
}
