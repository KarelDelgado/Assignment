package com.assignment.example.top10posts.Fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.example.top10posts.Instagram.Model.InstLocation;
import com.assignment.example.top10posts.Instagram.Model.Post;
import com.assignment.example.top10posts.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder> {

    private final List<Post> items;
    private Context context;

    public PostRecyclerViewAdapter(List<Post> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = items.get(position);
        Picasso.with(context)
                .load(holder.item.getImages().getStandardResolution().getUrl())
                .placeholder(R.drawable.default_placeholder)
                .into(holder.postImage);
        Picasso.with(context)
                .load(holder.item.getUser().getProfilePicture())
                .placeholder(R.drawable.default_placeholder)
                .into(holder.userImage);
        holder.userName.setText(holder.item.getUser().getUsername());
        InstLocation location = holder.item.getLocation();
        if(location != null) {
            holder.location.setText(location.getName());
        }
        int likes = holder.item.getLikes().getCount();
        if(likes > 0) {
            holder.likes.setText(String.valueOf(likes));
        }
        else {
            holder.likesContainer.setVisibility(View.GONE);
        }
        holder.userComment.setText(holder.item.getCaption().getText());
        int comments = holder.item.getComments().getCount();
        if(comments > 0) {
            holder.comments.setText(String.valueOf(comments));
        }
        else {
            holder.commentsContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final ImageView userImage;
        public final TextView userName;
        public final TextView location;
        public final ImageView postImage;
        public final TextView userComment;
        public final TextView likes;
        public final TextView comments;
        public final View likesContainer;
        public final View commentsContainer;
        public Post item;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            userImage = (ImageView) view.findViewById(R.id.post_user_image);
            userName = (TextView) view.findViewById(R.id.post_user_name);
            location = (TextView) view.findViewById(R.id.post_location);
            postImage = (ImageView) view.findViewById(R.id.post_image);
            likes = (TextView) view.findViewById(R.id.post_likes);
            userComment = (TextView) view.findViewById(R.id.post_user_comment);
            comments = (TextView) view.findViewById(R.id.post_comments);
            likesContainer = (View) view.findViewById(R.id.post_likes_container);
            commentsContainer = (View) view.findViewById(R.id.post_comments_container);
        }
    }
}
