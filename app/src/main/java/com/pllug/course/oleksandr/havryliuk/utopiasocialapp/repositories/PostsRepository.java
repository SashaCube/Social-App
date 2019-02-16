package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.repositories;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.APIInterface;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.RetrofitClient;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.posts.PostsContract;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsRepository implements PostsContract.IPostsModel {
    private static PostsRepository INSTANCE;
    private APIInterface api;

    public static PostsRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PostsRepository();
            INSTANCE.api = RetrofitClient.getApi();
        }
        return INSTANCE;
    }

    @Override
    public void loadPosts(final Utils.LoadData<List<Post>> callback) {

        api.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.body() == null) {
                    callback.onFailure();
                    return;
                }
                callback.onData(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
