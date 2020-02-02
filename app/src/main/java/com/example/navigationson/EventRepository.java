package com.example.navigationson;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

public class EventRepository {

    private static EventRepository instance;
    private EventDao eventDao;

    public EventRepository(Context context) {

        EventRoomDatabase database = EventRoomDatabase.getInstance(context);
        eventDao = database.eventDao();
    }

    public static EventRepository getInstance(Context context) {
        if (instance == null)
            instance = new EventRepository(context);
        return instance;
    }

    public void insert(Event event) {
        eventDao.insert(event);
    }

    public void update(Event event) {
        eventDao.update(event);


    }

    public void delete(Event event) {

    }

    public LiveData<List<Event>> getAllEvents(String username) {
        return eventDao.getAllEvents(username);
    }



}








