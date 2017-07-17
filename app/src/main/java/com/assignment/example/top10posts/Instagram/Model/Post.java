package com.assignment.example.top10posts.Instagram.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by karel on 7/14/2017.
 */

public class Post extends RealmObject{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("created_time")
    @Expose
    private long createdTime;
    @SerializedName("caption")
    @Expose
    private Caption caption;
    @SerializedName("user_has_liked")
    @Expose
    private Boolean userHasLiked;
    @SerializedName("likes")
    @Expose
    private Likes likes;
    @SerializedName("filter")
    @Expose
    private String filter;
    @SerializedName("comments")
    @Expose
    private Comments comments;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("location")
    @Expose
    private InstLocation location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public Caption getCaption() {
        return caption;
    }

    public void setCaption(Caption caption) {
        this.caption = caption;
    }

    public Boolean getUserHasLiked() {
        return userHasLiked;
    }

    public void setUserHasLiked(Boolean userHasLiked) {
        this.userHasLiked = userHasLiked;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public InstLocation getLocation() {
        return location;
    }

    public void setLocation(InstLocation location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", images=" + images +
                ", createdTime=" + createdTime +
                ", caption=" + caption +
                ", userHasLiked=" + userHasLiked +
                ", likes=" + likes +
                ", filter='" + filter + '\'' +
                ", comments=" + comments +
                ", type='" + type + '\'' +
                ", link='" + link + '\'' +
                ", location=" + location +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Post post = (Post) o;
        return getCreatedTime() == post.getCreatedTime() && getId().equals(post.getId());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (int) (getCreatedTime() ^ (getCreatedTime() >>> 32));
        return result;
    }
}
