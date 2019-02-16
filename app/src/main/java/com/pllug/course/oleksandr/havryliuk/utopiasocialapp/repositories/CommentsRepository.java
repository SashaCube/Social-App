package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.repositories;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.APIInterface;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.RetrofitClient;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.comments.CommentsContract;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsRepository implements CommentsContract.ICommentModel {
    private static CommentsRepository INSTANCE;
    private APIInterface api;

    public static CommentsRepository getInstance() {
        if(INSTANCE == null){
            INSTANCE = new CommentsRepository();
            INSTANCE.api = RetrofitClient.getApi();
        }
        return INSTANCE;
    }

    @Override
    public void loadComments(final Utils.LoadData<List<Comment>> callback) {

        api.getComments().enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.body() == null) {
                    callback.onFailure();
                    return;
                }
                callback.onData(response.body());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
