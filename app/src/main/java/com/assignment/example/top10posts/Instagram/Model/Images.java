package com.assignment.example.top10posts.Instagram.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by karel on 7/14/2017.
 */

public class Images extends RealmObject{

    @SerializedName("thumbnail")
    @Expose
    private Resolution thumbnail;
    @SerializedName("low_resolution")
    @Expose
    private Resolution lowResolution;
    @SerializedName("standard_resolution")
    @Expose
    private Resolution standardResolution;

    public Resolution getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Resolution thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Resolution getLowResolution() {
        return lowResolution;
    }

    public void setLowResolution(Resolution lowResolution) {
        this.lowResolution = lowResolution;
    }

    public Resolution getStandardResolution() {
        return standardResolution;
    }

    public void setStandardResolution(Resolution standardResolution) {
        this.standardResolution = standardResolution;
    }

    @Override
    public String toString() {
        return "Images{" +
                "thumbnail=" + thumbnail +
                ", lowResolution=" + lowResolution +
                ", standardResolution=" + standardResolution +
                '}';
    }
}
