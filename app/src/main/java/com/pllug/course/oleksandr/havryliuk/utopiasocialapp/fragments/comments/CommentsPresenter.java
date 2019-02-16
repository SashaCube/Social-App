package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.comments;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.APIInterface;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.RetrofitClient;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Comment;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.repositories.CommentsRepository;

import java.util.List;

public class CommentsPresenter implements CommentsContract.ICommentPresenter {

    private CommentsContract.ICommentModel model;
    private CommentsContract.ICommentsView view;

    public CommentsPresenter(CommentsContract.ICommentModel model,
                             CommentsContract.ICommentsView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void loadComments() {
        model.loadComments(new Utils.LoadData<List<Comment>>() {
            @Override
            public void onData(List<Comment> data) {
                view.showComments(data);
            }

            @Override
            public void onFailure() {
                //TODO display problem
            }
        });
    }
}
