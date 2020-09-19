package com.example.taxmann;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NewsRoomFragment.onFragmentBtnSelected {
    private DrawerLayout drawer;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private Button logoutbtn;

    private FirebaseAuth firebaseAuth;

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logoutTopMenu:{
                Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        logoutbtn = (Button)findViewById(R.id.logoutTopMenu);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });


        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);   //allow to slide nav. drawer

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){ //takes care of rotation problems
            //load default fragment
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, new NewsRoomFragment());
            fragmentTransaction.commit();
            navigationView.setCheckedItem(R.id.nav_newsroom);
        }
    }

//    public void openLogoutActivity(){
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
//    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        if(item.getItemId() == R.id.nav_newsroom){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new NewsRoomFragment());
            fragmentTransaction.commit();
        }if(item.getItemId() == R.id.nav_chat){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new ChatFragment());
            fragmentTransaction.commit();
        }if(item.getItemId() == R.id.nav_profile){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new ProfileFragment());
            fragmentTransaction.commit();
        }
        if(item.getItemId() == R.id.nav_logout){
            Toast.makeText(this, "Logout button clicked.", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.nav_share){
            Toast.makeText(this, "Share button clicked.", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onBtnSelected() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new ChatFragment());
        fragmentTransaction.commit();
    }
}
