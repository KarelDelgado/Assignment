package com.assignment.example.top10posts.Instagram.Services;

import com.assignment.example.top10posts.Instagram.InstagramAPI;
import com.assignment.example.top10posts.Instagram.Model.Media;
import com.assignment.example.top10posts.Instagram.Model.AccessToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by karel on 7/14/2017.
 */

public interface InstagramService {

    @POST(InstagramAPI.ACCESS_TOKEN_PATH)
    @FormUrlEncoded
    Call<AccessToken> getToken(@Field("client_id") String clientId,
                               @Field("client_secret") String clientSecret,
                               @Field("redirect_uri") String redirectUri,
                               @Field("grant_type") String grantType,
                               @Field("code") String code);

    @GET(InstagramAPI.MEDIA_SEARCH_PATH)
    Call<Media> getMedia(@Query("lat") double latitude,
                         @Query("lng") double longitude,
                         @Query("distance") long distance,
                         @Query("access_token") String accessToken);
}
