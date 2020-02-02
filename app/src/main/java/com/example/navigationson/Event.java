package com.example.navigationson;

import android.text.Editable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "event")
public class Event implements Serializable{

   // private String event;
    //private String detail;
    //private int type;
    //private String username;

    public Event(String event, String detail, int type,double lng, double lat, String username) {
        this.event = event;
        this.detail = detail;
        this.type = type;
        this.location = new Location(lng, lat);
        this.username = username;

    }


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "username")
    private String username;


    @ColumnInfo(name="event")
    private String event;
    @ColumnInfo(name="detail")
    private String detail;
    @ColumnInfo(name="type")
    private int type;



    @Embedded(prefix = "location")
    Location location;

    public Event(){

    }

    public Event(double longitude, double latitude){

    }


    public Event(int id, double lng, double lat) {
        this.id = id;
        this.location = new Location(lng, lat);

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }


    public void setLocation(Location location) {
        this.location = location;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public enum EventType {
        CONCERT("Concert"), SPORT("Sport"), MEETING("Meeting"), OTHER("Other");

        private String type;

        EventType(String type) {
            this.type = type;
        }

        public static String getType(int type) {
            for (EventType t : EventType.values()) {
                if (t.ordinal() == type)
                    return t.type;
            }
            return "";
        }
    }
}
