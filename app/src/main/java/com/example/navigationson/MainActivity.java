package com.example.navigationson;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import android.view.Menu;
import android.widget.Button;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Event> eventList = new ArrayList<>();
    EventRoomDatabase eventRoomDatabase;
    ExpandableListView expandableListView;
    public static DrawerLayout drawer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        eventRoomDatabase = EventRoomDatabase.getInstance(getApplicationContext());
        expandableListView = (ExpandableListView)findViewById(R.id.simple_expandable_listview);

        SharedPreferences sharedPreferences = this.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE);
        EventRoomDatabase.getInstance(this).eventDao().getAllEvents(sharedPreferences.getString("username",""))
                .observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> all) {
                if (all != null) {
                    eventList.clear();
                    eventList.addAll(all);
                    final ExpandableAdapter adapter = new ExpandableAdapter(MainActivity.this,eventList);
                   // adapter = new ExpandableListAdapter(MainActivity.this, eventList,);
                    final int[] prevExpandPosition = {-1};
                    expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                        @Override
                        public void onGroupExpand(int groupPosition) {
                            if (prevExpandPosition[0] >= 0 && prevExpandPosition[0] != groupPosition) {
                                expandableListView.collapseGroup(prevExpandPosition[0]);
                            }
                            prevExpandPosition[0] = groupPosition;
                        }
                    });


                    expandableListView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }

            }

        });


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        MapFragment mapFragment = new MapFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.mainLayout, mapFragment).commit();



        Button exitButton = (Button)findViewById(R.id.exitButton);

     

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login_Activity.class);
                startActivity(i);
            }
        });



    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

}












