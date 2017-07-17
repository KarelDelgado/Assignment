package com.assignment.example.top10posts.Instagram.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by karel on 7/14/2017.
 */

public class Comments extends RealmObject{

    @SerializedName("count")
    @Expose
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "count=" + count +
                '}';
    }
}
