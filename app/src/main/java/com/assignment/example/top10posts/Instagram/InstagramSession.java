package com.assignment.example.top10posts.Instagram;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.content.SharedPreferencesCompat;

import com.assignment.example.top10posts.Instagram.Model.AccessToken;
import com.assignment.example.top10posts.Instagram.Model.User;

/**
 * Created by karel on 7/14/2017.
 */

public class InstagramSession {

    private static InstagramSession instance = null;

    private SharedPreferences sharedPreferences;

    private static final String INSTAGRAM_PREFERENCES = "instagram_preferences";
    private static final String API_ACCESS_TOKEN = "access_token";
    private static final String API_ID = "id";
    private static final String API_USERNAME = "username";
    private static final String API_FULL_NAME = "name";
    private static final String API_PROFILE_PICTURE = "profile_picture";
    private static final String API_CODE = "code";
    private static final String CODE_AVAILABLE = "code_available";
    private static final String AT_AVAILABLE = "access_token_available";

    private AccessToken accessToken;
    private String code;
    private boolean codeAvailable;
    private boolean accessTokenAvailable;

    public static InstagramSession getInstance(Context context) {
        if(instance == null) {
            instance = new InstagramSession(context);
        }
        return instance;
    }

    private InstagramSession(Context context) {
        sharedPreferences = context.getSharedPreferences(INSTAGRAM_PREFERENCES, Context.MODE_PRIVATE);
        code = sharedPreferences.getString(API_CODE, "");
        codeAvailable = sharedPreferences.getBoolean(CODE_AVAILABLE, false);
        accessTokenAvailable = sharedPreferences.getBoolean(AT_AVAILABLE, false);
        readAccessToken();
    }

    private void readAccessToken() {
        accessToken = new AccessToken();
        accessToken.setAccessToken(sharedPreferences.getString(API_ACCESS_TOKEN, ""));
        User user = new User();
        user.setId(sharedPreferences.getString(API_ID, ""));
        user.setUsername(sharedPreferences.getString(API_USERNAME, ""));
        user.setFullName(sharedPreferences.getString(API_FULL_NAME, ""));
        user.setProfilePicture(sharedPreferences.getString(API_PROFILE_PICTURE, ""));
        accessToken.setUser(user);
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
        accessTokenAvailable = true;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(API_ACCESS_TOKEN, accessToken.getAccessToken());
        editor.putString(API_ID, accessToken.getUser().getId());
        editor.putString(API_USERNAME, accessToken.getUser().getUsername());
        editor.putString(API_FULL_NAME, accessToken.getUser().getFullName());
        editor.putString(API_PROFILE_PICTURE, accessToken.getUser().getProfilePicture());
        editor.putBoolean(AT_AVAILABLE, accessTokenAvailable);
        editor.apply();
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        codeAvailable = true;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(API_CODE, code);
        editor.putBoolean(CODE_AVAILABLE, codeAvailable);
        editor.apply();
    }

    public void clearCode() {
        this.code = "";
        codeAvailable = false;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(API_CODE, code);
        editor.putBoolean(CODE_AVAILABLE, codeAvailable);
        editor.apply();
    }

    public void clearAccessToken() {
        this.accessToken.setAccessToken("");
        accessTokenAvailable = false;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(API_ACCESS_TOKEN, "");
        editor.putBoolean(CODE_AVAILABLE, accessTokenAvailable);
        editor.apply();
    }

    public boolean isCodeAvailable() {
        return codeAvailable;
    }

    public boolean isAccessTokenAvailable() {
        return accessTokenAvailable;
    }
}
