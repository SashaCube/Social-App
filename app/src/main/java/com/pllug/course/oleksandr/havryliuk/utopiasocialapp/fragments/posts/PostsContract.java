package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.posts;

import android.view.View;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Post;

import java.util.List;

public interface PostsContract {

    interface IPostsView{
        void showPosts(List<Post> posts);

        void init(View root);

        void setPresenter(IPostsPresenter presenter);
    }

    interface IPostsPresenter{

        void loadPosts();

    }

    interface IPostsModel{

        void loadPosts(final Utils.LoadData<List<Post>> callback);

    }
}
