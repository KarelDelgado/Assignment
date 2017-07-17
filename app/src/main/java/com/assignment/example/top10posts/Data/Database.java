package com.assignment.example.top10posts.Data;

import android.content.Context;
import android.util.Log;

import com.assignment.example.top10posts.Instagram.Model.Media;
import com.assignment.example.top10posts.Instagram.Model.Post;
import com.assignment.example.top10posts.R;
import com.assignment.example.top10posts.Utils.Constants;
import com.assignment.example.top10posts.Utils.Messages;

import java.util.concurrent.atomic.AtomicBoolean;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by karel on 7/15/2017.
 */

public class Database {

    private Realm realm;
    private Context context;
    private final String POST_ID = "id";

    public Database(Context context) {
        realm = Realm.getDefaultInstance();
        this.context = context;
    }

    public void saveInstagramMedia(final Media media) {
        if(realm.isClosed())
            realm = Realm.getDefaultInstance();

        final AtomicBoolean newPostsAdded = new AtomicBoolean(false);
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Post> posts = realm.where(Post.class).findAll();
                for (Post post : media.getData()) {
                    if(posts.where().equalTo(POST_ID, post.getId()).findAll().size() == 0) {
                        realm.copyToRealm(post);
                        newPostsAdded.set(true);
                    }
                }
            }
        });
        if(!newPostsAdded.get()) {
            Messages.showToastMessage(context.getString(R.string.error_new_posts_not_found), context);
        }
    }

    public RealmResults<Post> getPosts() {
        if(realm.isClosed())
            realm = Realm.getDefaultInstance();

        return realm.where(Post.class).findAllSorted(POST_ID, Sort.DESCENDING);
    }

    public void close() {
        realm.close();
    }
}
