package com.assignment.example.top10posts.Utils;

import android.net.Uri;

import com.google.gson.Gson;

import java.io.StringReader;
import java.io.Reader;
import java.util.Set;

/**
 * Created by karel on 7/14/2017.
 */

public class CodeParse {

    private String code;
    private CodeError codeError;
    private boolean successful = false;

    public CodeParse() {
    }

    public void parseCode(String url) {
        Uri uri = Uri.parse(url);
        Set<String> parameters = uri.getQueryParameterNames();
        for (String parameter : parameters) {
            if(parameter.equals("code")) {
                code = uri.getQueryParameter(parameter);
                successful = true;
            }
        }
    }

    public void parseError(String error) {
        Gson gson = new Gson();
        Reader reader = new StringReader(error);
        codeError = gson.fromJson(reader, CodeError.class);
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getCode() {
        return code;
    }

    public CodeError getError() {
        return codeError;
    }
}
