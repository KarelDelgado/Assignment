package com.assignment.example.top10posts.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.assignment.example.top10posts.Utils.CodeParse;
import com.assignment.example.top10posts.Utils.Constants;
import com.assignment.example.top10posts.Instagram.InstagramAPI;
import com.assignment.example.top10posts.Instagram.InstagramSession;
import com.assignment.example.top10posts.R;
import com.assignment.example.top10posts.Utils.Messages;


public class InstagramFragment extends Fragment {

    private WebView webView;

    private Callbacks listener;
    private InstagramSession instagramSession;

    public InstagramFragment() {
    }

    public static InstagramFragment newInstance() {
        return new InstagramFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callbacks) {
            listener = (Callbacks) context;
        } else {
            // TODO: Check this
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instagramSession = InstagramSession.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.instagram_fragment, container, false);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookies(null); // TODO: This can't be called all the time
        webView = (WebView) rootView.findViewById(R.id.instagram_web_view);
        webView.setWebViewClient(new ResponseReader());
        webView.loadUrl(InstagramAPI.getAuthorizeUrl());
        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private class ResponseReader extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith(Constants.REDIRECT_URI)) {
                CodeParse codeParse = new CodeParse();
                codeParse.parseCode(url);
                if (codeParse.isSuccessful()) {
                    instagramSession.setCode(codeParse.getCode());
                    InstagramAPI.requestAccessToken(getContext());
                    if(listener != null)
                        listener.onUserAuthorizeSuccessful();
                }
                else {
                    Messages.showToastMessage(getActivity().getString(R.string.error_getting_access_token), getActivity());
                    if(listener != null)
                        listener.onUserAuthorizeFail();
                }
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Messages.showToastMessage(getActivity().getString(R.string.error_getting_access_token), getActivity());
            if(listener != null)
                listener.onUserAuthorizeFail();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    public interface Callbacks {
        void onUserAuthorizeSuccessful();
        void onUserAuthorizeFail();
    }
}
