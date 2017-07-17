package com.assignment.example.top10posts.Instagram;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.assignment.example.top10posts.Data.Database;
import com.assignment.example.top10posts.Instagram.Model.AccessToken;
import com.assignment.example.top10posts.Instagram.Model.Media;
import com.assignment.example.top10posts.Instagram.Services.InstagramService;
import com.assignment.example.top10posts.Instagram.Services.RetrofitClient;
import com.assignment.example.top10posts.R;
import com.assignment.example.top10posts.Utils.Constants;
import com.assignment.example.top10posts.Utils.Messages;
import com.assignment.example.top10posts.Utils.Settings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by karel on 7/13/2017.
 */

public class InstagramAPI {

    public static final String BASE_URL  = "https://api.instagram.com/";
    public static final String AUTH_PATH = "oauth/authorize/";
    public static final String MEDIA_SEARCH_PATH = "v1/media/search";
    public static final String ACCESS_TOKEN_PATH  = "oauth/access_token";
    public static final String CLIENT_ID_PARAM  = "?client_id=";
    public static final String REDIRECT_URI_PARAM  = "&redirect_uri=";
    public static final String RESPONSE_TYPE_PARAM  = "&response_type=";
    public static final String SCOPE_PARAM  = "&scope=";

    public static void requestAccessToken(final Context context) {
        final InstagramSession instagramSession = InstagramSession.getInstance(context);
        InstagramService instagramService = RetrofitClient.getClient(BASE_URL)
                                            .getRetrofit()
                                            .create(InstagramService.class);
        instagramService.getToken(Constants.CLIENT_ID,
                                    Constants.CLIENT_SECRET,
                                    Constants.REDIRECT_URI,
                                    Constants.GRANT_TYPE,
                                    instagramSession.getCode()).enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(@NonNull Call<AccessToken> call, @NonNull Response<AccessToken> response) {
                if(response.isSuccessful() && response.body() != null) {
                    instagramSession.setAccessToken(response.body());
                    requestMedia(context);
                }
                else {
                    Messages.showToastMessage(context.getString(R.string.error_getting_access_token), context);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AccessToken> call, @NonNull Throwable t) {
                Messages.showToastMessage(context.getString(R.string.error_connecting), context);
            }
        });
    }

    public static void requestMedia(final Context context) {
        final InstagramSession instagramSession = InstagramSession.getInstance(context);
        InstagramService instagramService = RetrofitClient.getClient(BASE_URL)
                                            .getRetrofit()
                                            .create(InstagramService.class);
        Settings settings = new Settings(context);
        final Database database = new Database(context);
        if(settings.isLocationAvailable()) {
            instagramService.getMedia(
                    settings.getLocation().latitude,
                    settings.getLocation().longitude,
                    settings.getDistance(),
                    instagramSession.getAccessToken().getAccessToken())
                .enqueue(new Callback<Media>() {
                    @Override
                    public void onResponse(@NonNull Call<Media> call, @NonNull Response<Media> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            database.saveInstagramMedia(response.body());
                        }
                        else {
                            Messages.showToastMessage(context.getString(R.string.error_getting_posts), context);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Media> call, @NonNull Throwable t) {
                        Messages.showToastMessage(context.getString(R.string.error_connecting), context);
                    }
                });
        }
        else {
            Messages.showToastMessage(context.getString(R.string.error_no_post_because_no_location), context);
        }

    }

    public static String getAuthorizeUrl() {
        return BASE_URL + AUTH_PATH + CLIENT_ID_PARAM + Constants.CLIENT_ID + REDIRECT_URI_PARAM +
                Constants.REDIRECT_URI + RESPONSE_TYPE_PARAM + Constants.RESPONSE_TYPE
                + SCOPE_PARAM + Constants.PUBLIC_CONTENT_SCOPE;
    }
}
