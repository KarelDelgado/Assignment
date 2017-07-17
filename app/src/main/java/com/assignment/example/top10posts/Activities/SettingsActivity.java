package com.assignment.example.top10posts.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

import com.assignment.example.top10posts.Instagram.InstagramSession;
import com.assignment.example.top10posts.R;
import com.assignment.example.top10posts.Utils.Settings;

public class SettingsActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getTitle());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        settings = new Settings(this);
        seekBar = (SeekBar) findViewById(R.id.distance_seek_bar);
        seekBar.setProgress(settings.getVisibleDistance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.save_action) {
            settings.saveDistance(seekBar.getProgress());
            navigateUpTo(new Intent(this, PostListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
