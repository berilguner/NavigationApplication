package com.example.navigationson;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

class Location {


    @ColumnInfo(name= "lng")
    private double lng;
    @ColumnInfo(name= "lat")
    private double lat;

    @Ignore
    public Location(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public Location(){

    }


    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }



}