package com.assignment.example.top10posts.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.assignment.example.top10posts.Data.Database;
import com.assignment.example.top10posts.Fragments.InstagramFragment;
import com.assignment.example.top10posts.Fragments.PostListFragment;
import com.assignment.example.top10posts.Instagram.InstagramAPI;
import com.assignment.example.top10posts.Instagram.InstagramSession;
import com.assignment.example.top10posts.Instagram.Model.Post;
import com.assignment.example.top10posts.R;
import com.assignment.example.top10posts.Utils.Constants;
import com.assignment.example.top10posts.Utils.Settings;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class PostListActivity extends AppCompatActivity implements InstagramFragment.Callbacks {

    private static final int LOCATION_PERMISSION_CODE = 1;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_list_activity);
        settings = new Settings(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_post_list);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.posts_activity_tittle));
        }

        InstagramSession instagramSession = InstagramSession.getInstance(this);
        if(!instagramSession.isCodeAvailable()) {
            setInstagramLoginScreen();
        }
        else if(!instagramSession.isAccessTokenAvailable()) {
            InstagramAPI.requestAccessToken(this);
        }
        else {
            setPostsListScreen();
        }

        checkForLocation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_action:
                InstagramAPI.requestMedia(this);
                return true;
            case R.id.settings_action:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkForLocation() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            readLocation();
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == LOCATION_PERMISSION_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readLocation();
            }
            else {
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    new AlertDialog.Builder(this)
                            .setTitle("Location Access Permission")
                            .setMessage("The app ask for Instagram post around yor position.")
                            .show();
                }
                else {
                    new AlertDialog.Builder(this)
                            .setTitle("Location Access Permission")
                            .setMessage("You denied location access, so the app could not get Instagram post around your position.")
                            .show();
                }
            }
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void readLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null) {
                    settings.saveLocation(location);
                }
            }
        });
    }

    private void setInstagramLoginScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, InstagramFragment.newInstance())
                .commit();
    }

    private void setPostsListScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, PostListFragment.newInstance())
                .commit();
        InstagramAPI.requestMedia(this);
    }

    @Override
    public void onUserAuthorizeSuccessful() {
        setPostsListScreen();
    }

    @Override
    public void onUserAuthorizeFail() {
        setPostsListScreen();
    }
}
