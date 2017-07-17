package com.assignment.example.top10posts;

import com.assignment.example.top10posts.Instagram.Model.Post;
import com.assignment.example.top10posts.Utils.CodeError;
import com.assignment.example.top10posts.Utils.CodeParse;
import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class InstagramAuthUnitTest {

    @Test
    public void parse_error_response() throws Exception {
        String response = "{\"error_type\": \"OAuthException\", \"code\": 400, \"error_message\": \"Invalid Client ID\"}";
        String expectedErrorType = "OAuthException";
        int expectedCode = 400;
        String expectedErrorMessage = "Invalid Client ID";
        CodeParse parse = new CodeParse();
        parse.parseError(response);
        CodeError codeError = parse.getError();
        assertEquals(codeError.getErrorType(), expectedErrorType);
        assertEquals(codeError.getCode(), expectedCode);
        assertEquals(codeError.getErrorMessage(), expectedErrorMessage);
    }
}