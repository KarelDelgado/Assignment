package com.assignment.example.top10posts.Instagram.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by karel on 7/14/2017.
 */

public class Meta {

    @SerializedName("code")
    @Expose
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "code=" + code +
                '}';
    }
}
