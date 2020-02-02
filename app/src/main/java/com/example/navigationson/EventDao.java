package com.example.navigationson;


import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {

   @Query("SELECT * FROM event WHERE username = :username")
    LiveData<List<Event>> getAllEvents(String username);

    //@Query("SELECT * FROM event WHERE username =:user")
    //LiveData<List<Event>> getAllEvents(String user);



    @Query("SELECT * FROM register WHERE username=:username and password= :password")
    LoginEvent isTrue(String username,String password);

    @Query("UPDATE event SET event = :event ,detail= :detail,type= :type  WHERE id LIKE :id ")
    int updateItem(long id, String event, String detail, String type);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Event event);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllOrders(List <Event> order);


 @Update
 void update(Event event);

   @Insert
    void insertRegister(LoginEvent loginEvents);


//
//    @Query("UPDATE event SET locationlng = :lng, locationlat = :lat WHERE id LIKE :id")
//    int updateLocation (int id, long lng, long lat);

}
