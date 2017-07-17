package com.assignment.example.top10posts.Instagram.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by karel on 7/14/2017.
 */

public class Media {

    @SerializedName("data")
    @Expose
    private List<Post> data = null;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<Post> getData() {
        return data;
    }

    public void setData(List<Post> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        return "Media{" +
                "data=" + data +
                ", meta=" + meta +
                '}';
    }
}
