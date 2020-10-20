package com.example.pa_pe.blackhole.dashboard;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;

import com.example.pa_pe.blackhole.R;
import com.example.pa_pe.blackhole.menu.MenuActivity;
import com.example.pa_pe.blackhole.users.FriendsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.io.File;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

/**
 * The type Dash board activity.
 */
public class DashBoardActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        getSupportActionBar().setTitle("Home");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(DashBoardActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView)  findViewById(R.id.navigation_view);
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@androidx.annotation.NonNull MenuItem item)
            {
                UserMenuSelector(item);
                return false;
            }
        });


        //Bottom navigation
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        // Home fragment transaction (default, on star)
        File file = new File(getApplicationContext().getFilesDir(), "checkpoint.dat");
        if(!file.exists()){
            ChatsFragment fragment1 = new ChatsFragment();
            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
            ft1.replace(R.id.content, fragment1, "chat");
            ft1.commit();
            getSupportActionBar().setTitle("Chat");
        }else if(file.exists()){
            HomeFragment fragment1 = new HomeFragment();
            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
            ft1.replace(R.id.content, fragment1, "home");
            ft1.commit();
            getSupportActionBar().setTitle("Home");
        }
    }

    private void SendsUserToMenuActivity()
    {
        Intent addMenuIntent = new Intent(DashBoardActivity.this, MenuActivity.class);
        startActivity(addMenuIntent);
    }

    private void SendsUserToFriendsActivity()
    {
        Intent addMenuIntent = new Intent(DashBoardActivity.this, FriendsActivity.class);
        startActivity(addMenuIntent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void UserMenuSelector(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.nav_friends:
                SendsUserToFriendsActivity();
                break;

            case R.id.nav_logout:
                SendsUserToMenuActivity();
                break;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    //Handle item clicks
                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            // Home fragment transaction
                            getSupportActionBar().setTitle("Home");
                            HomeFragment fragment1 = new HomeFragment();
                            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                            ft1.replace(R.id.content, fragment1, "home");
                            ft1.commit();
                            return true;
                        case R.id.nav_profile:
                            // Profile fragment transaction
                            getSupportActionBar().setTitle("Profile");
                            ProfileFragment fragment2 = new ProfileFragment();
                            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                            ft2.replace(R.id.content, fragment2, "profile");
                            ft2.commit();
                            return true;
                        case R.id.nav_chat:
                            // Users fragment transaction
                            getSupportActionBar().setTitle("Chat");
                            ChatsFragment fragment3 = new ChatsFragment();
                            FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                            ft3.replace(R.id.content, fragment3, "chat");
                            ft3.commit();
                            return true;
                    }

                    return false;
                }
            };

    @Override
    public void onBackPressed(){

    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onStop(){
        super.onStop();
    }
}
