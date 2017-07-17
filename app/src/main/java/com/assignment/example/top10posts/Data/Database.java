package com.assignment.example.top10posts.Data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.assignment.example.top10posts.Instagram.Model.Media;
import com.assignment.example.top10posts.Instagram.Model.Post;
import com.assignment.example.top10posts.R;
import com.assignment.example.top10posts.Utils.Constants;
import com.assignment.example.top10posts.Utils.Messages;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by karel on 7/15/2017.
 */

public class Database {

    private Realm realm;
    private Context context;

    public Database(Context context) {
        realm = Realm.getDefaultInstance();
        this.context = context;
    }

    public void saveInstagramMedia(final Media media) {
        if(realm.isClosed())
            realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Post> posts = realm.where(Post.class).findAllSorted("id");
                for (Post post : media.getData()) {
                    if(posts.where().equalTo("id", post.getId()).findAll().size() == 0) {
                        realm.copyToRealm(post);
                    }
                }
            }
        });
        if(media.getData().size() == 0) {
            Messages.showToastMessage(context.getString(R.string.error_getting_new_posts), context);
        }
    }

    public RealmResults<Post> getPosts() {
        if(realm.isClosed())
            realm = Realm.getDefaultInstance();

        return realm.where(Post.class).findAllSorted("id", Sort.DESCENDING);
    }

    public void close() {
        realm.close();
    }
}
