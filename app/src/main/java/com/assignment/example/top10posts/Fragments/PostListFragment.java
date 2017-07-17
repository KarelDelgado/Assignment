package com.assignment.example.top10posts.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.example.top10posts.Data.Database;
import com.assignment.example.top10posts.Instagram.Model.Post;
import com.assignment.example.top10posts.R;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class PostListFragment extends Fragment {

    private Database database;
    private RealmResults<Post> posts;
    private PostRecyclerViewAdapter postRecyclerViewAdapter;

    public PostListFragment() {
    }

    public static PostListFragment newInstance() {
        return new PostListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new Database(getActivity());
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
        View view = inflater.inflate(R.layout.post_list_fragment, container, false);
        postRecyclerViewAdapter = new PostRecyclerViewAdapter(posts, getActivity());
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(postRecyclerViewAdapter);
        }
        return view;
    }
}
