package com.assignment.example.top10posts.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.example.top10posts.Data.Database;
import com.assignment.example.top10posts.Instagram.InstagramAPI;
import com.assignment.example.top10posts.Instagram.InstagramSession;
import com.assignment.example.top10posts.Instagram.Model.Post;
import com.assignment.example.top10posts.R;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class PostListFragment extends Fragment {

    private RealmResults<Post> posts;
    private PostRecyclerViewAdapter postRecyclerViewAdapter;
    private View view;

    public PostListFragment() {
    }

    public static PostListFragment newInstance() {
        return new PostListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Database database = new Database(getActivity());
        posts = database.getPosts();
        posts.addChangeListener(new RealmChangeListener<RealmResults<Post>>() {
            @Override
            public void onChange(RealmResults<Post> element) {
                postRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.post_list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        postRecyclerViewAdapter = new PostRecyclerViewAdapter(posts, getActivity());
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(postRecyclerViewAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(InstagramSession.getInstance(getContext()).isAccessTokenAvailable()) {
            InstagramAPI.requestMedia(getActivity());
        }
        else {
            InstagramAPI.requestAccessToken(getContext());
        }
    }
}
