package com.example.navigationson;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Event.class, LoginEvent.class}, version=6, exportSchema = false)
public abstract class EventRoomDatabase extends RoomDatabase {

    private static EventRoomDatabase instance;
    public abstract EventDao eventDao();

    public static synchronized EventRoomDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EventRoomDatabase.class, "event")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }

        return instance;

    }

   public static void destroyInstance(){
        instance = null;
   }

}
