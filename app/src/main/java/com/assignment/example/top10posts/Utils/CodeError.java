package com.assignment.example.top10posts.Utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by karel on 7/16/2017.
 */

public class CodeError {

    @SerializedName("error_type")
    @Expose
    private String errorType;
    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("error_message")
    @Expose
    private String errorMessage;

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
