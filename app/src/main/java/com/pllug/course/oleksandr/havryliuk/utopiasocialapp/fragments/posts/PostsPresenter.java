package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.posts;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Post;

import java.util.List;

public class PostsPresenter implements PostsContract.IPostsPresenter {

    private PostsContract.IPostsView view;
    private PostsContract.IPostsModel model;

    public PostsPresenter(PostsContract.IPostsModel model, PostsContract.IPostsView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void loadPosts() {
        model.loadPosts(new Utils.LoadData<List<Post>>() {
            @Override
            public void onData(List<Post> data) {
                view.showPosts(data);
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
